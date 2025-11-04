package bankapp.domain;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Currency;

public class Account {
    @SerializedName("id")
    private final String id;

    @SerializedName("ownerName")
    private final String ownerName;

    @SerializedName("balance")
    private Money balance;

    @SerializedName("currency")
    private final Currency currency;

    @SerializedName("transactions")
    private final List<Transaction> transactions;

    @SerializedName("isActive")
    private boolean isActive;

    public Account(String ownerName, Money initialBalance) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        if (initialBalance == null) {
            throw new IllegalArgumentException("Initial balance cannot be null");
        }

        this.id = UUID.randomUUID().toString();
        this.ownerName = ownerName.trim();
        this.balance = initialBalance;
        this.currency = initialBalance.getCurrency();
        this.transactions = new ArrayList<>();
        this.isActive = true;
    }

    public Account(String id, String ownerName, Money balance, List<Transaction> transactions, boolean isActive) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
        this.currency = balance.getCurrency();
        this.transactions = transactions != null ? new ArrayList<>(transactions) : new ArrayList<>();
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Money getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void deposit(Money amount, String description) {
        if (amount == null) {
            throw new IllegalArgumentException("Deposit amount cannot be null");
        }
        if (!amount.getCurrency().equals(this.currency)) {
            throw new IllegalArgumentException("Currency mismatch. Account currency: " + this.currency +
                    ", Deposit currency: " + amount.getCurrency());
        }

        this.balance = this.balance.add(amount);

        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, this.id, description);
        this.transactions.add(transaction);
    }

    public void withdraw(Money amount, String description) {
        if (amount == null) {
            throw new IllegalArgumentException("Withdrawal amount cannot be null");
        }
        if (!amount.getCurrency().equals(this.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
        if (!balance.isGreaterThanOrEqual(amount)) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.balance = this.balance.subtract(amount);

        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, this.id, description);
        this.transactions.add(transaction);
    }

    public void addTransferTransaction(Money amount, String destinationAccountId,
            String description, Double exchangeRate, Money fee) {
        TransactionType type = destinationAccountId != null ? TransactionType.TRANSFER_OUT
                : TransactionType.TRANSFER_IN;

        Transaction transaction = new Transaction(type, amount, this.id, destinationAccountId,
                description, exchangeRate, fee);
        this.transactions.add(transaction);
    }

    public void addTransferTransactionWithBigDecimal(Money amount, String destinationAccountId,
            String description, BigDecimal exchangeRate, Money fee) {
        Double rateValue = exchangeRate != null ? exchangeRate.doubleValue() : null;
        addTransferTransaction(amount, destinationAccountId, description, rateValue, fee);
    }

    public void updateBalance(Money newBalance) {
        this.balance = newBalance;
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public List<Transaction> getTransactions(LocalDateTime fromDate, LocalDateTime toDate) {
        return transactions.stream()
                .filter(t -> !t.getTimestamp().isBefore(fromDate) && !t.getTimestamp().isAfter(toDate))
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Account %s: %s (%s) - Balance: %s %s",
                id.substring(0, 8),
                ownerName,
                isActive ? "Active" : "Inactive",
                balance.getAmount(),
                balance.getCurrency().getCurrencyCode());
    }
}