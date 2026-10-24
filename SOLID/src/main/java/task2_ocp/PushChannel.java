package task2_ocp;

public class PushChannel implements NotificationChannel {

    @Override
    public String name() {
        return "PUSH";
    }

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[PUSH] DeviceToken: " + message.recipient());
        System.out.println("[PUSH] Payload: { \"title\": \"" + message.subject() + "\", \"body\": \"" + message.body() + "\" }");
        System.out.println("[PUSH] Status: Delivered via Firebase Cloud Messaging.");
        return true;
    }
}
