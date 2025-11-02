package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.exceptions.objectNotFound.GarageNotFoundException;
import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanService;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.login.user.UserService;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.car.CarService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Component
@Primary
@Log4j2

public class CarGarageServiceImpl implements CarGarageService {

    private final CarGarageRepository carGarageRepository;

    private final LoanService loanService;

    private final UserService userService;

    private final CarService carService;



    public CarGarageServiceImpl(CarGarageRepository carGarageRepository, LoanService loanService, UserService userService, CarService carService) {
        this.carGarageRepository = carGarageRepository;
        this.loanService = loanService;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public List<Long> getCarGarages() {
        List<Long> idList = new ArrayList<>();
        carGarageRepository.findAll().forEach(carGarage -> idList.add(carGarage.getId()));
        return idList;
    }

    @Override
    public CarGarage addGarage() {
        return carGarageRepository.save(new CarGarage());
    }

    @Override
    public Set<User> getAllCustomers(long garage_id) {
        return carGarageRepository.findById(garage_id).orElseThrow(GarageNotFoundException::new)
                .getUserList().stream().filter(user -> !user.isBanned()).collect(Collectors.toSet());
    }

    @Override
    public List<Car> getCarList(long garage_id) {
        CarGarage carGarage = carGarageRepository.findById(garage_id)
                .orElseThrow(GarageNotFoundException::new);
        return new ArrayList<>(carGarage.getCarList());
    }

    @Override
    public Set<User> getAllBannedPeople(long garage_id) {
        return carGarageRepository.findById(garage_id).orElseThrow(GarageNotFoundException::new)
                .getUserList().stream().filter(User::isBanned).collect(Collectors.toSet());
    }

    @Override
    public List<LoanContract> getAllContracts(long garage_id) {
        return loanService.getAllLoans();
    }

    @Override
    public List<LoanContract> getAllContractsOfAPerson(long garage_id, UUID userId) {
        return loanService.getLoansByUser(userId);
    }

    @Override
    public LoanContract getContractById(long garage_id, UUID contractId) {
        return loanService.getLoanById(contractId);
    }

    @Override
    public LoanContract createContract(long garage_id, UUID personId, UUID carId, LocalDate startDate, LocalDate endDate, LoanStatus status) throws Exception {
        CarGarage garage = carGarageRepository.findById(garage_id).orElseThrow(GarageNotFoundException::new);
        garage.getContracts().add(loanService.createLoan(personId, carId, startDate, endDate, status, garage));
        return garage.getContracts().getLast();
    }

    @Override
    public Car createCar(long garage_id, Car car) {
        CarGarage garage = carGarageRepository.findById(garage_id).orElseThrow(GarageNotFoundException::new);
        garage.getCarList().add(car);
        return carService.createCar(car);
    }

    @Override
    public User addUserToUserList(Long garageId, UUID userId) {
        User user = userService.getUserById(userId);
        CarGarage garage = carGarageRepository.findById(garageId).orElseThrow(GarageNotFoundException::new);
        garage.getUserList().add(user);
        return user;
    }

    @Override
    public void deleteLoan(long garage_id, UUID id) {
        carGarageRepository.findById(garage_id).orElseThrow(GarageNotFoundException::new)
                .getContracts().removeIf(loanContract -> loanContract.getId() == id);
        loanService.deleteLoanById(id);
    }

    @Override
    public void deleteCar(long garage_id, UUID id) {
        carService.deleteCar(id);
    }

    @Override
    public Car updateCar(UUID carId, Car newCar) {
        return carService.updateCar(carId, newCar);
    }

    @Override
    public LoanContract updateContract(UUID loanId, UUID personId, UUID carId, LocalDate startDate, LocalDate endDate, LoanStatus status) {
        return null;
    }

    @Override
    public Car getCarById(long garageId, UUID carId) {
        return carService.getCarById(carId);
    }
}
