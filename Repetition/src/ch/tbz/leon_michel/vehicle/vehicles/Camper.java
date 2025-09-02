package ch.tbz.leon_michel.vehicle.vehicles;

import ch.tbz.leon_michel.vehicle.Vehicle;

public class Camper extends Vehicle {
    private int sleepPlaces;
    public Camper(int requiredAge, int maxLeaseLengthInDays, double price, String model, String brand, int seatCount,
                  String numberPlate, String insuranceCompany, String vin, int sleepPlaces) {
        super(requiredAge, maxLeaseLengthInDays, price, model, brand, seatCount, numberPlate, insuranceCompany, vin);
        this.sleepPlaces = sleepPlaces;
    }
    public int getSleepPlaces(){return sleepPlaces;}
    public void setSleepPlaces(int sleepPlaces){this.sleepPlaces = sleepPlaces;}
}
