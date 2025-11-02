package ch.noseryoung.blj.exceptions.loanExceptions;

public class LoanErrorException extends RuntimeException {
    public LoanErrorException(String message) {
        super(message);
    }
    public LoanErrorException() {
    super("This Loan can't get a contract");
  }

}
