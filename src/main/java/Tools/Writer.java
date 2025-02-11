package Tools;

import Calculators.ChipCalculators;
import POJOs.GameweekPlayer;
import POJOs.League;
import POJOs.Member;
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
    ChipCalculators chipCalculators = new ChipCalculators();

    @Autowired
    public Writer(League league) {
        this.league = league;
    }

    public void writeTeamData(String playerName, int teamId, int totalPoints) {
        JSONObject teamData = reader.returnTeamData(teamId);

        Member member = new Member();
        member.setPlayerName(playerName);
        member.setTotalPoints(totalPoints);
        teamData.get("current");

        JSONArray gameweeks = teamData.getJSONArray("current");

        gameweeks.forEach(gameweek -> {
            JSONObject gameweekData = (JSONObject) gameweek;
            member.addGameweek(new GameweekPlayer(gameweekData.getInt("event"), gameweekData.getInt("points")));
            member.getGameweek(gameweekData.getInt("event")).setPointsLeftOnBench(gameweekData.getInt("points_on_bench"));
        });

        chipCalculators.assignUsedChips(member, teamData.getJSONArray("chips"));

        league.addMember(member);
    }

    public void addLeagueMembersFromLeagueID(int leagueID) {
        JSONObject leagueData = reader.returnLeagueData(leagueID);

        JSONArray leaguePlayers = leagueData.getJSONObject("standings").getJSONArray("results");

        for (int i = 0; i < leaguePlayers.length(); i++) {
            JSONObject player = leaguePlayers.getJSONObject(i);
            writeTeamData(player.getString("player_name"), player.getInt("entry"), player.getInt("total"));
            System.out.println("Added Player: " + player.getString("player_name"));

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
