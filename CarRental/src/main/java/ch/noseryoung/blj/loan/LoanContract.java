package ch.noseryoung.blj.loan;

import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(schema="blj", name="loan_contract")

public class LoanContract {

    public LoanContract(LocalDate startDate, LocalDate endDate, LoanStatus status, Person customer, Car car){
        id = UUID.randomUUID();
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanStatus = status;
        this.customer = customer;
        this.car = car;
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
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    @NotNull
    private Person customer;

    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    @NotNull
    private Car car;

}
