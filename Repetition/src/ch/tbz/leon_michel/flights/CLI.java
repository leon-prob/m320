package ch.tbz.leon_michel.flights;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private final Schedule schedule;
    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    public CLI() {
        this.schedule = new Schedule();
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    }

    public static void main(String[] args) {
        CLI cliApp = new CLI();
        cliApp.run();
    }

    public void run() {
        setupInitialFlights();
        while (true) {
            printMainMenu();
            String input = scanner.nextLine();
            if (handleMainMenuChoice(input)) {
                break;
            }
        }
    }

    private boolean handleMainMenuChoice(String choice) {
        switch (choice) {
            case "1" -> registerPassengerForFlight();
            case "2" -> viewPassengersOfFlight();
            case "3" -> searchForAFlight();
            case "4" -> managePassengers();
            case "0" -> {
                System.out.println("\nExiting the application. Goodbye! 👋");
                scanner.close();
                return true;
            }
            default -> System.out.println("\nInvalid option. Please enter a number from the menu.");
        }
        return false;
    }

    private void setupInitialFlights() {
        Flight flight1 = new Flight(LocalDateTime.of(2025, 10, 20, 8, 30), LocalDateTime.of(2025, 10, 20, 10, 45));
        Flight flight2 = new Flight(LocalDateTime.of(2025, 10, 21, 14, 0), LocalDateTime.of(2025, 10, 21, 17, 15));
        Flight flight3 = new Flight(LocalDateTime.of(2025, 11, 5, 20, 0), LocalDateTime.of(2025, 11, 6, 6, 30));

        flight1.getPassengers().add(new Passenger("Max", "Muster"));
        flight1.getPassengers().add(new Passenger("Gonçalo", "De Almeida"));

        schedule.getFlights().add(flight1);
        schedule.getFlights().add(flight2);
        schedule.getFlights().add(flight3);
    }

    private void printMainMenu() {
        System.out.println("\n✈️ --- Flight Management System --- ✈️");
        System.out.println("1. Register for a flight");
        System.out.println("2. View passengers for a flight");
        System.out.println("3. Search for a flight");
        System.out.println("4. Manage passengers on a flight");
        System.out.println("0. Exit");
        System.out.print("Please enter your choice: ");
    }

    private void managePassengers() {
        System.out.println("\n--- Manage Passengers ---");
        System.out.println("First, select a flight to manage:");
        Flight selectedFlight = selectFlight();
        if (selectedFlight == null) {
            System.out.println("Action canceled.");
            return;
        }
        managePassengersMenu(selectedFlight);
    }

    private void managePassengersMenu(Flight flight) {
        while (true) {
            System.out.println("\n--- Managing Passengers for Flight departing at " + flight.getDeparture().format(formatter) + " ---");
            System.out.println("1. Search for a passenger");
            System.out.println("2. Remove a passenger");
            System.out.println("0. Back to main menu");
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine();

            if (handleManagePassengersChoice(choice, flight)) {
                return;
            }
        }
    }

    private boolean handleManagePassengersChoice(String choice, Flight flight) {
        switch (choice) {
            case "1" -> searchForPassenger(flight);
            case "2" -> removePassenger(flight);
            case "0" -> {
                return true; // Go back to the main menu
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
        return false;
    }

    private void registerPassengerForFlight() {
        System.out.println("\n--- Register Passenger ---");
        String firstName = getFirstNameFromUser();
        String lastName = getLastNameFromUser();
        Flight selectedFlight = selectFlight();
        if (selectedFlight == null) {
            System.out.println("Registration canceled.");
            return;
        }
        Passenger newPassenger = new Passenger(firstName, lastName);
        selectedFlight.getPassengers().add(newPassenger);
        System.out.println("\n✅ Success! " + firstName + " " + lastName +
                " has been registered for the flight departing at " + selectedFlight.getDeparture().format(formatter));
    }

    private String getFirstNameFromUser() {
        System.out.print("Enter your first name: ");
        return scanner.nextLine();
    }

    private String getLastNameFromUser() {
        System.out.print("Enter your last name: ");
        return scanner.nextLine();
    }

    private void searchForAFlight() {
        System.out.println("\n--- Search for Flights ---");
        System.out.print("Enter a date or time to search for (e.g., 2025-10-20): ");
        String searchTerm = scanner.nextLine().trim();

        if (searchTerm.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        List<Flight> matchingFlights = findMatchingFlights(searchTerm);
        displayFoundFlights(matchingFlights);
    }

    private List<Flight> findMatchingFlights(String searchTerm) {
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : schedule.getFlights()) {
            String departureStr = flight.getDeparture().format(formatter);
            String arrivalStr = flight.getArrival().format(formatter);
            if (departureStr.contains(searchTerm) || arrivalStr.contains(searchTerm)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights;
    }

    private void displayFoundFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            System.out.println("No flights found matching your search criteria.");
        } else {
            System.out.println("\n--- Found Flights ---");
            for (int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
                System.out.println((i + 1) + ": Departure: " + flight.getDeparture().format(formatter) + " | Arrival: " + flight.getArrival().format(formatter));
            }
        }
    }

    private void viewPassengersOfFlight() {
        System.out.println("\n--- View Passengers ---");
        Flight selectedFlight = selectFlight();
        if (selectedFlight == null) {
            System.out.println("Action canceled.");
            return;
        }
        displayPassengers(selectedFlight);
    }

    private void displayPassengers(Flight flight) {
        List<Passenger> passengers = flight.getPassengers();
        System.out.println("\n--- Passengers for Flight departing at " + flight.getDeparture().format(formatter) + " ---");
        if (passengers.isEmpty()) {
            System.out.println("No passengers are registered for this flight yet.");
        } else {
            System.out.println("First Name           | Last Name            | Passenger ID");
            System.out.println("---------------------------------------------------------------------------------");
            for (Passenger p : passengers) {
                System.out.printf("%-20s | %-20s | %s%n", p.getFirstName(), p.getLastName(), p.getId());
            }
        }
    }

    private void printAvailableFlights(List<Flight> flights) {
        System.out.println("\n--- Available Flights ---");
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.println((i + 1) + ": Departure: " + flight.getDeparture().format(formatter) + " | Arrival: " + flight.getArrival().format(formatter));
        }
    }

    private Flight getSelectedFlightOfUser(List<Flight> flights) {
        while (true) {
            System.out.print("Select a flight by number (or 0 to go back): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                }
                if (choice > 0 && choice <= flights.size()) {
                    return flights.get(choice - 1);
                } else {
                    System.out.println("Invalid flight number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private Flight selectFlight() {
        List<Flight> flights = schedule.getFlights();
        if (flights.isEmpty()) {
            System.out.println("Sorry, no flights are currently scheduled.");
            return null;
        }
        printAvailableFlights(flights);
        return getSelectedFlightOfUser(flights);
    }

    private void searchForPassenger(Flight flight) {
        System.out.println("\n--- Search for Passenger ---");
        System.out.print("Enter passenger's first or last name to search: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Search name cannot be empty.");
            return;
        }

        List<Passenger> foundPassengers = findPassengersByName(flight, name);
        displayFoundPassengers(foundPassengers);
    }

    private List<Passenger> findPassengersByName(Flight flight, String name) {
        List<Passenger> foundPassengers = new ArrayList<>();
        for (Passenger p : flight.getPassengers()) {
            if (p.getFirstName().equalsIgnoreCase(name) || p.getLastName().equalsIgnoreCase(name)) {
                foundPassengers.add(p);
            }
        }
        return foundPassengers;
    }

    private void displayFoundPassengers(List<Passenger> foundPassengers) {
        if (foundPassengers.isEmpty()) {
            System.out.println("No passenger found with that name on this flight.");
        } else {
            System.out.println("\n--- Found Passengers ---");
            System.out.println("First Name           | Last Name            | Passenger ID");
            System.out.println("---------------------------------------------------------------------------------");
            for (Passenger p : foundPassengers) {
                System.out.printf("%-20s | %-20s | %s%n", p.getFirstName(), p.getLastName(), p.getId());
            }
        }
    }

    private void removePassenger(Flight flight) {
        System.out.println("\n--- Remove Passenger ---");
        List<Passenger> passengers = flight.getPassengers();

        if (passengers.isEmpty()) {
            System.out.println("There are no passengers to remove from this flight.");
            return;
        }

        printPassengersForRemoval(passengers);
        handlePassengerRemovalChoice(passengers);
    }

    private void printPassengersForRemoval(List<Passenger> passengers) {
        System.out.println("Select a passenger to remove:");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.println((i + 1) + ": " + p.getFirstName() + " " + p.getLastName());
        }
    }

    private void handlePassengerRemovalChoice(List<Passenger> passengers) {
        while (true) {
            System.out.print("Enter passenger number to remove (or 0 to cancel): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    System.out.println("Removal canceled.");
                    return;
                }
                if (choice > 0 && choice <= passengers.size()) {
                    Passenger removedPassenger = passengers.remove(choice - 1);
                    System.out.println("\n✅ Success! " + removedPassenger.getFirstName() + " " + removedPassenger.getLastName() + " has been removed from the flight.");
                    return;
                } else {
                    System.out.println("Invalid passenger number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}