package ch.noseryoung.blj.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<LoanContract, UUID> {
    List<LoanContract> findDistinctByCustomer_Id(UUID id);

    @Modifying
    @Query("DELETE FROM LoanContract lc WHERE lc.id = :id")
    void deleteByIdCustom(@Param("id") UUID id);
}
