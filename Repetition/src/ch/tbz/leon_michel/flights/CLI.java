package ch.tbz.leon_michel.flights;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A command-line interface for managing flight passenger registration.
 * This class provides a menu-driven application for users to perform various tasks
 * like registering passengers, viewing flight details, and managing existing
 * passenger lists.
 */
public class CLI {

    private final Schedule schedule;
    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    /**
     * Constructs a new CLI object, initializing the flight schedule,
     * a scanner for user input, and a date-time formatter.
     */
    public CLI() {
        this.schedule = new Schedule();
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        CLI cliApp = new CLI();
        cliApp.run();
    }

    /**
     * Starts the main application loop, displaying the main menu and
     * handling user input until the user chooses to exit.
     */
    public void run() {
        setupInitialFlights();

        while (true) {
            printMainMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> registerPassengerForFlight();
                case "2" -> viewPassengersOfFlight();
                case "3" -> searchForAFlight();
                case "4" -> managePassengers();
                case "0" -> {
                    System.out.println("\nExiting the application. Goodbye! 👋");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\nInvalid option. Please enter a number from the menu.");
            }
        }
    }

    /**
     * Sets up a few initial flights and registers some passengers to demonstrate
     * the application's functionality.
     */
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

    /**
     * Prints the main menu options to the console.
     */
    private void printMainMenu() {
        System.out.println("\n✈️ --- Flight Management System --- ✈️");
        System.out.println("1. Register for a flight");
        System.out.println("2. View passengers for a flight");
        System.out.println("3. Search for a flight");
        System.out.println("4. Manage passengers on a flight");
        System.out.println("0. Exit");
        System.out.print("Please enter your choice: ");
    }

    /**
     * Manages a submenu for passenger management on a selected flight.
     * The user can search for or remove a passenger.
     */
    private void managePassengers() {
        System.out.println("\n--- Manage Passengers ---");
        System.out.println("First, select a flight to manage:");
        Flight selectedFlight = selectFlight();

        if (selectedFlight == null) {
            System.out.println("Action canceled.");
            return;
        }

        while (true) {
            System.out.printf("%n--- Managing Passengers for Flight departing at %s ---%n", selectedFlight.getDeparture().format(formatter));
            System.out.println("1. Search for a passenger");
            System.out.println("2. Remove a passenger");
            System.out.println("0. Back to main menu");
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchForPassenger(selectedFlight);
                    break;
                case "2":
                    removePassenger(selectedFlight);
                    break;
                case "0":
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Guides the user through the process of registering a new passenger
     * for a selected flight.
     */
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
                " has been registered for the flight departing at " +
                selectedFlight.getDeparture().format(formatter));
    }

    /**
     * Prompts the user to enter their first name and returns the input.
     *
     * @return The first name entered by the user.
     */
    private String getFirstNameFromUser(){
        System.out.print("Enter your first name: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter their last name and returns the input.
     *
     * @return The last name entered by the user.
     */
    private String getLastNameFromUser(){
        System.out.print("Enter your last name: ");
        return scanner.nextLine();
    }

    /**
     * Allows the user to search for flights by a date or time string.
     * Displays a list of flights that match the search term.
     */
    private void searchForAFlight(){
        System.out.println("\n--- Search for Flights ---");
        System.out.print("Enter a date or time to search for (e.g., 2025-10-20): ");
        String searchTerm = scanner.nextLine().trim();

        if (searchTerm.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : schedule.getFlights()) {
            String departureStr = flight.getDeparture().format(formatter);
            String arrivalStr = flight.getArrival().format(formatter);
            if (departureStr.contains(searchTerm) || arrivalStr.contains(searchTerm)) {
                matchingFlights.add(flight);
            }
        }

        if (matchingFlights.isEmpty()) {
            System.out.println("No flights found matching your search criteria.");
        } else {
            System.out.println("\n--- Found Flights ---");
            for (int i = 0; i < matchingFlights.size(); i++) {
                Flight flight = matchingFlights.get(i);
                System.out.printf("%d: Departure: %s | Arrival: %s%n",
                        i + 1,
                        flight.getDeparture().format(formatter),
                        flight.getArrival().format(formatter));
            }
        }
    }

    /**
     * Allows the user to select a flight and then views the list of
     * passengers registered for that flight.
     */
    private void viewPassengersOfFlight() {
        System.out.println("\n--- View Passengers ---");
        Flight selectedFlight = selectFlight();
        if (selectedFlight == null) {
            System.out.println("Action canceled.");
            return;
        }
        List<Passenger> passengers = selectedFlight.getPassengers();
        System.out.println("\n--- Passengers for Flight departing at " + selectedFlight.getDeparture().format(formatter)
                + " ---");
        if (passengers.isEmpty()) {
            System.out.println("No passengers are registered for this flight yet.");
        } else {
            System.out.printf("%-20s | %-20s | %-36s%n", "First Name", "Last Name", "Passenger ID");
            System.out.println(new String(new char[81]).replace('\0', '-'));
            for (Passenger p : passengers) {
                System.out.printf("%-20s | %-20s | %s%n", p.getFirstName(), p.getLastName(), p.getId());
            }
        }
    }

    /**
     * Prints a formatted list of available flights to the console.
     *
     * @param flights The list of flights to display.
     */
    private void printAvailableFlights(List<Flight> flights){
        System.out.println("\n--- Available Flights ---");
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.println((i + 1) + ": Departure: "
                    + flight.getDeparture().format(formatter)
                    + " | Arrival: "
                    + flight.getArrival().format(formatter));
        }
    }

    /**
     * Prompts the user to select a flight from a given list by number.
     *
     * @param flights The list of flights to choose from.
     * @return The selected Flight object, or {@code null} if the user cancels.
     */
    private Flight getSelectedFlightOfUser(List<Flight> flights){
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

    /**
     * Displays a list of available flights and prompts the user to select one.
     *
     * @return The selected Flight object, or {@code null} if no flights are available or the user cancels.
     */
    private Flight selectFlight() {
        List<Flight> flights = schedule.getFlights();
        if (flights.isEmpty()) {
            System.out.println("Sorry, no flights are currently scheduled.");
            return null;
        }
        printAvailableFlights(flights);
        return getSelectedFlightOfUser(flights);
    }

    /**
     * Searches for a passenger on a given flight by first or last name.
     * Displays a list of all matching passengers.
     *
     * @param flight The flight to search within.
     */
    private void searchForPassenger(Flight flight) {
        System.out.println("\n--- Search for Passenger ---");
        System.out.print("Enter passenger's first or last name to search: ");
        String name = scanner.nextLine().trim();
        if(name.isEmpty()) {
            System.out.println("Search name cannot be empty.");
            return;
        }

        List<Passenger> foundPassengers = new ArrayList<>();
        for(Passenger p : flight.getPassengers()) {
            if (p.getFirstName().equalsIgnoreCase(name) || p.getLastName().equalsIgnoreCase(name)) {
                foundPassengers.add(p);
            }
        }

        if (foundPassengers.isEmpty()) {
            System.out.println("No passenger found with that name on this flight.");
        } else {
            System.out.println("\n--- Found Passengers ---");
            System.out.printf("%-20s | %-20s | %-36s%n", "First Name", "Last Name", "Passenger ID");
            System.out.println(new String(new char[81]).replace('\0', '-'));
            for (Passenger p : foundPassengers) {
                System.out.printf("%-20s | %-20s | %s%n", p.getFirstName(), p.getLastName(), p.getId());
            }
        }
    }

    /**
     * Removes a passenger from a specific flight after user selection.
     *
     * @param flight The flight from which to remove the passenger.
     */
    private void removePassenger(Flight flight) {
        System.out.println("\n--- Remove Passenger ---");
        List<Passenger> passengers = flight.getPassengers();

        if (passengers.isEmpty()) {
            System.out.println("There are no passengers to remove from this flight.");
            return;
        }

        System.out.println("Select a passenger to remove:");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.printf("%d: %s %s%n", i + 1, p.getFirstName(), p.getLastName());
        }

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
                    System.out.printf("\n✅ Success! %s %s has been removed from the flight.%n",
                            removedPassenger.getFirstName(), removedPassenger.getLastName());
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