package ch.tbz.leon_michel.flights;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private final List<Passenger> passengers;
    private LocalDateTime departure;
    private LocalDateTime arrival;

    public Flight(LocalDateTime departure, LocalDateTime arrival) {
        passengers = new ArrayList<>();
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
