package ch.tbz.leon_michel.vehicle;

import java.time.LocalDate;
import java.util.UUID;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthYear;

    public Person(String firstName, String lastName, LocalDate birthYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public int getAge(){return birthYear.until(LocalDate.now()).getYears();}

    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setBirthYear(LocalDate birthYear){this.birthYear = birthYear;}

}
