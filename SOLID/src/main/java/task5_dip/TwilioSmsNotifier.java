package task5_dip;

public class TwilioSmsNotifier implements SmsNotifier {

    @Override
    public void sendSmsReceipt(String phone, String transactionId, double amount) {
        System.out.println("[Twilio SMS] Message to " + phone + ": Payment " + transactionId + " for " + amount + " UAH completed.");
    }
}
