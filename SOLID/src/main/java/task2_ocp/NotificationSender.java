package task2_ocp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationSender {
    private final Map<String, NotificationChannel> channels = new HashMap<>();

    public NotificationSender(List<NotificationChannel> availableChannels) {
        for (NotificationChannel channel : availableChannels) {
            channels.put(channel.name(), channel);
        }
    }

    public boolean send(NotificationMessage message, String channelName) {
        NotificationChannel channel = channels.get(channelName);
        if (channel == null) {
            throw new IllegalArgumentException("Notification channel " + channelName + " is not supported.");
        }
        return channel.send(message);
    }
}
