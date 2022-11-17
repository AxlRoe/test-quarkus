package bet.infra.adapters;

import bet.domain.vo.MatchSchedule;

import java.util.List;

public interface ScheduleApi {
    List<MatchSchedule> scrapeTodaySchedule();
}
