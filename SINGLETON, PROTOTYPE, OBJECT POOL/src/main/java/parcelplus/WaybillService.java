package parcelplus;

import java.util.UUID;

public final class WaybillService {

    private static WaybillService instance;

    private final String id;
    private long lastNumber = 1000000;

    private WaybillService() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        Log.log("WaybillService", "Numbering service initialized, id " + id);
    }

    public static synchronized WaybillService getInstance() {
        if (instance == null) {
            instance = new WaybillService();
        }
        return instance;
    }

    public synchronized long nextNumber() {
        return ++lastNumber;
    }

    public String getId() {
        return id;
    }
}
