package ch.noseryoung.blj.exceptions.loanExceptions;

public class PersonTooYoungToLoan extends LoanErrorException {
    public PersonTooYoungToLoan() {
        super("Person is too young to loan certain media.");
    }
}
