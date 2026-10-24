package fitness.cli;

import fitness.application.SubscriptionController;
import fitness.infrastructure.DevicePushNotifier;
import fitness.infrastructure.InMemorySubscriptionRepository;

public class Main {
    public static void main(String[] args) {
        SubscriptionController controller = new SubscriptionController(
                new InMemorySubscriptionRepository(), new DevicePushNotifier());
        ConsoleCommandParser parser = new ConsoleCommandParser();

        System.out.println("=== Fitness club subscription registration ===");
        try {
            controller.register(parser.read()).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
