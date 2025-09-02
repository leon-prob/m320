package ch.tbz.leon_michel.vehicle.exceptions;

public class MinorAgeException extends RuntimeException {
    public MinorAgeException() {
        super("Customer is too young");
    }
}
