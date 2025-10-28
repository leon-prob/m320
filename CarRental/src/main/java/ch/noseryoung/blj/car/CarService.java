package ch.noseryoung.blj.car;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CarService {
    List<Car> getAllCars();
    Car getCarById(UUID id);

    Car createCar(Car car);

    Car updateCar(UUID carId, Car newCar);

    void deleteCar(UUID id);
}
