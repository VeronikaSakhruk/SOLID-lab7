package fitness.infrastructure;

import fitness.application.SubscriptionRepository;
import fitness.domain.Subscription;
import java.util.HashMap;
import java.util.Map;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final Map<Integer, Subscription> table = new HashMap<>();

    @Override
    public String save(Subscription subscription) {
        table.put(subscription.id(), subscription);
        return "[Database] Subscription #" + subscription.id() + " added to table subscriptions.";
    }
}
