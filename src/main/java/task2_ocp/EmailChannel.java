package task2_ocp;

public class EmailChannel implements NotificationChannel {

    @Override
    public String name() {
        return "EMAIL";
    }

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[EMAIL] To: " + message.recipient());
        System.out.println("[EMAIL] Subject: " + message.subject());
        System.out.println("[EMAIL] Body: " + message.body());
        System.out.println("[EMAIL] Status: Sent via SMTP server.");
        return true;
    }
}
