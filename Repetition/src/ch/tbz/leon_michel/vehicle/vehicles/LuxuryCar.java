package ch.tbz.leon_michel.vehicle.vehicles;

import ch.tbz.leon_michel.vehicle.Vehicle;

public class LuxuryCar extends Vehicle {
    private boolean isLimited;
    private int numberOfMassageSeats;

    public LuxuryCar(int requiredAge, int maxLeaseLengthInDays, double price, String model, String brand, int seatCount,
                     String numberPlate, String insuranceCompany, String vin, boolean isLimited, int numberOfMassageSeats) {
        super(requiredAge, maxLeaseLengthInDays, price, model, brand, seatCount, numberPlate, insuranceCompany, vin);
        this.isLimited = isLimited;
        this.numberOfMassageSeats = numberOfMassageSeats;
    }

    public boolean isLimited(){return isLimited;}
    public int getNumberOfMassageSeats(){return  numberOfMassageSeats;}
    public void setLimited(boolean isLimited){
        this.isLimited = isLimited;
    }
    public void setNumberOfMassageSeats(int numberOfMassageSeats){
        this.numberOfMassageSeats = numberOfMassageSeats;
    }
}
