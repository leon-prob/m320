package ch.noseryoung;

import java.util.Scanner;

class JokeApplication {
    private final Scanner scanner;
    private final JokeFetcher jokeFetcher;

    public JokeApplication() {
        this.scanner = new Scanner(System.in);
        this.jokeFetcher = new JokeFetcher();
    }

    public void run() {
        boolean running = true;
        System.out.println("Welcome to the Object-Oriented Joke CLI!");
        while (running) {
            printMenu();
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "joke", "j" -> displayJoke();
                case "q", "quit", "exit" -> running = false;
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
        System.out.println("Thanks for the laughs. Goodbye!");
    }

    private void displayJoke() {
        try {
            System.out.println("\nFetching a joke for you...");
            Joke joke = jokeFetcher.fetchRandomJoke();
            joke.display();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("Enter 'j' or 'joke' to get a new joke.");
        System.out.println("Enter 'q' or 'quit' to exit.");
        System.out.print("Your choice: ");
    }
}
