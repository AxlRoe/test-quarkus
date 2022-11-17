package bet.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@EqualsAndHashCode
@ToString
public class MatchAndRunnerDTO {
    @JsonProperty("cutoffDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime cutoffDate;
    @JsonProperty("match")
    private MatchDTO match;
    @JsonProperty("runners")
    private List<RunnerDTO> runners;
}
