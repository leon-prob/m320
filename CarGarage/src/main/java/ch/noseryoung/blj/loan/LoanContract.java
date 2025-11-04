package ch.noseryoung.blj.loan;

import ch.noseryoung.blj.garage.CarGarage;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.car.Car;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(schema = "blj", name = "loan_contract")
public class LoanContract {

    public LoanContract(LocalDate startDate, LocalDate endDate, LoanStatus status, User customer, Car car, CarGarage library) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanStatus = status;
        this.customer = customer;
        this.car = car;
        this.carGarage = library;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "loan_id")
    private UUID id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "user_id")
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car_garage")
    @JsonIdentityReference(alwaysAsId = true)
    private CarGarage carGarage;

     @Positive
    private Double totalPrice;
}