package ch.noseryoung.blj.person;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public interface PersonService {
    List<Person> getAllPeople();
    Person getPersonById(UUID id);
    Set<Person> getBannedCustomers();
    Set<Person> getCustomers();

    Person createPerson(Person person);

    void deletePerson(UUID id);
}
