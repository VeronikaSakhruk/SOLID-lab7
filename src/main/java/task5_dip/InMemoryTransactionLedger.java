package task5_dip;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionLedger implements TransactionLedger {

    private final List<String> records = new ArrayList<>();

    @Override
    public void recordTransaction(String transactionId, double amount, String status) {
        records.add(transactionId + ":" + amount + ":" + status);
    }

    public List<String> getRecords() {
        return records;
    }
}
