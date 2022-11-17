package bet.infra.adapters.feign;

import bet.infra.dto.request.ExchangeRequest;
import bet.infra.dto.response.MatchScheduleResponse;
import bet.infra.dto.response.RunnerResponse;
import feign.Headers;
import feign.RequestLine;

public interface ExchangeApi {
    @RequestLine("POST /schedule")
    @Headers("Content-Type: application/json")
    MatchScheduleResponse scrapeTodaySchedule();

    @RequestLine("POST /runner")
    @Headers("Content-Type: application/json")
    RunnerResponse askForRunners(ExchangeRequest body);
}
