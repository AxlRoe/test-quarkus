package bet.infra.dto.request;

import lombok.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@EqualsAndHashCode
@ToString
public class ExchangeRequest {

    private String type;

    private LocalDateTime ts;

    private String resolution;

    @Valid
    private List<String> eventids;

}
