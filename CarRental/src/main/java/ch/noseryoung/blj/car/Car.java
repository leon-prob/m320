package ch.noseryoung.blj.car;

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
    private String title;
    @PositiveOrZero
    private int requiredAge;
    @Positive
    private int maximumRentalPeriodInDays;
    @NotNull @NotEmpty @NotBlank
    private String description;
    @NotNull @NotEmpty @NotBlank
    private String creator;
    @NotNull
    private LocalDate releaseDate;
    private boolean hasAward;

}
