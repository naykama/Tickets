import dto.TicketDto;
import dto.TicketListDto;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository repository = new TicketRepository();

    public void saveAll(TicketListDto dtoList) {
        for (TicketDto ticketDto : dtoList.getTickets()) {
            repository.save(TicketMapper.convertToTicket(ticketDto));
        }
        System.out.println(repository.findById(2));
    }
}
