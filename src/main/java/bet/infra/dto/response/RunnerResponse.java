package bet.infra.dto.response;

import bet.infra.dto.RunnerDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class RunnerResponse {
    @JsonProperty("runners")
    private List<RunnerDTO> runners;

    @JsonCreator
    public RunnerResponse(List<RunnerDTO> runners) {
        this.runners = runners;
    }
}
