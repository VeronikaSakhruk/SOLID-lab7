package task5_dip;

public class MockPaymentGateway implements PaymentGateway {

    private final boolean result;

    public MockPaymentGateway(boolean result) {
        this.result = result;
    }

    @Override
    public boolean executeCharge(String cardNumber, double amount) {
        return result;
    }
}
