package bankapp.service;

import bankapp.domain.Account;
import bankapp.domain.Money;
import bankapp.service.exchange.ExchangeRateException;
import bankapp.service.fee.FeePolicy;
import java.math.BigDecimal;

public class TransferService {

    private final ExchangeService exchangeService;
    private final FeePolicy feePolicy;

    public TransferService(ExchangeService exchangeService, FeePolicy feePolicy) {
        if (exchangeService == null) {
            throw new IllegalArgumentException("ExchangeService cannot be null");
        }
        if (feePolicy == null) {
            throw new IllegalArgumentException("FeePolicy cannot be null");
        }
        this.exchangeService = exchangeService;
        this.feePolicy = feePolicy;
    }

    public void transfer(Account fromAccount, Account toAccount, Money amount, String description)
            throws TransferException {
        if (fromAccount == null) {
            throw new IllegalArgumentException("From account cannot be null");
        }
        if (toAccount == null) {
            throw new IllegalArgumentException("To account cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (!fromAccount.getBalance().isGreaterThanOrEqual(amount)) {
            throw new TransferException("Insufficient funds in source account");
        }

        Money fee = null;
        BigDecimal exchangeRate = null;
        Money convertedAmount = amount;

        try {
            Money rawFee = feePolicy.calculateFee(amount);
            
            if (!rawFee.getCurrency().equals(fromAccount.getCurrency())) {
                fee = exchangeService.convert(rawFee, fromAccount.getCurrency());
            } else {
                fee = rawFee;
            }
            
            Money totalDeduction = amount.add(fee);
            if (!fromAccount.getBalance().isGreaterThanOrEqual(totalDeduction)) {
                throw new TransferException("Insufficient funds to cover transfer amount and fee");
            }

            if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
                exchangeRate = exchangeService.getExchangeRate(fromAccount.getCurrency(), toAccount.getCurrency());
                convertedAmount = exchangeService.convert(amount, toAccount.getCurrency());
            }

            performTransfer(fromAccount, toAccount, amount, convertedAmount, fee, exchangeRate, description);

        } catch (ExchangeRateException e) {
            throw new TransferException("Failed to get exchange rate for transfer", e);
        }
    }

    public void transferInDestinationCurrency(Account fromAccount, Account toAccount,
                                            Money amount, String description) throws TransferException {
        if (fromAccount == null) {
            throw new IllegalArgumentException("From account cannot be null");
        }
        if (toAccount == null) {
            throw new IllegalArgumentException("To account cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        try {
            Money sourceAmount;
            BigDecimal exchangeRate = null;

            if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
                exchangeRate = exchangeService.getExchangeRate(toAccount.getCurrency(), fromAccount.getCurrency());
                sourceAmount = exchangeService.convert(amount, fromAccount.getCurrency());
            } else {
                sourceAmount = amount;
            }

            Money rawFee = feePolicy.calculateFee(sourceAmount);
            Money fee;
            
            if (!rawFee.getCurrency().equals(fromAccount.getCurrency())) {
                fee = exchangeService.convert(rawFee, fromAccount.getCurrency());
            } else {
                fee = rawFee;
            }

            Money totalDeduction = sourceAmount.add(fee);
            if (!fromAccount.getBalance().isGreaterThanOrEqual(totalDeduction)) {
                throw new TransferException("Insufficient funds to cover transfer amount and fee");
            }

            performTransfer(fromAccount, toAccount, sourceAmount, amount, fee, exchangeRate, description);

        } catch (ExchangeRateException e) {
            throw new TransferException("Failed to get exchange rate for transfer", e);
        }
    }

    private void performTransfer(Account fromAccount, Account toAccount, Money sourceAmount,
                               Money destinationAmount, Money fee, BigDecimal exchangeRate, String description) {
        Money newSourceBalance = fromAccount.getBalance().subtract(sourceAmount.add(fee));
        Money newDestBalance = toAccount.getBalance().add(destinationAmount);
        
        fromAccount.updateBalance(newSourceBalance);
        toAccount.updateBalance(newDestBalance);

        Double rateValue = exchangeRate != null ? exchangeRate.doubleValue() : null;
        fromAccount.addTransferTransaction(sourceAmount, toAccount.getId(), description, rateValue, fee);
        toAccount.addTransferTransaction(destinationAmount, fromAccount.getId(), description, rateValue, null);
    }

    public static class TransferException extends Exception {
        public TransferException(String message) {
            super(message);
        }

        public TransferException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}