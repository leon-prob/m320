package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.loan.LoanStatus;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.car.Car;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<Long>> getLibraries(){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarGarages());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{garage_id}/customer")
    public ResponseEntity<Set<User>> getAllCustomers(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllCustomers(garage_id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{garage_id}/banned")
    public ResponseEntity<Set<User>> getAllBannedPeople(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllBannedPeople(garage_id));
    }

    @GetMapping("/{garage_id}/car")
    public ResponseEntity<List<Car>> getCarCollection(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarList(garage_id));
    }

    @GetMapping("/{garage_id}/car/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable long garage_id, @PathVariable UUID carId){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getCarById(garage_id, carId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{garage_id}/contract")
    public ResponseEntity<List<LoanContract>> getContracts(@PathVariable long garage_id){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllContracts(garage_id));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{garage_id}/contract/{contractId}")
    public ResponseEntity<LoanContract> getContractById(@PathVariable long garage_id, @PathVariable UUID contractId){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getContractById(garage_id, contractId));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{garage_id}/contract/person/{userId}")
    public ResponseEntity<List<LoanContract>> getContractsOfAPerson(@PathVariable long garage_id, @PathVariable UUID userId){
        return ResponseEntity.status(HttpStatus.OK).body(carGarageService.getAllContractsOfAPerson(garage_id, userId));
    }


    // post api's
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/garage")
    public ResponseEntity<CarGarage> createLibrary(){
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.addGarage());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{garage_id}/car")
    public ResponseEntity<Car> addCar(@PathVariable long garage_id, @Valid @RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.createCar(garage_id, car));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/{garage_id}/contract")
    public ResponseEntity<LoanContract> addLoanContract(@PathVariable long garage_id,
                                                        @RequestParam UUID personId, @RequestParam UUID carId,
                                                        @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                                        @RequestParam LoanStatus status) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carGarageService.createContract(garage_id, personId, carId,startDate, endDate, status));
    }

    // put api's

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{garageId}/car/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Long garageId, @PathVariable UUID carId, @Valid @RequestBody Car updatedCar){

        return ResponseEntity.status(HttpStatus.CREATED).body(carGarageService.updateCar(carId, updatedCar));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{garage_id}/contract/{loanId}")
    public ResponseEntity<LoanContract> updateLoanContract(@PathVariable UUID loanId,
                                                           @RequestParam UUID personId, @RequestParam UUID carId,
                                                           @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                                           @RequestParam LoanStatus status, @PathVariable long garage_id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carGarageService.updateContract(loanId, personId, carId,startDate, endDate, status));
    }

    // delete api's
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{garage_id}/contract/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable long garage_id, @PathVariable UUID id){
        carGarageService.deleteLoan(garage_id, id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{garage_id}/car/{id}")
    public ResponseEntity<Void> deletecar(@PathVariable long garage_id, @PathVariable UUID id){
        carGarageService.deleteCar(garage_id, id);
        return ResponseEntity.noContent().build();
    }

}
