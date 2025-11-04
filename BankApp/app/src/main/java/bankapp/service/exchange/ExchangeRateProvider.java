package bankapp.service.exchange;

import java.util.Currency;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException;

    boolean supportsCurrencyPair(Currency fromCurrency, Currency toCurrency);
}