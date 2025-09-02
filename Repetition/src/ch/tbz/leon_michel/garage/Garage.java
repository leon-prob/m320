package ch.tbz.leon_michel.garage;

import java.util.*;

public class Garage {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public void add(Vehicle v) { vehicles.add(v); }

    public Vehicle findById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    public List<Vehicle> list() {
        return Collections.unmodifiableList(vehicles);
    }

    public List<Vehicle> repaired() {
        List<Vehicle> out = new ArrayList<>();
        for (Vehicle v : vehicles) if (v.isRepaired()) out.add(v);
        return out;
    }

    public double totalRepairCosts() {
        double sum = 0;
        for (Vehicle v : vehicles) if (v.isRepaired()) sum += v.getLastRepairCost();
        return sum;
    }
}
