import dto.TicketDto;
import exception.NotFoundException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TicketMapper {

    private static final String DATE_PATTERN = "dd.MM.yy H:mm";

    private static final Map<String, ZoneId> zoneDictionary = Map.ofEntries(
            Map.entry("VVO", ZoneId.of("+10:00")),
            Map.entry("TLV", ZoneId.of("+03:00")),
            Map.entry("UFA", ZoneId.of("+05:00")),
            Map.entry("LRN", ZoneId.of("+03:00"))

    );

    public static Ticket convertToTicket(TicketDto dto) {
        return new Ticket(dto.getOriginCode(), dto.getOriginName(), dto.getDestinationCode(),
                dto.getDestinationName(), getTimeUTC(dto.getDepartureDate(), dto.getDepartureTime(), dto.getOriginCode()),
                getTimeUTC(dto.getArrivalDate(), dto.getArrivalTime(), dto.getDestinationCode()),
                dto.getCarrierName(), dto.getStops(), dto.getPrice());
    }

    private static Instant getTimeUTC(String date, String time, String placeCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse(String.join(" ", date, time), formatter);
        try {
            ZonedDateTime zonedDateTime = localDateTime.atZone(zoneDictionary.get(placeCode));
            return zonedDateTime.toInstant();
        } catch (NullPointerException e) {
            throw new NotFoundException("В словаре нет временной зоны для пункта " + placeCode);
        }
    }
}
