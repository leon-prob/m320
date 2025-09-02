package ch.tbz.leon_michel.flights;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flight with a specific departure and arrival time.
 * This class also manages a list of passengers registered for the flight.
 */
public class Flight {
    private final List<Passenger> passengers;
    private LocalDateTime departure;
    private LocalDateTime arrival;

    /**
     * Constructs a new Flight instance with specified departure and arrival times.
     * Initializes an empty list to hold passengers.
     *
     * @param departure The {@link LocalDateTime} when the flight is scheduled to depart.
     * @param arrival   The {@link LocalDateTime} when the flight is scheduled to arrive.
     */
    public Flight(LocalDateTime departure, LocalDateTime arrival) {
        this.passengers = new ArrayList<>();
        this.departure = departure;
        this.arrival = arrival;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }
}