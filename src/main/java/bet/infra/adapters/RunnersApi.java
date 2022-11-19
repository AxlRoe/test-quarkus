package bet.infra.adapters;

import bet.domain.vo.MatchAndRunner;

import java.util.List;

public interface RunnersApi {
    List<MatchAndRunner> scrapeRunnersFor(List<String> eventIds);
}
