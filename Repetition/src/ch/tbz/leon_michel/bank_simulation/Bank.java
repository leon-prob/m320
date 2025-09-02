package ch.tbz.leon_michel.bank_simulation;

import java.math.BigDecimal;
import java.util.*;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String ownerName, BigDecimal initialBalance) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Account acc = new Account(id, ownerName, initialBalance);
        accounts.put(id, acc);
        return acc;
    }

    public Optional<Account> findAccount(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public List<Account> listAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public void transfer(String fromId, String toId, BigDecimal amount) {
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);
        if (from == null || to == null)
            throw new NoSuchElementException("Account not found");
        from.transferTo(to, amount);
    }
}