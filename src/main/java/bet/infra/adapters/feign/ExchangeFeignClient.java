package bet.infra.adapters.feign;

import bet.infra.dto.request.ExchangeRequest;
import bet.infra.dto.response.MatchScheduleResponse;
import bet.infra.dto.response.RunnerResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExchangeFeignClient implements ExchangeApi {

    private BetFeignClient<ExchangeApi> client;
    private String url;

    public ExchangeFeignClient(final BetFeignClient<ExchangeApi> client, final String url) {
        log.debug("Exchange feign client: url {}", url);
        this.client = client;
        this.url = url;
    }

    @Override
    public MatchScheduleResponse scrapeTodaySchedule() {
        return client.target(ExchangeApi.class, url).scrapeTodaySchedule();
    }

    @Override
    public RunnerResponse askForRunners(ExchangeRequest body) {
        return client.target(ExchangeApi.class, url).askForRunners(body);
    }
}
