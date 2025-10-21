package ch.noseryoung.blj.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query("select distinct p from Person p where p.isBanned = true")
    Set<Person> findAllBannedPeople();

    @Query("select p from Person p where p.isBanned = false")
    Set<Person> findAllCustomers();

}
