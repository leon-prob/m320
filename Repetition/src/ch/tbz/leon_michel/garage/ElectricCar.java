package ch.tbz.leon_michel.garage;

public class ElectricCar extends Vehicle {
    public ElectricCar(String id, String model, double baseLaborHours) {
        super(id, model, baseLaborHours);
    }
    @Override
    public double calculateRepairCost() {
        return hours() * 55 + 150;
    }
}
