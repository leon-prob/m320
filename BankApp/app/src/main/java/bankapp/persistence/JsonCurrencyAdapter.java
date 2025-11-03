package bankapp.persistence;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Currency;

public class JsonCurrencyAdapter extends TypeAdapter<Currency> {

    @Override
    public void write(JsonWriter out, Currency currency) throws IOException {
        if (currency == null) {
            out.nullValue();
        } else {
            out.value(currency.getCurrencyCode());
        }
    }

    @Override
    public Currency read(JsonReader in) throws IOException {
        String currencyCode = in.nextString();
        if (currencyCode == null) {
            return null;
        }
        try {
            return Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid currency code: " + currencyCode, e);
        }
    }
}