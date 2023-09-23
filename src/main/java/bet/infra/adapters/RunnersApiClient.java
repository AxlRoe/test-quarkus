package bet.infra.adapters;

import bet.domain.vo.Match;
import bet.domain.vo.Runner;
import bet.domain.vo.MatchAndRunner;
import bet.infra.adapters.feign.ExchangeApi;
import bet.infra.dto.RunnerDTO;
import bet.infra.dto.request.ExchangeRequest;
import bet.infra.dto.response.RunnerResponse;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.Default;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class RunnersApiClient implements RunnersApi {

    private ExchangeApi client;

    public RunnersApiClient(ExchangeApi client) {
        this.client = client;
    }

    @Override
    public List<MatchAndRunner> scrapeRunnersFor(List<String> eventIds) {
        log.debug("scrape runners for event {}", String.join(";", eventIds));
        ExchangeRequest request = ExchangeRequest.builder().eventids(eventIds).build();
        RunnerResponse response = client.askForRunners(request);

        return fromDTO(response.getRunners());
    }

    private List<MatchAndRunner> fromDTO (List<RunnerDTO> dtos) {

        return dtos.stream()
                .collect(groupingBy(RunnerDTO::getEventId))
                .entrySet()
                .stream()
                .map(entry -> MatchAndRunner.builder()
                        .traceid(entry.getValue().get(0).getTraceid())
                        .match(Match.builder()
                                .eventId(entry.getValue().get(0).getEventId())
                                .home(entry.getValue().get(0).getHome())
                                .guest(entry.getValue().get(0).getGuest())
                                .favourite(entry.getValue().get(0).getFavourite())
                                .delta(entry.getValue().get(0).getDelta())
                                .build())

                        .runners(entry.getValue()
                                .stream()
                                .map(dto -> Runner.builder()
                                    .available(dto.getAvailable())
                                    .odd(dto.getOdd())
                                    .startOdd(dto.getStartOdd())
                                    .id(dto.getEventId())
                                    .marketId(dto.getMarketId())
                                    .marketName(dto.getMarketName())
                                    .matched(dto.getMatched())
                                    .totalMatched(dto.getTotalMatched())
                                    .totalAvailable(dto.getTotalAvailable())
                                    .runnerId(dto.getRunnerId())
                                    .runnerName(dto.getRunnerName())
                                    .build()).collect(Collectors.toList()))
                                .build())
                .collect(Collectors.toList());
    }
}
