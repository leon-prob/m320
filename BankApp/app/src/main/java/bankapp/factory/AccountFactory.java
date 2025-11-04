package bankapp.factory;

import bankapp.domain.Account;
import bankapp.domain.Money;

public interface AccountFactory {

    Account createAccount(String ownerName, Money initialBalance);

    String getAccountType();
}