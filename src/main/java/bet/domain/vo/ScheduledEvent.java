package bet.domain.vo;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@EqualsAndHashCode
@Getter
@ToString
public class ScheduledEvent {
    private String eventId;
    private String competitionId;
    private String eventName;
    private LocalDateTime cutoffDate;
}
