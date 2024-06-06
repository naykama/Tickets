import dto.TicketListDto;

import java.util.Map;

public interface TicketService {
    void saveAll(TicketListDto dto);
    Map<String, Long> getMinTimeBetween(String originCode, String destinationCode);
    double getDiffAverageMediumPriceBetween(String originCode, String destinationCode);
}
