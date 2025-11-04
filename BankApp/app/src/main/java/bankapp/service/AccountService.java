package bankapp.service;

import bankapp.domain.Account;
import bankapp.domain.Money;
import bankapp.factory.AccountFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Currency;

public class AccountService {

    private final AccountFactory accountFactory;
    private final Map<String, Account> accounts;

    public AccountService(AccountFactory accountFactory) {
        if (accountFactory == null) {
            throw new IllegalArgumentException("AccountFactory cannot be null");
        }
        this.accountFactory = accountFactory;
        this.accounts = new HashMap<>();
    }

    public Account createAccount(String ownerName, double initialBalance, Currency currency) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        Money balance = new Money(initialBalance, currency);
        Account account = accountFactory.createAccount(ownerName, balance);

        accounts.put(account.getId(), account);
        return account;
    }

    public Account getAccount(String accountId) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
        return accounts.get(accountId);
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public void deposit(String accountId, Money amount, String description) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        Account account = getAccount(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }

        account.deposit(amount, description != null ? description : "Deposit");
    }

    public void deposit(String accountId, double amount, Currency currency, String description) {
        Money money = new Money(amount, currency);
        deposit(accountId, money, description);
    }

    public void withdraw(String accountId, Money amount, String description) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        Account account = getAccount(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }

        account.withdraw(amount, description != null ? description : "Withdrawal");
    }

    public void withdraw(String accountId, double amount, Currency currency, String description) {
        Money money = new Money(amount, currency);
        withdraw(accountId, money, description);
    }

    public boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.put(account.getId(), account);
    }

    public void removeAccount(String accountId) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
        accounts.remove(accountId);
    }
}