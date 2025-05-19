package Backend.POJOs;

import Backend.Tools.TableSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member {

    private String playerName;
    private List<GameweekPlayer> gameweekPlayerList = new ArrayList<>();
    private int totalPoints;
    private int totalFlames;
    private int totalPointsLeftOnBench;
    private int totalPoop;
    private Map<String, Integer> chipsPlayed = new HashMap<>();

    public Member() {
    }

    public GameweekPlayer getGameweek(int gameweekNumber) {
        return gameweekPlayerList.stream()
                .filter(gameweekPlayer -> gameweekPlayer.getGameweekNumber() == gameweekNumber)
                .findFirst()
                .orElse(null);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<GameweekPlayer> getGameweeks() {
        return gameweekPlayerList;
    }

    public void addGameweek(GameweekPlayer gameweekPlayer) {
        gameweekPlayerList.add(gameweekPlayer);
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalFlames() {
        return totalFlames;
    }

    public int getTotalPoop() {
        return totalPoop;
    }

    public void addFlames(int flames) {
        this.totalFlames += flames;
    }
    public void addPoop(int poop) {
        this.totalPoop += poop;
    }
    public void addPointsLeftOnBench(int pointsLeftOnBench) {
        this.totalPointsLeftOnBench += pointsLeftOnBench;
    }

    public int getTotalPointsLeftOnBench() {
        return totalPointsLeftOnBench;
    }

    public Map<String, Integer> getChipsPlayed() {
        gameweekPlayerList.forEach(gameweekPlayer -> {
            if (gameweekPlayer.wasChipPlayed()) {
                chipsPlayed.put(gameweekPlayer.getChipPlayed(), gameweekPlayer.getGameweekNumber());
            }
        });
        return TableSorter.sortChipsPlayed(chipsPlayed);
    }
}
