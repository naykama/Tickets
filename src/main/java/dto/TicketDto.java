package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class TicketDto {
    @JsonProperty("origin")
    private String originCode;
    @JsonProperty("origin_name")
    private String originName;
    @JsonProperty("destination")
    private String destinationCode;
    @JsonProperty("destination_name")
    private String destinationName;
    @JsonProperty("departure_date")
    private String departureDate;
    @JsonProperty("departure_time")
    private String departureTime;
    @JsonProperty("arrival_date")
    private String arrivalDate;
    @JsonProperty("arrival_time")
    private String arrivalTime;
    @JsonProperty("carrier")
    private String carrierName;
    private int stops;
    private int price;
}
