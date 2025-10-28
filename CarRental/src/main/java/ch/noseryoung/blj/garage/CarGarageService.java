package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.login.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

public interface CarGarageService {
    List<CarGarage> getCarGarages();

    CarGarage addGarage();

    Set<User> getAllCustomers(long garage_id);
    List<Car> getCarCollection(long garage_id);
    Set<User> getAllBannedPeople(long garage_id);
    List<LoanContract> getAllContracts(long garage_id);
    Car getCarById(long garage_id, UUID carId);

    LoanContract createContract(long garage_id, UUID personId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status) throws Exception;

    Car createCar(long garage_id, Car media);

    Car updateCar(long garageId, UUID carId, Car newCar);

    void deleteLoan(long garage_id, UUID id);

    void deleteCar(long garage_id, UUID id);

}
