package ch.tbz.leon_michel.bank_simulation;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();
        Account a = bank.createAccount("Alice", new BigDecimal("1000"));
        bank.createAccount("Bob", new BigDecimal("250"));

        System.out.println("Accounts created:");
        printAccounts(bank);

        while (true) {
            System.out.println("\n1) Deposit  2) Withdraw  3) Transfer  4) Show accounts 0) Exit");
            System.out.print("> ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        depositFlow(bank);
                        break;
                    case "2":
                        withdrawFlow(bank);
                        break;
                    case "3":
                        transferFlow(bank);
                        break;
                    case "4":
                        printAccounts(bank);
                        break;
                    case "0":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Unknown option");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static BigDecimal readAmount() {
        System.out.print("Amount (e.g., 12.50): ");
        String raw = scanner.nextLine().trim().replace(',', '.');
        return new BigDecimal(raw);
    }

    private static void depositFlow(Bank bank) {
        Account acc = askAccount(bank, "Account ID to deposit into");
        BigDecimal amount = readAmount();
        acc.deposit(amount);
        System.out.println("New balance: " + acc.getBalance());
    }

    private static void withdrawFlow(Bank bank) {
        Account acc = askAccount(bank, "Account ID to withdraw from");
        BigDecimal amount = readAmount();
        acc.withdraw(amount);
        System.out.println("New balance: " + acc.getBalance());
    }

    private static void transferFlow(Bank bank) {
        Account from = askAccount(bank, "FROM Account ID");
        Account to = askAccount(bank, "TO Account ID");
        BigDecimal amount = readAmount();
        from.transferTo(to, amount);
        System.out.println("Transferred. From balance: " + from.getBalance()
                + ", To balance: " + to.getBalance());
    }

    private static Account askAccount(Bank bank, String prompt) {
        System.out.print(prompt + ": ");
        String id = scanner.nextLine().trim();
        return bank.findAccount(id).orElseThrow(() -> new IllegalArgumentException("No such account"));
    }

    private static void printAccounts(Bank bank) {
        for (Account acc : bank.listAccounts()) {
            System.out.println(acc.getId() + " | " + acc.getOwnerName() + " | " + acc.getBalance());
        }
    }

    private static void mutate(int n, Account acc) {
        n = n + 10;
        acc.deposit(new BigDecimal("1.00"));
    }
}