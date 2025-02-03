import java.util.ArrayList;
import java.util.List;

public class Member {

    private String playerName;
    private List<GameweekPlayer> gameweekPlayers = new ArrayList<>();
    private int totalPoints;
    private int totalFlames;

    private int totalPoop;

    public Member() {
    }

    public GameweekPlayer getGameweek(int gameweekNumber) {
        return gameweekPlayers.stream()
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
        return gameweekPlayers;
    }

    public void addGameweek(GameweekPlayer gameweekPlayer) {
        gameweekPlayers.add(gameweekPlayer);
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
}
