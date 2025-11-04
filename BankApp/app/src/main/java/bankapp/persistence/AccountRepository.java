package bankapp.persistence;

import bankapp.domain.Account;
import bankapp.domain.Money;
import bankapp.domain.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.time.LocalDateTime;

public class AccountRepository {

    private static final String ACCOUNTS_FILE = "accounts.json";
    private final Gson gson;

    public AccountRepository() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Currency.class, new JsonCurrencyAdapter())
                .registerTypeAdapter(LocalDateTime.class, new JsonLocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void saveAccounts(List<Account> accounts) throws PersistenceException {
        try (FileWriter writer = new FileWriter(ACCOUNTS_FILE)) {
            List<AccountData> accountDataList = convertAccountsToAccountData(accounts);
            gson.toJson(accountDataList, writer);
        } catch (IOException e) {
            throw new PersistenceException("Failed to save accounts to file", e);
        }
    }

    public List<Account> loadAccounts() throws PersistenceException {
        try (FileReader reader = new FileReader(ACCOUNTS_FILE)) {
            Type listType = new TypeToken<List<AccountData>>() {
            }.getType();
            List<AccountData> accountDataList = gson.fromJson(reader, listType);

            if (accountDataList == null) {
                return new ArrayList<>();
            }

            return convertAccountDataToAccounts(accountDataList);

        } catch (IOException e) {
            if (e.getMessage().contains("No such file")) {
                return new ArrayList<>();
            }
            throw new PersistenceException("Failed to load accounts from file", e);
        }
    }

    private List<AccountData> convertAccountsToAccountData(List<Account> accounts) {
        List<AccountData> accountDataList = new ArrayList<>();

        for (Account account : accounts) {
            AccountData data = new AccountData();
            data.id = account.getId();
            data.ownerName = account.getOwnerName();
            data.balanceAmount = account.getBalance().getAmount().toString();
            data.currency = account.getCurrency().getCurrencyCode();
            data.isActive = account.isActive();

            data.transactions = new ArrayList<>();
            for (Transaction transaction : account.getTransactions()) {
                TransactionData td = new TransactionData();
                td.id = transaction.getId();
                td.type = transaction.getType();
                td.amountAmount = transaction.getAmount().getAmount().toString();
                td.amountCurrency = transaction.getAmount().getCurrency().getCurrencyCode();
                td.timestamp = transaction.getTimestamp();
                td.description = transaction.getDescription();
                td.sourceAccountId = transaction.getSourceAccountId();
                td.destinationAccountId = transaction.getDestinationAccountId();
                td.exchangeRate = transaction.getExchangeRate();
                if (transaction.getFee() != null) {
                    td.feeAmount = transaction.getFee().getAmount().toString();
                    td.feeCurrency = transaction.getFee().getCurrency().getCurrencyCode();
                }
                data.transactions.add(td);
            }

            accountDataList.add(data);
        }

        return accountDataList;
    }

    private List<Account> convertAccountDataToAccounts(List<AccountData> accountDataList) {
        List<Account> accounts = new ArrayList<>();

        for (AccountData accountData : accountDataList) {
            BigDecimal balanceAmount = new BigDecimal(accountData.balanceAmount);
            Currency currency = Currency.getInstance(accountData.currency);
            Money balance = new Money(balanceAmount, currency);

            List<Transaction> transactions = new ArrayList<>();
            if (accountData.transactions != null) {
                for (TransactionData transactionData : accountData.transactions) {
                    Transaction transaction = convertTransactionData(transactionData);
                    transactions.add(transaction);
                }
            }

            Account account = new Account(
                    accountData.id,
                    accountData.ownerName,
                    balance,
                    transactions,
                    accountData.isActive);
            accounts.add(account);
        }

        return accounts;
    }

    private Transaction convertTransactionData(TransactionData transactionData) {
        BigDecimal amountValue = new BigDecimal(transactionData.amountAmount);
        Currency amountCurrency = Currency.getInstance(transactionData.amountCurrency);
        Money amount = new Money(amountValue, amountCurrency);

        Money fee = null;
        if (transactionData.feeAmount != null && transactionData.feeCurrency != null) {
            BigDecimal feeValue = new BigDecimal(transactionData.feeAmount);
            Currency feeCurrency = Currency.getInstance(transactionData.feeCurrency);
            fee = new Money(feeValue, feeCurrency);
        }

        return new Transaction(
                transactionData.id,
                transactionData.type,
                amount,
                transactionData.timestamp,
                transactionData.description,
                transactionData.sourceAccountId,
                transactionData.destinationAccountId,
                transactionData.exchangeRate,
                fee);
    }

    private static class AccountData {
        String id;
        String ownerName;
        String balanceAmount;
        String currency;
        List<TransactionData> transactions;
        boolean isActive;
    }

    private static class TransactionData {
        String id;
        bankapp.domain.TransactionType type;
        String amountAmount;
        String amountCurrency;
        LocalDateTime timestamp;
        String description;
        String sourceAccountId;
        String destinationAccountId;
        Double exchangeRate;
        String feeAmount;
        String feeCurrency;
    }

    public static class PersistenceException extends Exception {
        public PersistenceException(String message) {
            super(message);
        }

        public PersistenceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}