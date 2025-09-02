package ch.tbz.leon_michel.flights;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<Flight> flights;

    public Schedule() {
        flights = new ArrayList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<LocalDateTime> getDepartures(LocalDate date){
        List<LocalDateTime> departures = new ArrayList<>();
        flights.forEach(flight ->  {
            if(flight.getDeparture().toLocalDate().equals(date)){
                departures.add(flight.getDeparture());
            }
        });
        return departures;
    }

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
