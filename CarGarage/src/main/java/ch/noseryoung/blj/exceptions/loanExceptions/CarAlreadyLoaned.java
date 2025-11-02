package ch.noseryoung.blj.exceptions.loanExceptions;

public class CarAlreadyLoaned extends LoanErrorException {
    public CarAlreadyLoaned() {
        super("This is already loaned in given time period");
    }
    public CarAlreadyLoaned(String message) {
        super(message);
    }
}
