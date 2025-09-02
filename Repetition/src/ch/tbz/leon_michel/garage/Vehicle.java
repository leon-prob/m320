package ch.tbz.leon_michel.garage;

public abstract class Vehicle {
    private final String id;
    private final String model;
    private final double baseLaborHours;

    private boolean repaired;
    private Double lastRepairCost;

    public Vehicle(String id, String model, double baseLaborHours) {
        this.id = id;
        this.model = model;
        this.baseLaborHours = baseLaborHours;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public Double getLastRepairCost() {
        return lastRepairCost;
    }

    protected double hours() {
        return baseLaborHours;
    }

    public double calculateRepairCost() {
        return hours() * 50 + 20;
    }

    public double calculateRepairCost(double discount) {
        double cost = calculateRepairCost();
        return cost * (1.0 - discount);
    }

    public double repair() {
        double cost = calculateRepairCost();
        repaired = true;
        lastRepairCost = cost;
        return cost;
    }

    public double repair(double discount) {
        double cost = calculateRepairCost(discount);
        repaired = true;
        lastRepairCost = cost;
        return cost;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + id + ", " + model +
                ", hours=" + hours() + ", repaired=" + repaired + ")";
    }
}
