import lombok.*;

import java.time.Instant;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
    private long id;
    private String originCode;
    private String originName;
    private String destinationCode;
    private String destinationName;
    private Instant departureDateTime;
    private Instant arrivalDateTime;
    private String carrierName;
    private int stops;
    private int price;

    public Ticket(String originCode, String originName, String destinationCode, String destinationName,
                  Instant departureDateTime, Instant arrivalDateTime, String carrierName, int stops, int price) {
        this.originCode = originCode;
        this.originName = originName;
        this.destinationCode = destinationCode;
        this.destinationName = destinationName;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.carrierName = carrierName;
        this.stops = stops;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
