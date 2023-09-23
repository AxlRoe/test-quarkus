package bet.infra.ports;

public interface BrokerRepository<T> {
    boolean send(T body);
}
