package ch.noseryoung.blj.exceptions.objectNotFound;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super("The car with given UUID was not found.");
    }
}
