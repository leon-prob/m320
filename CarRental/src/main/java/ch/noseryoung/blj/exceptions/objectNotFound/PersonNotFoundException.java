package ch.noseryoung.blj.exceptions.objectNotFound;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("The person with given UUID was not found.");
    }
}
