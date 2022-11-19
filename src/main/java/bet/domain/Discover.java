package bet.domain;

import bet.domain.vo.*;
import bet.infra.adapters.RunnersApi;
import bet.infra.adapters.ScheduleApi;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class Discover {

    private final ScheduleApi scheduleApi;
    private final RunnersApi runnersApi;

    public Discover(ScheduleApi scheduleApi, RunnersApi runnersApi) {
        this.scheduleApi = scheduleApi;
        this.runnersApi = runnersApi;
    }

    public void run() {
        System.out.println("Starting run ");
        List<MatchSchedule> eventsNotStarted = scheduleApi.scrapeTodaySchedule()
                .stream()
                .filter(schedule -> isNotStartedYet(schedule.getCutoffDate()))
                .collect(Collectors.toList());

        List<Match> matches = eventsNotStarted
        .stream()
        .map(MatchSchedule::getEvents)
        .flatMap(Collection::stream)
        .map(event -> Match.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .competitionId(event.getCompetitionId())
                .cutoffDate(event.getCutoffDate())
                .build())
        .collect(Collectors.toList());

        log.info("Scraped #{} matches from exchange ", matches.size());

        List<MatchAndRunner> matchAndRunners = getMatchAndRunners(eventsNotStarted);
        Map<String, Match> additionalMatchInfo = matchAndRunners
                .stream()
                .map(MatchAndRunner::getMatch)
                .collect(Collectors.toMap(Match::getEventId, m -> m));
        Map<String, List<Runner>> runnerMap = matchAndRunners.stream().collect(Collectors.toMap(mr -> mr.getMatch().getEventId(), MatchAndRunner::getRunners));

        List<Match> enrichedMatches = enrichMatches(matches, additionalMatchInfo);

        log.info("Sent discover message ");

        log.info("Notify new events to scrape arrived ");

        System.out.println("Ending run ");
    }

    private List<MatchAndRunner> getMatchAndRunners(List<MatchSchedule> eventsNotStarted) {
        ExecutorService executorService = Executors.newFixedThreadPool(eventsNotStarted.size());
        return eventsNotStarted.stream()
                .map(schedule -> CompletableFuture.supplyAsync(() -> {
                    List<String> eventIds = schedule.getEvents()
                            .stream()
                            .map(ScheduledEvent::getEventId)
                            .collect(Collectors.toList());
                    return runnersApi.scrapeRunnersFor(eventIds);
                }, executorService))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Match> enrichMatches(List<Match> matches, Map<String, Match> additionalMatchInfo) {
        return matches
        .stream()
        .map(m -> {
            Match match = additionalMatchInfo.get(m.getEventId());
            return match.toBuilder()
                    .eventId(m.getEventId())
                    .competitionId(m.getCompetitionId())
                    .eventName(m.getEventName())
                    .cutoffDate(m.getCutoffDate())
                    .home(match.getHome())
                    .guest(match.getGuest())
                    .favourite(match.getFavourite())
                    .delta(match.getDelta())
                    .build();
        }).collect(Collectors.toList());
    }

    private boolean isNotStartedYet(LocalDateTime cutoffDate) {
        Instant startAt = cutoffDate.atOffset(ZoneOffset.UTC).withOffsetSameInstant(OffsetDateTime.now().getOffset()).toInstant();
        return startAt.compareTo(OffsetDateTime.now().toInstant()) >= 0;
    }
}

