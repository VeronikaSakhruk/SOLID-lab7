package parcelplus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Daily delivery routes (Prototype) ===");
        demonstrateRoutes();
        Log.log("Main", "=== Task 2: Service vans (Object Pool) ===");
        demonstrateVans();
        Log.log("Main", "=== Task 3: Waybill numbering (Singleton) ===");
        demonstrateWaybills();
    }

    private static void demonstrateRoutes() {
        Route reference = Route.buildReference("Chernivtsi - Khotyn - Kelmentsi");
        Route day1 = reference.copy("2026-10-29");
        Route day2 = reference.copy("2026-10-30");
        Route day3 = reference.copy("2026-10-31");

        day2.setCourier("Ivanenko");
        day2.addStop(new Stop("Sokyriany", 2.1, "+380501000004"));
        day2.setRestriction("maxParcelWeightKg", 15);

        Log.log("Main", "Reference: " + reference.describe());
        Log.log("Main", "Modified : " + day2.describe());
        Log.log("Main", "Day 1    : " + day1.describe());
        Log.log("Main", "Day 3    : " + day3.describe());
    }

    private static void demonstrateVans() {
        VanPool pool = new VanPool(3);
        String[] couriers = {"Ivanenko", "Petrenko", "Shevchenko", "Kovalenko", "Bondarenko"};
        Van[] vans = new Van[couriers.length];

        for (int i = 0; i < couriers.length; i++) {
            vans[i] = pool.acquire(couriers[i]);
        }

        vans[0].load("PS-1001");
        vans[0].load("PS-1002");
        vans[1].load("PS-1003");
        vans[2].load("PS-1004");

        vans[3] = pool.release(vans[0]);
        Log.log("Main", "Van " + vans[3].getId() + " courier: " + vans[3].getCourier()
                + ", parcels: " + vans[3].getParcels());

        vans[4] = pool.release(vans[1]);
        Log.log("Main", "Van " + vans[4].getId() + " courier: " + vans[4].getCourier()
                + ", parcels: " + vans[4].getParcels());

        pool.release(vans[2]);
        pool.release(vans[3]);
        pool.release(vans[4]);

        pool.printStatistics();
    }

    private static void demonstrateWaybills() {
        String[] sources = {"Branch #1", "Branch #2", "Online cabinet"};
        List<Long> numbers = new ArrayList<>();
        Set<String> serviceIds = new HashSet<>();

        for (int round = 1; round <= 3; round++) {
            for (String source : sources) {
                WaybillService service = WaybillService.getInstance();
                long number = service.nextNumber();
                numbers.add(number);
                serviceIds.add(service.getId());
                Log.log(source, "Shipment registered, waybill " + number + ", service id " + service.getId());
            }
        }

        boolean sequential = true;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) != numbers.get(i - 1) + 1) {
                sequential = false;
            }
        }
        Log.log("Main", numbers.size() + " waybills issued, distinct: " + new HashSet<>(numbers).size()
                + ", sequential: " + sequential);
        Log.log("Main", "Distinct service ids seen from all places: " + serviceIds.size());
    }
}
