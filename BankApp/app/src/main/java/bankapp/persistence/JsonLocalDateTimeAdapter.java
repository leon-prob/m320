package bankapp.persistence;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, LocalDateTime dateTime) throws IOException {
        if (dateTime == null) {
            out.nullValue();
        } else {
            out.value(dateTime.format(FORMATTER));
        }
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String dateTimeString = in.nextString();
        if (dateTimeString == null) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeString, FORMATTER);
        } catch (Exception e) {
            throw new IOException("Invalid date-time format: " + dateTimeString, e);
        }
    }
}