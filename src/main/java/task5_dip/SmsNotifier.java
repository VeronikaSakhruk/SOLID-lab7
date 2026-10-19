package task5_dip;

public interface SmsNotifier {

    void sendSmsReceipt(String phone, String transactionId, double amount);
}
