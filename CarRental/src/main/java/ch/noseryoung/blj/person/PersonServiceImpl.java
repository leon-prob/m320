package ch.noseryoung.blj.person;

import ch.noseryoung.blj.exceptions.objectNotFound.PersonNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Component
@Primary
@Log4j2

public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(UUID id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Set<Person> getBannedCustomers() {
        return personRepository.findAllBannedPeople();
    }

    @Override
    public Set<Person> getCustomers() {
        return personRepository.findAllCustomers();
    }

    @Override
    public Person createPerson(Person person) {
        log.info("New Person created");
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(UUID id) {
        log.info("Person got deleted");
        personRepository.deleteById(id);
    }
}
