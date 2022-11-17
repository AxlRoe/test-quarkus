package bet.configuration;

import bet.infra.adapters.RunnersApi;
import bet.infra.adapters.RunnersApiClient;
import bet.infra.adapters.ScheduleApi;
import bet.infra.adapters.ScheduleApiClient;
import bet.infra.adapters.feign.BetFeignClient;
import bet.infra.adapters.feign.ExchangeApi;
import bet.infra.adapters.feign.ExchangeFeignClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;

public class FeignConfig {

    @ConfigProperty(name = "exchange.url")
    private String exchangeUrl;

    @ApplicationScoped
    public RunnersApi runnersApi() {
        BetFeignClient client = new BetFeignClient<ExchangeApi>();
        ExchangeApi api = new ExchangeFeignClient(client, exchangeUrl);
        return new RunnersApiClient(api);
    }

    @ApplicationScoped
    public ScheduleApi scheduleApi () {
        BetFeignClient client = new BetFeignClient<ExchangeApi>();
        ExchangeApi api = new ExchangeFeignClient(client, exchangeUrl);
        return new ScheduleApiClient(api);
    }
}
