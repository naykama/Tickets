import dto.TicketDto;
import dto.TicketListDto;
import exception.NotFoundException;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository repository = new TicketRepository();

    @Override
    public void saveAll(TicketListDto dtoList) {
        for (TicketDto ticketDto : dtoList.getTickets()) {
            repository.save(TicketMapper.convertToTicket(ticketDto));
        }
    }

    @Override
    public Map<String, Long> getMinTimeBetween(String originCode, String destinationCode) {
        List<Ticket> tickets = repository.findByPlace(originCode, destinationCode)
                .orElseThrow(() -> new NotFoundException(String.format("Tickets for flight from %s to %s not found",
                        originCode, destinationCode)));
        Map<String, Long> minTimeForCarrier = new HashMap<>();
        for (Ticket ticket : tickets) {
            String carrierName = ticket.getCarrierName();
            minTimeForCarrier.put(carrierName, minTimeForCarrier.get(carrierName) == null
                        || minTimeForCarrier.get(carrierName) > getFlightDurationInMinutes(ticket)
                    ? getFlightDurationInMinutes(ticket)
                    : minTimeForCarrier.get(carrierName));
        }
        return minTimeForCarrier;
    }

    @Override
    public double getDiffAverageMediumPriceBetween(String originCode, String destinationCode) {
        List<Integer> ticketPriceList = repository.findByPlace(originCode, destinationCode)
                .orElseThrow(() -> new NotFoundException(String.format("Tickets for flight from %s to %s not found",
                originCode, destinationCode))).stream()
                .map(Ticket::getPrice)
                .collect(Collectors.toList());
        return Math.abs(getAverage(ticketPriceList) - getMedium(ticketPriceList));
    }

    private double getAverage(List<Integer> priceList) {
        return priceList.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
    }

    private double getMedium(List<Integer> priceList) {
         priceList.sort(Integer::compare);
         return priceList.size() == 1 ? priceList.get(0)
                 : priceList.size() % 2 == 0
                    ? (double) (priceList.get(priceList.size() / 2) + priceList.get(priceList.size() / 2 - 1)) / 2
                    :  priceList.get(priceList.size() / 2);
    }

    private long getFlightDurationInMinutes(Ticket ticket) {
        return ChronoUnit.MINUTES.between(ticket.getDepartureDateTime(), ticket.getArrivalDateTime());
    }
}
