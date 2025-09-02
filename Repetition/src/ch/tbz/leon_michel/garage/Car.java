package ch.tbz.leon_michel.garage;

public class Car extends Vehicle {
    public Car(String id, String model, double baseLaborHours) {
        super(id, model, baseLaborHours);
    }
    @Override
    public double calculateRepairCost() {
        return hours() * 50 + 100;
    }
}
