package ch.noseryoung.blj.car;

import ch.noseryoung.blj.exceptions.objectNotFound.CarNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Component
@Primary
@Log4j2

public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(UUID id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    @Override
    public Car createCar(Car Car) {
        log.info("New Car created");
        return carRepository.save(Car);
    }

    public Car updateCar(UUID carId, Car newCar){
        Car updatingCar = getCarById(carId);
        updatingCar.setBrand(newCar.getBrand());
        updatingCar.setCategory(newCar.getCategory());
        updatingCar.setMaximumRentalPeriodInDays(newCar.getMaximumRentalPeriodInDays());
        updatingCar.setModel(newCar.getModel());
        updatingCar.setPricePerDay(newCar.getPricePerDay());
        updatingCar.setReleaseDate(newCar.getReleaseDate());
        updatingCar.setRequiredAge(newCar.getRequiredAge());
        return carRepository.save(updatingCar);
    }

    @Override
    public void deleteCar(UUID id) {
        log.info("Car got deleted");
        carRepository.deleteById(id);
    }

}
