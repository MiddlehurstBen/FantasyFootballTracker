import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class GameweekTable {

    private final League league;
    TableSorter tableSorter = new TableSorter();
    Map<String, Integer> gameweekTable = new HashMap<>();
    int gameweekNumber;

    GameweekTable(League league, int gameweekNumber) {
        this.league = league;
        this.gameweekNumber = gameweekNumber;
        gameweekTable = getGameweekMap();
    }

    public Map<String, Integer> getGameweekMap() {

        league.getMemberList().forEach(member -> {
            int gameweekPoints = member.getGameweeks().stream()
                    .filter(gameweek -> gameweek.getGameweekNumber() == gameweekNumber)
                    .mapToInt(Gameweek::getGameweekPoints)
                    .sum();
            gameweekTable.put(member.getPlayerName(), gameweekPoints);
        });
        gameweekTable = tableSorter.sortMapByValueDescending(gameweekTable);
        return gameweekTable;
    }

    public List<String> returnTop3() {
        List<String> top3 = gameweekTable.entrySet().stream()
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return top3;
    }

    public List<String> returnBottom3() {
        int skipCount = Math.max(0, gameweekTable.size() - 3);
        List<String> bottom3 = gameweekTable.entrySet().stream()
                .skip(skipCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return bottom3;
    }

}
