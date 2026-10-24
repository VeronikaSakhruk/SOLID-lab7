package task5_dip;

public class StripePaymentGateway implements PaymentGateway {

    @Override
    public boolean executeCharge(String cardNumber, double amount) {
        System.out.println("[Stripe API] Charged " + amount + " UAH from card " + cardNumber + ".");
        return true;
    }
}
