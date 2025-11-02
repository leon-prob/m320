package ch.noseryoung.blj.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    @Modifying
    @Query("DELETE FROM Car m WHERE m.id = :id")
    void deleteByIdCustom(@Param("id") UUID id);
}
