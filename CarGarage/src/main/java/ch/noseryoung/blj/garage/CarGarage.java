package ch.noseryoung.blj.garage;

import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.loan.LoanContract;
import ch.noseryoung.blj.login.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema="blj", name="car_garage")
public class CarGarage {
    @Id
    @Column(name="garage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "carGarage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Car> carList = new ArrayList<>();

    @OneToMany(mappedBy = "carGarage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LoanContract> contracts = new ArrayList<>();


    @JsonIgnore
    private List<User> userList = new ArrayList<>();

    public CarGarage(String name) {
        this.name = name;
    }
}