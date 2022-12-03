package bet.configuration;

import bet.infra.dto.message.DiscoverMessage;
import bet.infra.dto.message.MatchAndRunnerMessage;
import bet.infra.ports.BrokerRepository;
import bet.infra.ports.PubSubRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PubSubConfig {

    @ConfigProperty(name = "pubsub.schedule.out.topic")
    private String scheduleTopic;

    @ConfigProperty(name = "pubsub.mr.out.topic")
    private String mrTopic;

    @Inject
    private ObjectMapper objectMapper;

    @ApplicationScoped
    public BrokerRepository<DiscoverMessage> brokerDiscoverRepository() {
        return new PubSubRepository<>(scheduleTopic, objectMapper);
    }

    @ApplicationScoped
    public BrokerRepository<MatchAndRunnerMessage> brokerMrRepository() {
        return new PubSubRepository<>(mrTopic, objectMapper);
    }
}
