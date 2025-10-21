package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.person.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

public interface CarGarageService {
    List<CarGarage> getCarGarages();

    CarGarage addGarage();

    Set<Person> getAllCustomers(long library_id);
    List<Car> getCarCollection(long library_id);
    Set<Person> getAllBannedPeople(long library_id);
    List<LoanContract> getAllContracts(long library_id);

    Person createPerson(long library_id, Person person);

    LoanContract createContract(long library_id, UUID personId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status) throws Exception;

    Car createCar(long library_id, Car media);

    void deleteLoan(long library_id, UUID id);

    void deleteCar(long library_id, UUID id);

    void deletePerson(long library_id, UUID id);

}
