package ch.tbz.leon_michel.vehicle.exceptions;

public class DenyListedPersonException extends RuntimeException {
    public DenyListedPersonException() {
        super("This customer is on the deny list.");
    }
}
