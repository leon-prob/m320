package ch.tbz.leon_michel.vehicle.exceptions;

public class LeaseLengthCollisionException extends RuntimeException {
    public LeaseLengthCollisionException(int maxLeaseDays) {
        super("You can only rent this vehicle for " + maxLeaseDays + " days.");
    }
}
