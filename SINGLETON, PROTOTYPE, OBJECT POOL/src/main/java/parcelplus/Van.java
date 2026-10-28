package parcelplus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Van {

    private final String id;
    private String courier;
    private final List<String> parcels = new ArrayList<>();
    private int trips;

    Van(String id) {
        this.id = id;
        Log.work("Van " + id, "Preparing van: inspection, refueling, waybill (expensive)", 300);
        Log.log("Van " + id, "Ready for service");
    }

    public String getId() {
        return id;
    }

    public String getCourier() {
        return courier;
    }

    public List<String> getParcels() {
        return Collections.unmodifiableList(parcels);
    }

    public int getTrips() {
        return trips;
    }

    public void load(String parcel) {
        parcels.add(parcel);
        Log.log("Van " + id, "Parcel " + parcel + " loaded");
    }

    void assign(String courier) {
        this.courier = courier;
        trips++;
    }

    void reset() {
        courier = null;
        parcels.clear();
    }
}
