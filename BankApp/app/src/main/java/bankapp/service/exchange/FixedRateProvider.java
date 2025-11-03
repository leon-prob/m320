package bankapp.service.exchange;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class FixedRateProvider implements ExchangeRateProvider {

    private final Map<String, BigDecimal> rates;

    public FixedRateProvider() {
        this.rates = new HashMap<>();
        initializeDefaultRates();
    }

    private void initializeDefaultRates() {
        rates.put("USD_EUR", new BigDecimal("0.85"));
        rates.put("USD_GBP", new BigDecimal("0.75"));
        rates.put("USD_CHF", new BigDecimal("0.92"));
        rates.put("USD_JPY", new BigDecimal("110.0"));

        rates.put("EUR_USD", new BigDecimal("1.176"));
        rates.put("GBP_USD", new BigDecimal("1.333"));
        rates.put("CHF_USD", new BigDecimal("1.087"));
        rates.put("JPY_USD", new BigDecimal("0.0091"));

        rates.put("EUR_GBP", new BigDecimal("0.88"));
        rates.put("GBP_EUR", new BigDecimal("1.136"));
        rates.put("EUR_CHF", new BigDecimal("1.08"));
        rates.put("CHF_EUR", new BigDecimal("0.926"));
    }

    public void setRate(Currency from, Currency to, BigDecimal rate) {
        String key = from.getCurrencyCode() + "_" + to.getCurrencyCode();
        rates.put(key, rate);
    }

    @Override
    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        String key = fromCurrency.getCurrencyCode() + "_" + toCurrency.getCurrencyCode();

        if (fromCurrency.equals(toCurrency)) {
            return BigDecimal.ONE;
        }

        BigDecimal rate = rates.get(key);
        if (rate == null) {
            throw new ExchangeRateException("Exchange rate not available for " + key);
        }

        return rate;
    }

    @Override
    public boolean supportsCurrencyPair(Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return true;
        }

        String key = fromCurrency.getCurrencyCode() + "_" + toCurrency.getCurrencyCode();
        return rates.containsKey(key);
    }
}