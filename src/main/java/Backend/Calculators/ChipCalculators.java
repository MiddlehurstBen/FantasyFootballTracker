package Backend.Calculators;

import Backend.POJOs.Member;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class ChipCalculators {

    private final List<String> listOfChips = Arrays.asList("wildcard", "freehit", "bench boost", "triple captain", "manager");

    public void assignUsedChips(Member playerName, JSONArray chips) {
      chips.forEach(chip -> {
          JSONObject chipData = (JSONObject) chip;
          if (!chipData.getString("name").isEmpty()) {
              if (chipData.getString("name").equals("wildcard")) {
                  playerName.getGameweek(chipData.getInt("event")).setWasChipPlayed(true);
                  playerName.getGameweek(chipData.getInt("event")).setChipPlayed(wildcardCalculator(chipData.getInt("event")));
              } else {
                  playerName.getGameweek(chipData.getInt("event")).setWasChipPlayed(true);
                  playerName.getGameweek(chipData.getInt("event"))
                          .setChipPlayed(sortChips(chipData.getString("name")));
              }
          }
      });
    }

    private String wildcardCalculator(Integer gameweekNumber) {
        if (gameweekNumber < 19) {
            return "Wildcard 1";
        } else if (gameweekNumber > 18 && gameweekNumber < 38) {
            return "Wildcard 2";
        } else throw new IllegalArgumentException("Invalid Gameweek Number");
    }

    public String sortChips(String chipName) {
        if (chipName.equals("freehit")) {
            return "Free Hit";
        } else if (chipName.equals("bboost")) {
            return "Bench Boost";
        } else if (chipName.equals("3xc")) {
            return "Triple Captain";
        } else if (chipName.equals("manager")) {
            return "Manager";
        } else throw new IllegalArgumentException("Invalid Chip Name");
    }


}
