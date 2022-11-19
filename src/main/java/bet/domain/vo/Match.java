package bet.domain.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@EqualsAndHashCode
@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Match {
    private String eventId;
    private String competitionId;
    private String eventName;
    private String home;
    private String guest;
    private String favourite;
    private Double delta;
    private LocalDateTime cutoffDate;
    private String outcome;
}
