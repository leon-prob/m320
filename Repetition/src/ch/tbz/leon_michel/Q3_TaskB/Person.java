package ch.tbz.leon_michel.Q3_TaskB;

public class Person {
    private String name;
    private boolean hasCar;

    public Person(String name, boolean hasCar) {
        this.name = name;
        this.hasCar = hasCar;
    }

    public String getName() {
        return name;
    }

    public boolean hasCar() {
        return hasCar;
    }

    @Override
    public String toString() {
        return name + (hasCar ? " (has car)" : " (no car)");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}