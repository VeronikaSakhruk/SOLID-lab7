package task5_dip;

import java.util.ArrayList;
import java.util.List;

public class MockSmsNotifier implements SmsNotifier {

    private final List<String> sentMessages = new ArrayList<>();

    @Override
    public void sendSmsReceipt(String phone, String transactionId, double amount) {
        sentMessages.add(phone + ":" + transactionId + ":" + amount);
    }

    public List<String> getSentMessages() {
        return sentMessages;
    }
}
