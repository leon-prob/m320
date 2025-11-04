package bankapp.cli;

import bankapp.domain.Account;
import bankapp.domain.Money;
import bankapp.domain.Transaction;
import bankapp.factory.AccountFactory;
import bankapp.factory.BasicAccountFactory;
import bankapp.persistence.AccountRepository;
import bankapp.service.AccountService;
import bankapp.service.ExchangeService;
import bankapp.service.TransferService;
import bankapp.service.exchange.FixedRateProvider;
import bankapp.service.exchange.FrankfurterProvider;
import bankapp.service.exchange.ExchangeRateProvider;
import bankapp.service.fee.FlatFeePolicy;
import bankapp.service.fee.FeePolicy;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

public class BankCliController {

    private final Scanner scanner;
    private final AccountService accountService;
    private final TransferService transferService;
    private final AccountRepository accountRepository;

    public BankCliController() {
        this.scanner = new Scanner(System.in);

        AccountFactory accountFactory = new BasicAccountFactory();
        this.accountService = new AccountService(accountFactory);

        ExchangeRateProvider exchangeRateProvider = new FrankfurterProvider();
        ExchangeService exchangeService = new ExchangeService(exchangeRateProvider);

        FeePolicy feePolicy = new FlatFeePolicy(new BigDecimal("1.00"), Currency.getInstance("USD"));
        this.transferService = new TransferService(exchangeService, feePolicy);

        this.accountRepository = new AccountRepository();

        loadAccounts();
    }

    public void run() {
        System.out.println("=== Multi-Currency Bank Account Application ===");
        System.out.println("Welcome to your personal banking system!");

        boolean running = true;
        while (running) {
            try {
                showMainMenu();
                int choice = getIntInput("Enter your choice: ");

                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> viewAccounts();
                    case 3 -> deposit();
                    case 4 -> withdraw();
                    case 5 -> transfer();
                    case 6 -> viewTransactionHistory();
                    case 7 -> {
                        try {
                            saveAccounts();
                        } catch (Exception e) {
                            System.out.println("Failed to save accounts: " + e.getMessage());
                        } finally {
                            running = false;
                        }
                    }
                    case 8 -> {
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void showMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View Accounts");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. View Transaction History");
        System.out.println("7. Save and Exit");
        System.out.println("8. Exit without Saving");
    }

    private void createAccount() {
        System.out.println("\n=== Create Account ===");

        String ownerName = getStringInput("Enter account owner name: ");
        if (ownerName.trim().isEmpty()) {
            System.out.println("Owner name cannot be empty.");
            return;
        }

        double initialBalance = getDoubleInput("Enter initial balance: ");
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative.");
            return;
        }

        System.out.println("Available currencies:");
        System.out.println("1. USD (US Dollar)");
        System.out.println("2. EUR (Euro)");
        System.out.println("3. GBP (British Pound)");
        System.out.println("4. CHF (Swiss Franc)");
        System.out.println("5. JPY (Japanese Yen)");

        int currencyChoice = getIntInput("Select currency (1-5): ");
        Currency currency = switch (currencyChoice) {
            case 1 -> Currency.getInstance("USD");
            case 2 -> Currency.getInstance("EUR");
            case 3 -> Currency.getInstance("GBP");
            case 4 -> Currency.getInstance("CHF");
            case 5 -> Currency.getInstance("JPY");
            default -> {
                System.out.println("Invalid currency choice.");
                yield null;
            }
        };

        if (currency != null) {
            Account account = accountService.createAccount(ownerName, initialBalance, currency);
            System.out.println("Account created successfully!");
            System.out.println("Account ID: " + account.getId().substring(0, 8) + "...");
            System.out.println(account.toString());
        }
    }

    private void viewAccounts() {
        System.out.println("\n=== Account List ===");

        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found. Create an account first.");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.printf("%d. %s%n", i + 1, account.toString());
        }
    }

    private void deposit() {
        System.out.println("\n=== Deposit Money ===");

        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found. Create an account first.");
            return;
        }

        viewAccounts();
        int accountIndex = getIntInput("Select account (number): ") - 1;

        if (accountIndex < 0 || accountIndex >= accounts.size()) {
            System.out.println("Invalid account selection.");
            return;
        }

        Account account = accounts.get(accountIndex);
        double amount = getDoubleInput("Enter deposit amount: ");

        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        accountService.deposit(account.getId(), amount, account.getCurrency(), "Deposit");
        System.out.printf("Successfully deposited %.2f %s%n",
                amount, account.getCurrency().getCurrencyCode());
        System.out.println("New balance: " + account.getBalance().toString());
    }

    private void withdraw() {
        System.out.println("\n=== Withdraw Money ===");

        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found. Create an account first.");
            return;
        }

        viewAccounts();
        int accountIndex = getIntInput("Select account (number): ") - 1;

        if (accountIndex < 0 || accountIndex >= accounts.size()) {
            System.out.println("Invalid account selection.");
            return;
        }

        Account account = accounts.get(accountIndex);
        double amount = getDoubleInput("Enter withdrawal amount: ");

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        try {
            accountService.withdraw(account.getId(), amount, account.getCurrency(), "Withdrawal");
            System.out.printf("Successfully withdrew %.2f %s%n",
                    amount, account.getCurrency().getCurrencyCode());
            System.out.println("New balance: " + account.getBalance().toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private void transfer() {
        System.out.println("\n=== Transfer Money ===");

        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.size() < 2) {
            System.out.println("Need at least 2 accounts for transfer. Create more accounts first.");
            return;
        }

        System.out.println("Select source account:");
        viewAccounts();
        int sourceIndex = getIntInput("Source account (number): ") - 1;

        if (sourceIndex < 0 || sourceIndex >= accounts.size()) {
            System.out.println("Invalid source account selection.");
            return;
        }

        Account sourceAccount = accounts.get(sourceIndex);

        System.out.println("Select destination account:");
        for (int i = 0; i < accounts.size(); i++) {
            if (i != sourceIndex) {
                Account account = accounts.get(i);
                System.out.printf("%d. %s%n", i + 1, account.toString());
            }
        }

        int destIndex = getIntInput("Destination account (number): ") - 1;
        if (destIndex < 0 || destIndex >= accounts.size() || destIndex == sourceIndex) {
            System.out.println("Invalid destination account selection.");
            return;
        }

        Account destAccount = accounts.get(destIndex);

        double amount = getDoubleInput("Enter transfer amount: ");
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        String description = getStringInput("Enter description (optional): ");

        try {
            transferService.transfer(sourceAccount, destAccount,
                    new Money(amount, sourceAccount.getCurrency()), description);

            System.out.println("Transfer completed successfully!");
            System.out.println("Source account new balance: " + sourceAccount.getBalance().toString());
            System.out.println("Destination account new balance: " + destAccount.getBalance().toString());
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private void viewTransactionHistory() {
        System.out.println("\n=== Transaction History ===");

        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        viewAccounts();
        int accountIndex = getIntInput("Select account (number): ") - 1;

        if (accountIndex < 0 || accountIndex >= accounts.size()) {
            System.out.println("Invalid account selection.");
            return;
        }

        Account account = accounts.get(accountIndex);
        List<Transaction> transactions = account.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
            return;
        }

        System.out.println("\nTransaction History for: " + account.getOwnerName());
        System.out.println("----------------------------------------");

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }

    private void saveAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            accountRepository.saveAccounts(accounts);
            System.out.println("Accounts saved successfully!");
        } catch (AccountRepository.PersistenceException e) {
            System.out.println("Failed to save accounts: " + e.getMessage());
        }
    }

    private void loadAccounts() {
        try {
            List<Account> accounts = accountRepository.loadAccounts();
            for (Account account : accounts) {
                accountService.addAccount(account);
            }
            System.out.println("Loaded " + accounts.size() + " account(s) from previous session.");
        } catch (AccountRepository.PersistenceException e) {
            System.out.println("No previous data found or failed to load: " + e.getMessage());
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        try {
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            } else {
                System.out.println("\nNo input available. Exiting...");
                System.exit(0);
                return "";
            }
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage() + ". Exiting...");
            System.exit(0);
            return "";
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                if (scanner.hasNextLine()) {
                    int value = Integer.parseInt(scanner.nextLine().trim());
                    return value;
                } else {
                    System.out.println("\nNo input available. Exiting...");
                    System.exit(0);
                    return 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage() + ". Exiting...");
                System.exit(0);
                return 0;
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                if (scanner.hasNextLine()) {
                    double value = Double.parseDouble(scanner.nextLine().trim());
                    return value;
                } else {
                    System.out.println("\nNo input available. Exiting...");
                    System.exit(0);
                    return 0.0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage() + ". Exiting...");
                System.exit(0);
                return 0.0;
            }
        }
    }
}