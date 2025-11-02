package ch.noseryoung.blj.exceptions.objectNotFound;

import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(UUID uuid) {
        super("The User with given UUID was not found: " + uuid);
    }
    public PersonNotFoundException(String mail) {
        super("The User with given email-address was not found: " + mail);
    }
}
