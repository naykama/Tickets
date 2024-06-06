import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TicketListDto;

import java.io.File;
import java.io.IOException;

public class TestTaskApp {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TicketService ticketService = new TicketServiceImpl();

    public static void main(String[] args) {
        TicketListDto tickets;
        File file = new File("src/main/resources/tickets.json");
        try {
            tickets = objectMapper.readValue(file, TicketListDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ticketService.saveAll(tickets);
        System.out.println(ticketService.getMinTimeBetween("VVO", "TLV"));
        System.out.println(ticketService.getDiffAverageMediumPriceBetween("VVO", "TLV"));
    }
}
