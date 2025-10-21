package ch.noseryoung.blj.exceptions.loanExceptions;

public class InvalidDateException extends LoanErrorException {
  public InvalidDateException() {
    super("The end date is before the start date");
  }

}
