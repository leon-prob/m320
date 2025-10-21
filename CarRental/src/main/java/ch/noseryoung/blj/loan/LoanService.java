package ch.noseryoung.blj.loan;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface LoanService {
    List<LoanContract> getAllLoans();

    LoanContract getLoanById(UUID id);

    LoanContract createLoan(LoanContract loanContract);

    LoanContract createLoan(UUID personId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status) throws Exception;

    LoanContract updateLoan(UUID id, LoanContract loanContract);

    void deleteLoanById(UUID id);
}
