package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.exceptions.objectNotFound.GarageNotFoundException;
import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanService;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.car.CarService;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.login.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Component
@Primary
@Log4j2

public class CarGarageServiceImpl implements CarGarageService{

    private final CarGarageRepository carGarageRepository;

    private final LoanService loanService;

    private final UserService personService;

    private final CarService carService;
    private final CarGarage carGarage;


    public CarGarageServiceImpl(CarGarageRepository carGarageRepository, LoanService loanService, UserService personService, CarService carService, CarGarage carGarage) {
        this.carGarageRepository = carGarageRepository;
        this.loanService = loanService;
        this.personService = personService;
        this.carService = carService;
        this.carGarage = carGarage;
    }

    @Override
    public List<CarGarage> getCarGarages() {
        return carGarageRepository.findAll();
    }

    @Override
    public CarGarage addGarage() {
        return carGarageRepository.save(new CarGarage());
    }

    @Override
    public Set<User> getAllCustomers(long library_id) {
        CarGarage garage = carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new);
        return (Set<User>) garage.getPersonSet().stream().filter(user -> !user.isBanned());
    }

    @Override
    public List<Car> getCarCollection(long library_id) {
        return carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new).getCars();
    }

    @Override
    public Set<User> getAllBannedPeople(long library_id) {
        CarGarage garage = carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new);
        return (Set<User>) garage.getPersonSet().stream().filter(user -> user.isBanned());
    }

    @Override
    public List<LoanContract> getAllContracts(long library_id) {
        return carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new).getContracts();
    }

    @Override
    public Car getCarById(long garage_id, UUID carId) {
        return carService.getCarById(carId);
    }

    @Override
    public LoanContract createContract(long library_id, UUID personId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status) throws Exception {
        carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new).getContracts().add(loanService.createLoan(personId, mediaId, startDate, endDate, status));
        return carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new).getContracts().getLast();
    }

    @Override
    public Car createCar(long library_id, Car media) {
        CarGarage carGarage = carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new);
        List<Car> mediaCollection = carGarage.getCars();
        mediaCollection.add(carService.createCar(media));
        return carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new).getCars().getLast();
    }
    @Override
    public Car updateCar(long garageID, UUID carId, Car newCar){
        getCarCollection(garageID).add(carService.updateCar(carId, newCar));
        return carService.updateCar(carId, newCar);
    }

    @Override
    public void deleteLoan(long library_id, UUID id) {
        carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new)
                .getContracts().removeIf(loanContract -> loanContract.getId() == id);
    }

    @Override
    public void deleteCar(long library_id, UUID id) {
        carGarageRepository.findById(library_id).orElseThrow(GarageNotFoundException::new)
                .getCars().removeIf(media -> media.getId() == id);
    }
}
