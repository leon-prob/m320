package ch.noseryoung.blj.exceptions.loanExceptions;

public class BannedPersonException extends LoanErrorException {
    public BannedPersonException() {
        super("This Person is Banned");
    }
}
