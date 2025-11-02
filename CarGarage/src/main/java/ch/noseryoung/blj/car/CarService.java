package ch.noseryoung.blj.car;

import ch.noseryoung.blj.login.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CarService {
    List<Car> getAllCar();
    Car getCarById(UUID id);

    Car createCar(Car media);

    void deleteCar(UUID id);

    Car updateCar(UUID mediaId, Car newCar);

}
