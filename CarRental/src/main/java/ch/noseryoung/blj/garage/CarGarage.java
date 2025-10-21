package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Component
@NoArgsConstructor
@Table(schema="blj", name="car_garage")
public class CarGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_garage_id")
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_car_garage")
    private List<Car> cars = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_car_garage")
    private List<Person> personSet = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_car_garage")
    private List<LoanContract> contracts = new ArrayList<>();
}
