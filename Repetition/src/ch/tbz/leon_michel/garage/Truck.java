package ch.tbz.leon_michel.garage;

public class Truck extends Vehicle {
    public Truck(String id, String model, double baseLaborHours) {
        super(id, model, baseLaborHours);
    }
    @Override
    public double calculateRepairCost() {
        return hours() * 65 + 200;
    }
}
