package task5_dip;

public interface PaymentGateway {

    boolean executeCharge(String cardNumber, double amount);
}
