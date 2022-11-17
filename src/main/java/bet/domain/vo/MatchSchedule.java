package bet.domain.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@ToString
@EqualsAndHashCode
public class MatchSchedule {
    private LocalDateTime cutoffDate;
    private List<ScheduledEvent> events;
}
