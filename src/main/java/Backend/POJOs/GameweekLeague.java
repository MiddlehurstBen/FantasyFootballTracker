package Backend.POJOs;

import java.util.List;
import Backend.Calculators.GameweekCalculator;
import java.util.Map;

public class GameweekLeague {

    int gameweekNumber;
    Map<Integer, Map<String, Integer>> weeklyTable;
    Map<Integer, Map<String, Integer>> benchPointsTable;
    List<String> top3;
    List<String> bottom3;

    GameweekCalculator gameweekCalculator;

    public GameweekLeague(int gameweekNumber) {
        this.gameweekNumber = gameweekNumber;
    }
}
