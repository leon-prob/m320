package ch.tbz.leon_michel.flights;

import java.util.UUID;

/**
 * Represents a passenger with a unique identifier and personal information.
 * The passenger's ID is automatically generated upon creation.
 */
public class Passenger {
    private final UUID id;
    private String firstName;
    private String lastName;


    /**
     * Constructs a new Passenger with a given first and last name.
     * A unique ID is automatically assigned.
     *
     * @param firstName The first name of the passenger.
     * @param lastName  The last name of the passenger.
     */
    public Passenger(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}