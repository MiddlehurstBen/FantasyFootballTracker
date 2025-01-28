import java.util.ArrayList;
import java.util.List;

public class Member {

    private String playerName;
    private List<Gameweek> gameweeks = new ArrayList<>();
    private int totalPoints;
    private int totalFlames;

    private int totalPoop;

    public Member() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Gameweek> getGameweeks() {
        return gameweeks;
    }

    public void addGameweek(Gameweek gameweek) {
        gameweeks.add(gameweek);
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
