package bet.infra.dto.message;

import bet.domain.vo.MatchSchedule;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@EqualsAndHashCode
@ToString
public class DiscoverMessage {
    private List<MatchSchedule> eventsNotStarted;
}
