package Tools;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Reader {

    private String teamUrl = "https://fantasy.premierleague.com/api/entry/{teamID}/history/";
    private String leagueUrl = "https://fantasy.premierleague.com/api/leagues-classic/{leagueID}/standings/";
    AsyncHttpClient client = Dsl.asyncHttpClient();

    public Reader() {
    }

    public String buildLeagueURL(int leagueID) {
        return leagueUrl.replace("{leagueID}", String.valueOf(leagueID));
    }

    public String buildTeamURL(int teamID) {
        return teamUrl.replace("{teamID}", String.valueOf(teamID));
    }

    public JSONObject returnLeagueData(int leagueID) {

        try {
            URL url = new URL(buildLeagueURL(leagueID));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

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
            return new JSONObject(response.toString());



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject returnTeamData ( int teamID){

            try {
                URL url = new URL(buildTeamURL(teamID));
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
