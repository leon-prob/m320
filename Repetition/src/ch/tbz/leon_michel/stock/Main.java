package ch.tbz.leon_michel.stock;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ZurichStockExchange zurichStockExchange = new ZurichStockExchange();
        LondonStockExchange londonStockExchange = new LondonStockExchange();
        NewYorkStockExchange newYorkStockExchange = new NewYorkStockExchange();

        Portfolio portfolio = new Portfolio(zurichStockExchange, londonStockExchange, newYorkStockExchange);

        Share google = new Share("Google");
        google.setValue(150.00);
        portfolio.getShares().add(google);

        Share apple = new Share("Apple");
        apple.setValue(175.50);
        portfolio.getShares().add(apple);

        Share microsoft = new Share("Microsoft");
        microsoft.setValue(305.25);
        portfolio.getShares().add(microsoft);


        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Stock Price Lookup CLI ---");
        System.out.println("Available stocks: Google, Apple, Microsoft");
        System.out.println("Available exchanges: zurich, london, new york");
        System.out.println("Type 'exit' to quit.");
        System.out.println("------------------------------");

        while (true) {
            try {
                System.out.print("\nEnter stock name: ");
                String stockNameInput = scanner.nextLine().trim();

                if (stockNameInput.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter stock exchange (zurich, london, new york): ");
                String exchangeInput = scanner.nextLine();

                if (exchangeInput.equalsIgnoreCase("exit")) {
                    break;
                }

                String actualStockName = findActualStockName(portfolio, stockNameInput);

                StockExchange stockExchange;

                switch(exchangeInput){
                    case "zurich" -> stockExchange = new ZurichStockExchange();
                    case "london" -> stockExchange = new LondonStockExchange();
                    default -> stockExchange = new NewYorkStockExchange();
                }

                double price = portfolio.getStockPrice(actualStockName, stockExchange);

                System.out.printf("-> The price of %s on the %s exchange is %.2f%n",
                        actualStockName, exchangeInput.toLowerCase(), price);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            }
        }

        System.out.println("\nGoodbye!");
        scanner.close();
    }

    private static String findActualStockName(Portfolio portfolio, String userInput) throws Exception {
        for (Share share : portfolio.getShares()) {
            if (share.getStockName().equalsIgnoreCase(userInput)) {
                return share.getStockName();
            }
        }
        throw new Exception("Not a valid stock name!");
    }
}