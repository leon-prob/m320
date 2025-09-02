package ch.tbz.leon_michel.vehicle.vehicles;

import ch.tbz.leon_michel.vehicle.Vehicle;

public class Combi extends Vehicle {
    private boolean hasIntegratedGPS;
    
    public Combi(int requiredAge, int maxLeaseLengthInDays, double price, String model, String brand, int seatCount, String numberPlate, String insuranceCompany, String vin, boolean hasIntegratedGPS) {
        super(requiredAge, maxLeaseLengthInDays, price, model, brand, seatCount, numberPlate, insuranceCompany, vin);
        this.hasIntegratedGPS = hasIntegratedGPS;
    }

    public boolean hasIntegratedGPS(){return hasIntegratedGPS;}
    public void setHasIntegratedGPS(boolean hasIntegratedGPS){this.hasIntegratedGPS = hasIntegratedGPS;}
}
