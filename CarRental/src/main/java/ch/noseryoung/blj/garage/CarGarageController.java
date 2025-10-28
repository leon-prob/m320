package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.login.user.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/*
 * add following line after having implemented every api to the application.properties file:
 * springdoc.swagger-ui.url=/api-docs.yaml
 * */

@RestController
@RequestMapping("v1/api")
public class CarGarageController {
    private final CarGarageService carGarageService;

    public CarGarageController(CarGarageService carGarageService) {
        this.carGarageService = carGarageService;
    }


    // get api's
    @GetMapping("/garage")
    public ResponseEntity<List<CarGarage>> getLibraries(){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarGarages());
    }

    @GetMapping("/{garage_id}/customer")
    public ResponseEntity<Set<User>> getAllCustomers(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllCustomers(garage_id));
    }

    @GetMapping("/{garage_id}/banned")
    public ResponseEntity<Set<User>> getAllBannedPeople(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllBannedPeople(garage_id));
    }

    @GetMapping("/{garage_id}/car")
    public ResponseEntity<List<Car>> getCarCollection(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarCollection(garage_id));
    }

    @GetMapping("/{garage_id}/car/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable long garage_id, @PathVariable UUID carId){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarById(garage_id, carId));
    }

    @GetMapping("/{garage_id}/contract")
    public ResponseEntity<List<LoanContract>> getContracts(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllContracts(garage_id));
    }


    // post api's
    @PostMapping("/garage")
    public ResponseEntity<CarGarage> createGarage(){
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.addGarage());
    }

    @PostMapping("/{garage_id}/car")
    public ResponseEntity<Car> addCar(@PathVariable long garage_id, @Valid @RequestBody Car car){
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.createCar(garage_id, car));
    }

    @PostMapping("/{garage_id}/contract")
    public ResponseEntity<LoanContract> addLoanContract(@PathVariable long garage_id,
                                                        @RequestParam UUID personId, @RequestParam UUID carId,
                                                        @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                                        @RequestParam LoanStatus status) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carGarageService.createContract(garage_id, personId, carId,startDate, endDate, status));
    }

    // put api's
    @PutMapping("/{garage_id}/car/{car_id}")
    public ResponseEntity<Car> updateCar(@PathVariable long garage_id, @PathVariable UUID car_id, @RequestParam Car newCar) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carGarageService.updateCar(garage_id, car_id, newCar));
    }

    // delete api's
    @DeleteMapping("/{garage_id}/loan/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable long garage_id, @PathVariable UUID id){
        carGarageService.deleteLoan(garage_id, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{garage_id}/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable long garage_id, @PathVariable UUID id){
        carGarageService.deleteCar(garage_id, id);
        return ResponseEntity.noContent().build();
    }
}
