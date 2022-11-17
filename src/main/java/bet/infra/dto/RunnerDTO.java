package bet.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@EqualsAndHashCode
@ToString
public class RunnerDTO {
    @JsonProperty("traceid")
    private String traceid;
    @JsonProperty("home")
    private String home;
    @JsonProperty("guest")
    private String guest;
    @JsonProperty("favourite")
    private String favourite;
    @JsonProperty("delta")
    private Double delta;
    @JsonProperty("runnerName")
    private String runnerName;
    @JsonProperty("marketName")
    private String marketName;
    @JsonProperty("marketId")
    private String marketId;
    @JsonProperty("exchangeId")
    private String exchangeId;
    @JsonProperty("runnerId")
    private String runnerId;
    @JsonProperty("eventId")
    private String eventId;
    @JsonProperty("lay")
    private double lay;
    @JsonProperty("back")
    private double back;
    @JsonProperty("matched")
    private double matched;
    @JsonProperty("available")
    private double available;
    @JsonProperty("totalMatched")
    private double totalMatched;
    @JsonProperty("totalAvailable")
    private double totalAvailable;
}
