public class Gameweek {

    private int gameweekNumber;
    private int gameweekPoints;

    private int flames;
    private int poop;

    public Gameweek(int gameweekNumber, int gameweekPoints) {
        this.gameweekNumber = gameweekNumber;
        this.gameweekPoints = gameweekPoints;
    }

    public int getGameweekNumber() {
        return gameweekNumber;
    }

    public int getGameweekPoints() {
        return gameweekPoints;
    }

    public int getFlames() {
        return flames;
    }

    public void setFlames(int flames) {
        this.flames = flames;
    }

    public int getPoop() {
        return poop;
    }

    public void setPoop(int poop) {
        this.poop = poop;
    }
}
