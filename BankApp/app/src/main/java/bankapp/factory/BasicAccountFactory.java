package bankapp.factory;

import bankapp.domain.Account;
import bankapp.domain.Money;

public class BasicAccountFactory implements AccountFactory {

    @Override
    public Account createAccount(String ownerName, Money initialBalance) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        if (initialBalance == null) {
            throw new IllegalArgumentException("Initial balance cannot be null");
        }

        return new Account(ownerName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Basic Account";
    }
}