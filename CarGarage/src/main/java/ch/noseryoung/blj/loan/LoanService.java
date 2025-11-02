package ch.noseryoung.blj.loan;

import ch.noseryoung.blj.garage.CarGarage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface LoanService {
    List<LoanContract> getAllLoans();

    List<LoanContract> getLoansByUser(UUID userId);

    LoanContract getLoanById(UUID id);

    LoanContract createLoan(LoanContract loanContract);

    LoanContract createLoan(UUID personId, UUID carId, LocalDate startDate, LocalDate endDate, LoanStatus status, CarGarage garage) throws Exception;

    LoanContract updateLoan(UUID id, LoanContract loanContract);

    void deleteLoanById(UUID id);
}
