package bet.infra.ports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PubSubRepository<T> implements BrokerRepository<T> {
    @ConfigProperty(name = "gcp.project.id")
    private String projectId;

    final private String topic;
    final private ObjectMapper mapper;

    public PubSubRepository(String topic, ObjectMapper mapper) {
        this.topic = topic;
        this.mapper = mapper;
    }

    @SneakyThrows
    @Override
    public boolean send(T body) {
        System.out.println("topic " + topic + " project " + projectId);
        TopicName topicName = TopicName.of(projectId, topic);

        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(mapper.writeValueAsString(body));
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            log.info("Published message ID: {}", messageId);

            return true;
        } catch (InterruptedException | ExecutionException | IOException e) {
            log.error("Something went wrong during sending message {}", body.toString(), e);
            return false;

        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

}
