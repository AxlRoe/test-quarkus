package bet.infra.dto.message;

import bet.domain.vo.Match;
import bet.domain.vo.Runner;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@EqualsAndHashCode
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MatchAndRunnerMessage {
    private Match match;
    private List<Runner> runners;
}
