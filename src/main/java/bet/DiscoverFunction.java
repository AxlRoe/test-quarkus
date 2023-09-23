package bet;

import bet.domain.Discover;
import io.quarkus.funqy.Funq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("test")
public class DiscoverFunction {
    @Inject
    private Discover discover;
    @Funq
    public void trigger(Object pubSubEvent) {
        System.out.println("Receive event Id: ");
        discover.run();
        System.out.println("Discover finish ");
    }
}
