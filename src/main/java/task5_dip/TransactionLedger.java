package task5_dip;

public interface TransactionLedger {

    void recordTransaction(String transactionId, double amount, String status);
}
