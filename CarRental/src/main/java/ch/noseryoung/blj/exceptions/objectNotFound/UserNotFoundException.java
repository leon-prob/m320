package ch.noseryoung.blj.exceptions.objectNotFound;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The user with given UUID was not found.");
    }
}
