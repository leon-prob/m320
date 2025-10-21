package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.person.Person;

import jakarta.validation.Valid;
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
    @GetMapping("/library")
    public ResponseEntity<List<CarGarage>> getLibraries(){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarGarages());
    }

    @GetMapping("/{library_id}/customer")
    public ResponseEntity<Set<Person>> getAllCustomers(@PathVariable long library_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllCustomers(library_id));
    }

    @GetMapping("/{library_id}/banned")
    public ResponseEntity<Set<Person>> getAllBannedPeople(@PathVariable long library_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllBannedPeople(library_id));
    }

    @GetMapping("/{library_id}/media")
    public ResponseEntity<List<Car>> getCarCollection(@PathVariable long library_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarCollection(library_id));
    }

    @GetMapping("/{library_id}/contract")
    public ResponseEntity<List<LoanContract>> getContracts(@PathVariable long library_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllContracts(library_id));
    }


    // post api's
    @PostMapping("/library")
    public ResponseEntity<CarGarage> createGarage(){
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.addGarage());
    }

    @PostMapping("/{library_id}/person")
    public ResponseEntity<Person> addPerson(@PathVariable long library_id, @Valid @RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.createPerson(library_id, person));
    }

    @PostMapping("/{library_id}/media")
    public ResponseEntity<Car> addCar(@PathVariable long library_id, @Valid @RequestBody Car media){
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.createCar(library_id, media));
    }

    @PostMapping("/{library_id}/contract")
    public ResponseEntity<LoanContract> addLoanContract(@PathVariable long library_id,
                                                        @RequestParam UUID personId, @RequestParam UUID mediaId,
                                                        @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                                        @RequestParam LoanStatus status) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carGarageService.createContract(library_id, personId, mediaId,startDate, endDate, status));
    }

    // put api's



    // delete api's
    @DeleteMapping("/{library_id}/loan/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable long library_id, @PathVariable UUID id){
        carGarageService.deleteLoan(library_id, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{library_id}/media/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable long library_id, @PathVariable UUID id){
        carGarageService.deleteCar(library_id, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{library_id}/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long library_id, @PathVariable UUID id){
        carGarageService.deletePerson(library_id, id);
        return ResponseEntity.noContent().build();
    }

}
