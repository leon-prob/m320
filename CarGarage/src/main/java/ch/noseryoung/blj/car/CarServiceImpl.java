package ch.noseryoung.blj.car;

import ch.noseryoung.blj.exceptions.objectNotFound.CarNotFoundException;
import ch.noseryoung.blj.login.user.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@Component
@Primary
@Log4j2

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;


    @Autowired
    private EntityManager entityManager;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(UUID id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    @Override
    public Car createCar(Car media) {
        log.info("Creating media of type: {}", media.getClass().getSimpleName());
        return carRepository.save(media);
    }

    @Override
    @Transactional
    public void deleteCar(UUID id) {
        Car media = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        carRepository.delete(media);
        entityManager.flush();
        entityManager.clear();
        Car check = carRepository.findById(id).orElse(null);
        if(check == null) {
            log.info("Car got deleted");
        } else {
            log.warn("Spring boot is lagging");
        }
    }

    @Override
    public Car updateCar(UUID mediaId, Car newCar) {
        Car updatedCar = getCarById(mediaId);
        updatedCar.setBrand(newCar.getBrand());
        updatedCar.setModel(newCar.getModel());
        updatedCar.setPricePerDay(newCar.getPricePerDay());
        updatedCar.setMaximumRentalPeriodInDays(newCar.getMaximumRentalPeriodInDays());
        updatedCar.setReleaseDate(newCar.getReleaseDate());
        updatedCar.setRequiredAge(newCar.getRequiredAge());
        updatedCar.setCategory(newCar.getCategory());
        carRepository.save(updatedCar);
        return updatedCar;
    }

}
