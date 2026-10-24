package task2_ocp;

public class SmsChannel implements NotificationChannel {

    private static final int MAX_LENGTH = 160;
    private static final String ELLIPSIS = "...";

    @Override
    public String name() {
        return "SMS";
    }

    @Override
    public boolean send(NotificationMessage message) {
        String body = message.body();
        String text = body.length() > MAX_LENGTH ? body.substring(0, MAX_LENGTH - ELLIPSIS.length()) + ELLIPSIS : body;
        System.out.println("[SMS] Phone: " + message.recipient());
        System.out.println("[SMS] Text: " + text);
        System.out.println("[SMS] Status: Sent via GSM gateway.");
        return true;
    }
}
