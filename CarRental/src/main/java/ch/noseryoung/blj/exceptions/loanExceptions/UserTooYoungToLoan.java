package ch.noseryoung.blj.exceptions.loanExceptions;

public class UserTooYoungToLoan extends LoanErrorException {
    public UserTooYoungToLoan() {
        super("User is too young to loan certain media.");
    }
}
