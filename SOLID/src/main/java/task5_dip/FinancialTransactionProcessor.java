package task5_dip;

import java.util.Objects;

public class FinancialTransactionProcessor {

    private final PaymentGateway gateway;
    private final TransactionLedger ledger;
    private final SmsNotifier smsNotifier;

    public FinancialTransactionProcessor(PaymentGateway gateway, TransactionLedger ledger, SmsNotifier smsNotifier) {
        this.gateway = Objects.requireNonNull(gateway);
        this.ledger = Objects.requireNonNull(ledger);
        this.smsNotifier = Objects.requireNonNull(smsNotifier);
    }

    public void processPayment(PaymentRequest request) {
        if (request == null || request.amount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
        boolean success = gateway.executeCharge(request.cardNumber(), request.amount());
        String status = success ? "SUCCESS" : "FAILED";
        ledger.recordTransaction(request.transactionId(), request.amount(), status);
        smsNotifier.sendSmsReceipt(request.payerPhone(), request.transactionId(), request.amount());
    }
}
