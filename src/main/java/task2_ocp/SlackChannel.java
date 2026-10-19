package task2_ocp;

public class SlackChannel implements NotificationChannel {

    @Override
    public String name() {
        return "SLACK";
    }

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[SLACK] Channel: " + message.recipient());
        System.out.println("[SLACK] Message: *" + message.subject() + "* " + message.body());
        System.out.println("[SLACK] Status: Posted via Slack Web API.");
        return true;
    }
}
