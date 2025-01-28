import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Reader {

    private String url = "https://fantasy.premierleague.com/api/entry/{teamID}/history/";
    AsyncHttpClient client = Dsl.asyncHttpClient();

    public Reader() {
    }

    public String buildURL(int teamID) {
        return url.replace("{teamID}", String.valueOf(teamID));
    }

    public JSONObject returnTeamData(int teamID) {

        try {
            URL url = new URL(buildURL(teamID));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the HTTP request method
        connection.setRequestMethod("GET");

        // Add request headers if necessary (e.g., User-Agent)
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Get the response code and handle errors if needed
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // Disconnect the connection
        connection.disconnect();
        JSONObject responseJSON = new JSONObject(response.toString());

        return responseJSON;

        } catch (IOException e) {
            e.printStackTrace();
    }
    return null;
    }
}
