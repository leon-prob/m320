package bankapp.service.exchange;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Currency;

public class FrankfurterProvider implements ExchangeRateProvider {

    private static final String BASE_URL = "https://api.frankfurter.app/latest";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient httpClient;
    private final Gson gson;

    public FrankfurterProvider() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build();
        this.gson = new Gson();
    }

    @Override
    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        if (fromCurrency.equals(toCurrency)) {
            return BigDecimal.ONE;
        }

        return fetchFromAPI(fromCurrency, toCurrency);
    }

    @Override
    public boolean supportsCurrencyPair(Currency fromCurrency, Currency toCurrency) {
        return true;
    }

    private BigDecimal fetchFromAPI(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        try {
            String url = String.format("%s?from=%s&to=%s",
                BASE_URL,
                fromCurrency.getCurrencyCode(),
                toCurrency.getCurrencyCode());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(TIMEOUT)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ExchangeRateException("API request failed with status: " + response.statusCode());
            }

            return parseResponse(response.body(), toCurrency);

        } catch (IOException e) {
            throw new ExchangeRateException("Network error while fetching exchange rate", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExchangeRateException("Request timeout while fetching exchange rate", e);
        }
    }

    private BigDecimal parseResponse(String responseBody, Currency toCurrency) throws ExchangeRateException {
        try {
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

            if (jsonObject.has("error")) {
                throw new ExchangeRateException("API returned error: " + jsonObject.get("error").getAsString());
            }

            if (!jsonObject.has("rates")) {
                throw new ExchangeRateException("Invalid API response: missing rates");
            }

            JsonObject rates = jsonObject.getAsJsonObject("rates");
            String currencyCode = toCurrency.getCurrencyCode();

            if (!rates.has(currencyCode)) {
                throw new ExchangeRateException("Currency not found in API response: " + currencyCode);
            }

            return rates.get(currencyCode).getAsBigDecimal();

        } catch (Exception e) {
            throw new ExchangeRateException("Failed to parse API response", e);
        }
    }
}