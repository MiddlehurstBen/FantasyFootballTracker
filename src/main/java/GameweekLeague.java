import com.sun.tools.javac.util.List;

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
