package ch.tbz.leon_michel.vehicle;

import java.time.LocalDate;
import java.time.Period;

public class Contract {
    private final Person person;
    private final Vehicle vehicle;
    private final int durationInDays;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Contract (Person person, Vehicle vehicle, LocalDate startDate, LocalDate endDate){
        this.person = person;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationInDays = Period.between(startDate, endDate).getDays();
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Person getPerson() {
        return person;
    }
}
