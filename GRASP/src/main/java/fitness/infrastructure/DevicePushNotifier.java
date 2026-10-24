package fitness.infrastructure;

import fitness.application.PushNotifier;

public class DevicePushNotifier implements PushNotifier {
    @Override
    public String notifyMember(String memberName) {
        return "[Push Notification] Message sent to the device of client " + memberName;
    }
}
