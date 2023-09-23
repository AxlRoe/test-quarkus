package bet.domain.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@EqualsAndHashCode
@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Runner {
    private String id;
    private String runnerId;
    private String marketId;
    private String marketName;
    private String runnerName;
    private double odd;
    private double startOdd;
    private double matched;
    private double available;
    private double totalAvailable;
    private double totalMatched;
}
