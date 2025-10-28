package ch.noseryoung.blj.exceptions.loanExceptions;

public class BannedUserException extends LoanErrorException {
    public BannedUserException() {
        super("This User is Banned");
    }
}
