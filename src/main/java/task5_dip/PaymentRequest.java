package task5_dip;

public record PaymentRequest(String transactionId, String cardNumber, double amount, String payerPhone) {
}
