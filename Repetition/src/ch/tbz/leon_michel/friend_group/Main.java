package ch.tbz.leon_michel.friend_group;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        FriendGroup friendGroup = new FriendGroup();
        addData(friendGroup);
        System.out.println("Welcome to the Friend Group Manager!");
        run(friendGroup);
    }

    private static void run(FriendGroup friendGroup){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            printInfoBanner();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> friendGroup.printFriendsGroup();
                    case 2 -> searchForPerson(friendGroup);
                    case 3 -> {
                        System.out.println("Exiting. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void searchForPerson(FriendGroup friendGroup){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        Person foundPerson = findPersonByName(friendGroup, firstName, lastName);

        if (foundPerson != null) {
            friendGroup.searchForFriends(foundPerson);
        } else {
            System.out.println("Person not found in the group.");
        }
    }

    private static Person findPersonByName(FriendGroup friendGroup, String firstName, String lastName) {
        for (Person person : friendGroup.getFriendsGroup().keySet()) {
            if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
                return person;
            }
        }
        return null;
    }

    private static void printInfoBanner(){
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. List all friendships");
        System.out.println("2. Search for a person's friends");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addData(FriendGroup friendGroup){
        Person alice = new Person(UUID.randomUUID(), "Alice", "Smith");
        Person bob = new Person(UUID.randomUUID(), "Bob", "Johnson");
        Person charlie = new Person(UUID.randomUUID(), "Charlie", "Brown");
        Person david = new Person(UUID.randomUUID(), "David", "Miller");

        friendGroup.addFriend(alice, List.of(bob, charlie));
        friendGroup.addFriend(bob, List.of(alice, david));
        friendGroup.addFriend(charlie, List.of(alice));
        friendGroup.addFriend(david, List.of(bob));
    }
}