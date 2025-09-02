package ch.tbz.leon_michel.vehicle;


public class Vehicle {
    private int requiredAge;
    private int maxLeaseLengthInDays;
    private double price;
    private String model;
    private String brand;
    private int seatCount;
    private String numberPlate;
    private String insuranceCompany;
    private final String vin;

    public Vehicle (int requiredAge, int maxLeaseLengthInDays, double price, String model, String brand, int seatCount,
    String numberPlate, String insuranceCompany, String vin){
        this.requiredAge = requiredAge;
        this.maxLeaseLengthInDays = maxLeaseLengthInDays;
        this.price = price;
        this.model = model;
        this.brand = brand;
        this.seatCount = seatCount;
        this.numberPlate = numberPlate;
        this.insuranceCompany = insuranceCompany;
        this.vin = vin;
    }

    public int getRequiredAge(){return requiredAge;}
    public int getMaxLeaseLengthInDays(){return maxLeaseLengthInDays;}
    public double getPrice(){return price;}
    public String getModel(){return model;}
    public String getBrand(){return brand;}
    public int getSeatCount(){return seatCount;}
    public String getNumberPlate(){return numberPlate;}
    public String getInsuranceCompany(){return insuranceCompany;}
    public String getVin(){return vin;}

    public void setRequiredAge(int requiredAge){this.requiredAge = requiredAge;}
    public void setMaxLeaseLengthInDays(int maxLeaseLengthInDays){this.maxLeaseLengthInDays = maxLeaseLengthInDays;}
    public void setPrice(double price){this.price = price;}
    public void setModel(String model){this.model = model;}
    public void setBrand(String brand){this.brand = brand;}
    public void setSeatCount(int seatCount){this.seatCount = seatCount;}
    public void setNumberPlate(String numberPlate){this.numberPlate = numberPlate;}
    public void setInsuranceCompany(String insuranceCompany){this.insuranceCompany = insuranceCompany;}

}
