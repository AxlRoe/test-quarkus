package bet.infra.adapters;

import bet.domain.vo.MatchSchedule;
import bet.domain.vo.ScheduledEvent;
import bet.infra.adapters.feign.ExchangeApi;
import bet.infra.dto.MatchDTO;
import bet.infra.dto.response.MatchScheduleResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class ScheduleApiClient implements ScheduleApi {

    private ExchangeApi client;

    public ScheduleApiClient(ExchangeApi client) {
        this.client = client;
    }

    @Override
    public List<MatchSchedule> scrapeTodaySchedule() {

        MatchScheduleResponse response = client.scrapeTodaySchedule();
        log.debug("Scraped today schedule");
        return fromDTO(response.getSchedule());
    }

    private List<MatchSchedule> fromDTO (List<MatchDTO> dtos) {
        return dtos.stream()
        .map(dto -> ScheduledEvent.builder()
                .cutoffDate(dto.getCutoffDate())
                .eventId(dto.getId())
                .competitionId(dto.getCompetition())
                .eventName(dto.getName())
                .build())
        .collect(groupingBy(ScheduledEvent::getCutoffDate))
        .entrySet()
        .stream()
        .map(entry -> MatchSchedule.builder()
                .cutoffDate(entry.getKey())
                .events(entry.getValue())
                .build())
        .collect(Collectors.toList());
    }
}
