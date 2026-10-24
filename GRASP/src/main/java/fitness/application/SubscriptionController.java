package fitness.application;

import fitness.domain.Subscription;
import java.util.List;

public class SubscriptionController {
    private final SubscriptionRepository repository;
    private final PushNotifier notifier;

    public SubscriptionController(SubscriptionRepository repository, PushNotifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public List<String> register(RegistrationRequest request) {
        Subscription subscription = Subscription.open(1002, request.memberName(), request.planCode(), true, "2026-11-01");
        String contract = subscription.contractText();
        String storage = repository.save(subscription);
        String push = notifier.notifyMember(subscription.memberName());
        return List.of(contract, storage, push);
    }
}
