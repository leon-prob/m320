package ch.noseryoung.blj.garage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarGarageRepository extends JpaRepository<CarGarage, Long> {

    @Query("SELECT cg FROM CarGarage cg JOIN FETCH cg.carList WHERE cg.id = :garageId")
    Optional<CarGarage> findByIdWithMedia(@Param("libraryId") Long libraryId);
}
