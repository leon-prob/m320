package ch.noseryoung;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class JokeFetcher {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String API_URL = "https://v2.jokeapi.dev/joke/Any?safe-mode";

    public Joke fetchRandomJoke() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch joke. API responded with status code: " + response.statusCode());
        }

        JSONObject jsonResponse = new JSONObject(response.body());

        if (jsonResponse.getBoolean("error")) {
            throw new RuntimeException("The API returned an error. Please try again later.");
        }

        String jokeType = jsonResponse.getString("type");
        String category = jsonResponse.getString("category");

        if (jokeType.equals("single")) {
            return new SingleJoke(jsonResponse.getString("joke"), category);
        } else {
            return new TwoPartJoke(jsonResponse.getString("setup"), jsonResponse.getString("delivery"), category);
        }
    }
}
