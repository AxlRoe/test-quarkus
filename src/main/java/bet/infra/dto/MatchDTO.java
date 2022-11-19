package bet.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
@Getter
@EqualsAndHashCode
@ToString
public class MatchDTO {
    private String id;
    private String competition;
    private String name;
    private String home;
    private String guest;
    private String favourite;
    private Double delta;
    @JsonProperty("cutoffDate")
    private LocalDateTime cutoffDate;
}
