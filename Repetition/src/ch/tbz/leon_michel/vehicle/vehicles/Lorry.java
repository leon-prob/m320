package ch.tbz.leon_michel.vehicle.vehicles;

import ch.tbz.leon_michel.vehicle.Vehicle;

public class Lorry extends Vehicle {
    private double maxAmountOfLoadWeightInTons;

    public Lorry(int requiredAge, int maxLeaseLengthInDays, double price, String model, String brand, int seatCount,
                 String numberPlate, String insuranceCompany, String vin, double maxAmountOfLoadWeightInTons) {
        super(requiredAge, maxLeaseLengthInDays, price, model, brand, seatCount, numberPlate, insuranceCompany, vin);
        this.maxAmountOfLoadWeightInTons = maxAmountOfLoadWeightInTons;
    }

    public double getMaxAmountOfLoadWeightInTons(){return maxAmountOfLoadWeightInTons;}
    public void setMaxAmountOfLoadWeightInTons(double maxAmountOfLoadWeightInTons){
        this.maxAmountOfLoadWeightInTons = maxAmountOfLoadWeightInTons;
    }
}
