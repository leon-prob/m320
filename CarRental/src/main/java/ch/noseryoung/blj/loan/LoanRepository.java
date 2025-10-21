package ch.noseryoung.blj.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<LoanContract, UUID> {
}
