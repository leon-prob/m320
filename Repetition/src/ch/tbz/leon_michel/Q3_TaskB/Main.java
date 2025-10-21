package ch.tbz.leon_michel.Q3_TaskB;

public class Main {
    public static void main(String[] args) {
        Person a = new Person("A", false);
        Person b = new Person("B", false);
        Person c = new Person("C", false);
        Person d = new Person("D", true);

        SocialNetwork network = new SocialNetwork();

        network.addPerson(a);
        network.addPerson(b);
        network.addPerson(c);
        network.addPerson(d);

        network.addFriendship(a, b);
        network.addFriendship(a, c);
        network.addFriendship(b, d);

        Person result = network.findPersonWithCar(a);
        if (result != null) {
            System.out.println("Found person with car: " + result);
        } else {
            System.out.println("No person with a car found in the network.");
        }
    }
}