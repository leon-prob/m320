package ch.tbz.leon_michel.bank_simulation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Account {
    private final String id;
    private final String ownerName;
    private BigDecimal balance;

    public Account(String id, String ownerName, BigDecimal initialBalance) {
        if (initialBalance == null || initialBalance.signum() < 0) {
            throw new IllegalArgumentException("Initial balance must be >= 0");

        }
        this.id = Objects.requireNonNull(id);
        this.ownerName = Objects.requireNonNull(ownerName);
        this.balance = initialBalance.setScale(2, RoundingMode.HALF_UP);
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = balance.add(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public void withdraw(BigDecimal amount) {
        requirePositive(amount);
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public void transferTo(Account target, BigDecimal amount) {
        Objects.requireNonNull(target);
        if (this == target)
            throw new IllegalArgumentException("Cannot transfer to same account");
        requirePositive(amount);
        this.withdraw(amount);
        target.deposit(amount);
    }

    private static void requirePositive(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
    }

    @Override
    public String toString() {
        return "Account{" + id + ", owner='" + ownerName + "', balance=" + balance + "}";

    }
}