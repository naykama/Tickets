import java.util.*;
import java.util.stream.Collectors;

public class TicketRepository {
    private long id;

    private final HashMap<Long, Ticket> mainRepository;

    private final HashMap<String, Set<Long>> byPlaceRepository;

    public TicketRepository() {
        mainRepository = new HashMap<>();
        byPlaceRepository = new HashMap<>();
        id = 0L;
    }

    public Ticket save(Ticket ticket) {
        ticket.setId(++id);
        mainRepository.put(ticket.getId(), ticket);
        byPlaceRepository.computeIfAbsent(ticket.getOriginCode() + ticket.getDestinationCode(), k -> new HashSet<>())
                .add(ticket.getId());
        return ticket;
    }

    public Optional<Ticket> findById(long id) {
        return Optional.ofNullable(mainRepository.get(id));
    }

    public Optional<List<Ticket>> findByPlace(String originCode, String destinationCode) {
        String key = originCode + destinationCode;
        return Optional.ofNullable(!byPlaceRepository.containsKey(key) ? null : byPlaceRepository.get(key).stream()
                .map(mainRepository::get)
                .collect(Collectors.toList()));
    }
}
