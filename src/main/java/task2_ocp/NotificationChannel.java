package task2_ocp;

public interface NotificationChannel {

    String name();

    boolean send(NotificationMessage message);
}
