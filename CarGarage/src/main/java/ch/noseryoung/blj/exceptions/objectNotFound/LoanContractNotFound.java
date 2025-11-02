package ch.noseryoung.blj.exceptions.objectNotFound;

public class LoanContractNotFound extends ObjectNotFoundException {
    public LoanContractNotFound() {
        super("The loan with given UUID was not found.");
    }
}
