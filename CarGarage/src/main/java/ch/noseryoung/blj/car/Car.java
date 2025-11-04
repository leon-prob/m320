package ch.noseryoung.blj.car;

import java.time.LocalDate;
import java.util.UUID;

import ch.noseryoung.blj.garage.CarGarage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(schema = "blj", name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String brand;
    @NotNull
    @NotEmpty
    @NotBlank
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
    @ManyToOne
    @JoinColumn(name = "garage_id") 
    private CarGarage carGarage;
}
