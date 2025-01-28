import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class Writer {

    Reader reader = new Reader();
    private final League league;

    @Autowired
    Writer(League league) {
        this.league = league;
    }

    public void writeTeamData(String playerName, int teamId) {
        JSONObject teamData = reader.returnTeamData(teamId);

        Member member = new Member();
        member.setPlayerName(playerName);
        teamData.get("current");
        JSONArray gameweeks = teamData.getJSONArray("current");
        gameweeks.forEach(gameweek -> {
            JSONObject gameweekData = (JSONObject) gameweek;
            member.addGameweek(new Gameweek(gameweekData.getInt("event"), gameweekData.getInt("points")));
        });

        league.addMember(member);
    }

    public void addLeagueMembers(String fileName) {
        try {
            Map<String, Integer> players = returnListOfPlayers(fileName);
            players.forEach(this::writeTeamData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> returnListOfPlayers(String fileName) throws IOException {

        Map<String, Integer> listOfPlayers = jsonObjectToMap(getJSONFromResources(fileName));


        return listOfPlayers;
    }

    public JSONObject getJSONFromResources(String resourcePath) throws IOException {
        InputStream inputStream = Writer.class.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            // Attempt to load resource using file path
            File file = new File("src/test/resources/" + resourcePath);
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }
        }
        String jsonText = IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
        return new JSONObject(jsonText);
    }

    // Convert JSONObject to Map<String, Object>
    private static Map<String, Integer> jsonObjectToMap(JSONObject jsonObject) {
        Map<String, Integer> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Integer value = (Integer) jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }


}
