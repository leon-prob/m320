package ch.tbz.leon_michel.flights;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of flights in a schedule.
 * This class provides methods to store and retrieve flight information,
 * including filtering flights by departure or arrival date.
 */
public class Schedule {

    private final List<Flight> flights;

    /**
     * Constructs a new Schedule instance with an empty list of flights.
     */
    public Schedule() {
        flights = new ArrayList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * Gets a list of departure times for all flights scheduled on a specific date.
     *
     * @param date The {@link LocalDate} to filter departures by.
     * @return A {@link List} of {@link LocalDateTime} objects representing the departure times on the specified date.
     */
    public List<LocalDateTime> getDepartures(LocalDate date){
        List<LocalDateTime> departures = new ArrayList<>();
        flights.forEach(flight ->  {
            if(flight.getDeparture().toLocalDate().equals(date)){
                departures.add(flight.getDeparture());
            }
        });
        return departures;
    }

    /**
     * Gets a list of arrival times for all flights scheduled on a specific date.
     *
     * @param date The {@link LocalDate} to filter arrivals by.
     * @return A {@link List} of {@link LocalDateTime} objects representing the arrival times on the specified date.
     */
    public List<LocalDateTime> getArrivals(LocalDate date){
        List<LocalDateTime> arrivals = new ArrayList<>();
        flights.forEach(flight ->  {
            if(flight.getArrival().toLocalDate().equals(date)){
                arrivals.add(flight.getArrival());
            }
        });
        return arrivals;
    }
}