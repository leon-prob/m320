package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.car.Car;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

public interface CarGarageService {
    List<Long> getCarGarages();

    CarGarage addGarage();

    Set<User> getAllCustomers(long garage_id);

    List<Car> getCarList(long garage_id);

    Set<User> getAllBannedPeople(long garage_id);

    List<LoanContract> getAllContracts(long garage_id);

    List<LoanContract> getAllContractsOfAPerson(long garage_id, UUID userId);

    LoanContract getContractById(long garage_id, UUID contractId);

    LoanContract createContract(long garage_id, UUID personId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status) throws Exception;

    Car createCar(long garage_id, Car media);

    User addUserToUserList(Long garageId, UUID userId);

    void deleteLoan(long garage_id, UUID id);

    void deleteCar(long garage_id, UUID id);

    Car updateCar(UUID mediaId, @Valid Car updatedCar);

    LoanContract updateContract(UUID loanId, UUID personId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status);

    Car getCarById(long garageId, UUID mediaId);
}
