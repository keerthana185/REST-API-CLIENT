import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class RestApiClient {

    public static void main(String[] args) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=35&longitude=139&current_weather=true";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // Parse JSON response
            JSONObject json = new JSONObject(responseBody);
            JSONObject currentWeather = json.getJSONObject("current_weather");

            System.out.println("📍 Current Weather Data:");
            System.out.println("Temperature: " + currentWeather.getDouble("temperature") + "°C");
            System.out.println("Wind Speed: " + currentWeather.getDouble("windspeed") + " km/h");
            System.out.println("Time: " + currentWeather.getString("time"));

        } catch (IOException | InterruptedException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
}