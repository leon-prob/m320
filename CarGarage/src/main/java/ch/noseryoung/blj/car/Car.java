package ch.noseryoung.blj.car;

import ch.noseryoung.blj.garage.CarGarage;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(schema="blj", name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @NotNull @NotEmpty @NotBlank
    private String brand;
    @NotNull @NotEmpty @NotBlank
    private String model;
    @PositiveOrZero
    private int requiredAge;
    @Positive
    private int maximumRentalPeriodInDays;
    @Enumerated(EnumType.STRING)
    private CarCategory category;
    @NotNull
    private LocalDate releaseDate;
    @Positive
    private double pricePerDay;
}
