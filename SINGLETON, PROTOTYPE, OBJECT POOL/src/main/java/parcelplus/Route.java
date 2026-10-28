package parcelplus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Route {

    private final String name;
    private final String date;
    private final String schedule;
    private String courier;
    private final List<Stop> stops;
    private final Map<String, Integer> restrictions;

    private Route(String name, String date, String schedule, String courier,
                  List<Stop> stops, Map<String, Integer> restrictions) {
        this.name = name;
        this.date = date;
        this.schedule = schedule;
        this.courier = courier;
        this.stops = stops;
        this.restrictions = restrictions;
    }

    public static Route buildReference(String name) {
        Log.work("Route", "Building reference route '" + name
                + "': stops, schedule, tariffs, weight limits, warehouse contacts (expensive)", 500);
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("Chernivtsi", 1.0, "+380501000001"));
        stops.add(new Stop("Khotyn", 1.4, "+380501000002"));
        stops.add(new Stop("Kelmentsi", 1.8, "+380501000003"));
        Map<String, Integer> restrictions = new LinkedHashMap<>();
        restrictions.put("maxParcelWeightKg", 30);
        restrictions.put("maxTotalWeightKg", 500);
        Route route = new Route(name, "reference", "08:00-18:00", "unassigned", stops, restrictions);
        Log.log("Route", "Reference route '" + name + "' ready");
        return route;
    }

    public Route copy(String date) {
        List<Stop> stopsCopy = new ArrayList<>();
        for (Stop stop : stops) {
            stopsCopy.add(stop.copy());
        }
        Route route = new Route(name, date, schedule, courier, stopsCopy, new LinkedHashMap<>(restrictions));
        Log.log("Route", "Route for " + date + " copied from '" + name + "' without rebuilding");
        return route;
    }

    public void setCourier(String courier) {
        this.courier = courier;
        Log.log("Route " + date, "Courier changed to " + courier);
    }

    public void addStop(Stop stop) {
        stops.add(stop);
        Log.log("Route " + date, "Stop added: " + stop);
    }

    public void setRestriction(String key, int value) {
        restrictions.put(key, value);
        Log.log("Route " + date, "Restriction " + key + " set to " + value);
    }

    public String describe() {
        return "Route{name=" + name + ", date=" + date + ", schedule=" + schedule + ", courier=" + courier
                + ", stops=" + stops + ", restrictions=" + restrictions + "}";
    }
}
