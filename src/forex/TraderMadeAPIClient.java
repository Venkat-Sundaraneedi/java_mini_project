package forex;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;

public class TraderMadeAPIClient {

    private static final String API_KEY = "WIsJLsQEw6JL69nwC4-L";
    private static final String API_URL = "https://marketdata.tradermade.com/api/v1/live_crypto_list?api_key=WIsJLsQEw6JL69nwC4-L";

    public static String fetchData() {
        try {
            String cryptoPairsJson = fetchDataFromAPI(API_URL);


            // Parse JSON using Gson
            Gson gson = new Gson();
            CryptoPairsResponse response = gson.fromJson(cryptoPairsJson, CryptoPairsResponse.class);


            // Process and display cryptocurrency pairs
            return convertCryptoPairsToString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch cryptocurrency list.";
        }
    }

    private static String fetchDataFromAPI(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String convertCryptoPairsToString(forex.CryptoPairsResponse response) {
        StringBuilder cryptoList = new StringBuilder();
        if (response != null && response.getAvailableCurrencies() != null) {
            for (Map.Entry<String, String> entry : response.getAvailableCurrencies().entrySet()) {
                String currencyCode = entry.getKey();
                String currencyName = entry.getValue();
                cryptoList.append(currencyCode).append(": ").append(currencyName).append("\n");
            }
        } else {
            cryptoList.append("No cryptocurrency pairs found.");
        }
        return cryptoList.toString();
    }

}
class CryptoPairsResponse {

    private Map<String, String> available_currencies;

    public Map<String, String> getAvailableCurrencies() {
        return available_currencies;
    }

    public void setAvailableCurrencies(Map<String, String> availableCurrencies) {
        this.available_currencies = availableCurrencies;
    }
}


