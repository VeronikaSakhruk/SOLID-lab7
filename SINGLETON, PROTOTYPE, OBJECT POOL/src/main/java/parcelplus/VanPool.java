package parcelplus;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class VanPool {

    private final List<Van> allVans = new ArrayList<>();
    private final Deque<Van> freeVans = new ArrayDeque<>();
    private final Queue<String> waitingCouriers = new ArrayDeque<>();

    public VanPool(int size) {
        for (int i = 1; i <= size; i++) {
            Van van = new Van("F-" + i);
            allVans.add(van);
            freeVans.add(van);
        }
        Log.log("VanPool", "Pool created with " + size + " vans");
    }

    public synchronized Van acquire(String courier) {
        Van van = freeVans.poll();
        if (van == null) {
            waitingCouriers.add(courier);
            Log.log("VanPool", "No free vans, courier " + courier + " queued, position " + waitingCouriers.size());
            return null;
        }
        van.assign(courier);
        Log.log("VanPool", "Van " + van.getId() + " -> courier " + courier);
        return van;
    }

    public synchronized Van release(Van van) {
        if (van.getCourier() == null) {
            Log.log("VanPool", "Van " + van.getId() + " is already in the pool, release ignored");
            return null;
        }
        van.reset();
        Log.log("VanPool", van.getId() + " returned, unloaded");
        String next = waitingCouriers.poll();
        if (next == null) {
            freeVans.add(van);
            return null;
        }
        van.assign(next);
        Log.log("VanPool", "Van " + van.getId() + " -> courier " + next + " from queue, no preparation needed");
        return van;
    }

    public void printStatistics() {
        for (Van van : allVans) {
            Log.log("VanPool", "Statistics: van " + van.getId() + " made " + van.getTrips() + " trips");
        }
        Log.log("VanPool", "Statistics: total vans created " + allVans.size());
    }
}
