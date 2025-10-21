package ch.noseryoung.blj.exceptions.objectNotFound;

public class GarageNotFoundException extends ObjectNotFoundException {
    public GarageNotFoundException() {
        super("The library id is incorrect");
    }
}
