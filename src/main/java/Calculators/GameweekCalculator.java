package Calculators;

import POJOs.GameweekPlayer;
import POJOs.League;
import Tools.TableSorter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameweekCalculator {

    private final League league;
    TableSorter tableSorter = new TableSorter();
    Map<String, Integer> gameweekTable = new HashMap<>();

    public int getGameweekNumber() {
        return gameweekNumber;
    }

    int gameweekNumber;

    public GameweekCalculator(League league, int gameweekNumber) {
        this.league = league;
        this.gameweekNumber = gameweekNumber;
        gameweekTable = getGameweekMap();
    }

    public Map<String, Integer> getGameweekMap() {

        league.getMemberList().forEach(member -> {
            int gameweekPoints = member.getGameweeks().stream()
                    .filter(gameweekPlayer -> gameweekPlayer.getGameweekNumber() == gameweekNumber)
                    .mapToInt(GameweekPlayer::getGameweekPoints)
                    .sum();
            gameweekTable.put(member.getPlayerName(), gameweekPoints);
        });
        gameweekTable = tableSorter.sortMapByValueDescending(gameweekTable);
        return gameweekTable;
    }

    public Map<String, Integer> pointsLeftOnBenchMap(int gameweekNumber) {
        Map<String, Integer> benchPoints = new HashMap<>();
        league.getMemberList().forEach(member -> {
            int benchPointsTotal = member.getGameweeks().stream()
                    .filter(gameweekPlayer -> gameweekPlayer.getGameweekNumber() == gameweekNumber)
                    .mapToInt(GameweekPlayer::getPointsLeftOnBench)
                    .sum();
            benchPoints.put(member.getPlayerName(), benchPointsTotal);
        });
        return tableSorter.sortMapByValueDescending(benchPoints);
    }

    public List<String> returnTop3PointsWeekly() {
        List<String> top3 = gameweekTable.entrySet().stream()
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return top3;
    }

    public Map<String, Integer> getFlames() {
        Map<String, Integer> flames = new HashMap<>();
        List<String> top3 = returnTop3PointsWeekly();
        for (int i = 0; i < top3.size(); i++) {
            flames.put(top3.get(i), 3 - i);
        }
        return flames;
    }

    public Map<String, Integer> getPoop() {
        Map<String, Integer> poop = new HashMap<>();
        List<String> bottom3 = returnBottom3PointsWeekly();
        for (int i = 0; i < bottom3.size(); i++) {
            poop.put(bottom3.get(i), i + 1);
        }
        return poop;
    }

    public List<String> returnBottom3PointsWeekly() {
        int skipCount = Math.max(0, gameweekTable.size() - 3);
        List<String> bottom3 = gameweekTable.entrySet().stream()
                .skip(skipCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return bottom3;
    }

}
