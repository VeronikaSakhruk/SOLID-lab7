package task5_dip;

public class SqlServerTransactionLedger implements TransactionLedger {

    @Override
    public void recordTransaction(String transactionId, double amount, String status) {
        System.out.println("[SQL Server Ledger] Recorded transaction " + transactionId + ": amount=" + amount + ", status=" + status + ".");
    }
}
