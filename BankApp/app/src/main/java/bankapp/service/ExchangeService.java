package bankapp.service;

import bankapp.domain.Money;
import bankapp.service.exchange.ExchangeRateProvider;
import bankapp.service.exchange.ExchangeRateException;
import java.math.BigDecimal;
import java.util.Currency;

public class ExchangeService {

    private final ExchangeRateProvider exchangeRateProvider;

    public ExchangeService(ExchangeRateProvider exchangeRateProvider) {
        if (exchangeRateProvider == null) {
            throw new IllegalArgumentException("ExchangeRateProvider cannot be null");
        }
        this.exchangeRateProvider = exchangeRateProvider;
    }

    public Money convert(Money amount, Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (fromCurrency == null) {
            throw new IllegalArgumentException("From currency cannot be null");
        }
        if (toCurrency == null) {
            throw new IllegalArgumentException("To currency cannot be null");
        }

        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(fromCurrency, toCurrency);
        return amount.convertTo(toCurrency, exchangeRate);
    }

    public Money convert(Money amount, Currency toCurrency) throws ExchangeRateException {
        return convert(amount, amount.getCurrency(), toCurrency);
    }

    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        if (fromCurrency == null) {
            throw new IllegalArgumentException("From currency cannot be null");
        }
        if (toCurrency == null) {
            throw new IllegalArgumentException("To currency cannot be null");
        }

        return exchangeRateProvider.getExchangeRate(fromCurrency, toCurrency);
    }

    public boolean supportsCurrencyPair(Currency fromCurrency, Currency toCurrency) {
        return exchangeRateProvider.supportsCurrencyPair(fromCurrency, toCurrency);
    }
}