package bet.configuration;

import bet.infra.dto.message.DiscoverMessage;
import bet.infra.dto.message.MatchAndRunnerMessage;
import bet.infra.ports.BrokerRepository;
import bet.infra.ports.PubSubRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

public class PubSubConfig {

    @ConfigProperty(name = "pubsub.schedule.out.topic")
    private String scheduleTopic;

    @ConfigProperty(name = "pubsub.mr.out.topic")
    private String mrTopic;

    @ApplicationScoped
    public BrokerRepository<DiscoverMessage> brokerDiscoverRepository(ObjectMapper objectMapper) {
        return new PubSubRepository<>(scheduleTopic, objectMapper);
    }

    @ApplicationScoped
    public BrokerRepository<MatchAndRunnerMessage> brokerMrRepository(ObjectMapper objectMapper) {
        return new PubSubRepository<>(mrTopic, objectMapper);
    }
}
