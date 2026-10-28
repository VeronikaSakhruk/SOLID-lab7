package parcelplus;

public class Stop {

    private final String name;
    private final double tariff;
    private final String warehouseContact;

    public Stop(String name, double tariff, String warehouseContact) {
        this.name = name;
        this.tariff = tariff;
        this.warehouseContact = warehouseContact;
    }

    public Stop copy() {
        return new Stop(name, tariff, warehouseContact);
    }

    @Override
    public String toString() {
        return name + "(tariff=" + tariff + ", contact=" + warehouseContact + ")";
    }
}
