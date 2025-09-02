package ch.tbz.leon_michel.vehicle.vehicles;

import ch.tbz.leon_michel.vehicle.Vehicle;

public class Trailer extends Vehicle {
    private double maxAmountOfLoadWeightInTons;
    private boolean hasRoof;

    public Trailer(int requiredAge, int maxLeaseLengthInDays, double price, String model, String brand, int seatCount,
                 String numberPlate, String insuranceCompany, String vin, double maxAmountOfLoadWeightInTons, boolean hasRoof) {
        super(requiredAge, maxLeaseLengthInDays, price, model, brand, seatCount, numberPlate, insuranceCompany, vin);
        this.maxAmountOfLoadWeightInTons = maxAmountOfLoadWeightInTons;
        this.hasRoof = hasRoof;
    }

    public double getMaxAmountOfLoadWeightInTons(){return maxAmountOfLoadWeightInTons;}
    public void setMaxAmountOfLoadWeightInTons(double maxAmountOfLoadWeightInTons){
        this.maxAmountOfLoadWeightInTons = maxAmountOfLoadWeightInTons;
    }
    public boolean hasRoof(){return hasRoof;}
    public void setHasRoof(boolean hasRoof){this.hasRoof = hasRoof;}

}
