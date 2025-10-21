package ch.noseryoung.blj.exceptions.loanExceptions;

public class TooLongInquiryException extends LoanErrorException {
    public TooLongInquiryException() {
        super("Your Inquiry impedes the maximum loan period of the certain media.");
    }

}
