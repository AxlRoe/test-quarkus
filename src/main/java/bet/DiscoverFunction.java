package bet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import bet.domain.Discover;
import com.google.pubsub.v1.PubsubMessage;
import io.quarkus.funqy.Funq;

@ApplicationScoped
@Named("test")
public class DiscoverFunction {

    @Inject
    private Discover discover;


    @Funq
    public void trigger(PubsubMessage pubSubEvent) {
        System.out.println("Receive event Id: ");
        discover.run();
        System.out.println("Discover finish ");
    }
}
