package task5_dip;

public class Main {

    public static void main(String[] args) {
        PaymentRequest request = new PaymentRequest("TX-1001", "4111111111111111", 250.50, "+380501234567");

        FinancialTransactionProcessor production = new FinancialTransactionProcessor(
                new StripePaymentGateway(),
                new SqlServerTransactionLedger(),
                new TwilioSmsNotifier());
        production.processPayment(request);

        InMemoryTransactionLedger ledger = new InMemoryTransactionLedger();
        MockSmsNotifier notifier = new MockSmsNotifier();
        FinancialTransactionProcessor test = new FinancialTransactionProcessor(
                new MockPaymentGateway(false),
                ledger,
                notifier);
        test.processPayment(request);

        System.out.println("Mock ledger: " + ledger.getRecords());
        System.out.println("Mock SMS: " + notifier.getSentMessages());
    }
}
