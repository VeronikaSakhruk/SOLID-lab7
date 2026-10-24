package fitness.application;

import fitness.domain.Subscription;

public interface SubscriptionRepository {
    String save(Subscription subscription);
}
