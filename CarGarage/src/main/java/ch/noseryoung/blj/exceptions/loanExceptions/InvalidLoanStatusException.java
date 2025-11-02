package ch.noseryoung.blj.exceptions.loanExceptions;

public class InvalidLoanStatusException extends LoanErrorException {
    public InvalidLoanStatusException() {
        super("The given loan status is invalid with the dates.");
    }
}
