package task2_ocp;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        NotificationSender sender = new NotificationSender(List.of(
                new EmailChannel(),
                new SmsChannel(),
                new PushChannel(),
                new TelegramChannel(),
                new SlackChannel()));

        NotificationMessage message = new NotificationMessage("user@example.com", "Hello", "Your order has been shipped.", "HIGH");

        sender.send(message, "EMAIL");
        sender.send(message, "SMS");
        sender.send(message, "PUSH");
        sender.send(message, "TELEGRAM");
        sender.send(message, "SLACK");
    }
}
