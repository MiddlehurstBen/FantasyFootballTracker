import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class LeagueManager {

    private final League league;

    @Autowired
    LeagueManager(League league) {
        this.league = league;
    }

    public void assignGameweekFlames(GameweekTable gameweekTable) {
        List<String> top3 = gameweekTable.returnTop3();
        for (int i = 0; i < top3.size(); i++) {
            league.getMember(top3.get(i)).addFlames(3 - i);
        }
    }

    public void assignGameweekPoop(GameweekTable gameweekTable) {
        List<String> bottom3 = gameweekTable.returnBottom3();
        for (int i = 0; i < bottom3.size(); i++) {
            league.getMember(bottom3.get(i)).addPoop(3 - i);
        }
    }

    public void calculateGameWeeks(int currentGameweek) {
        for (int i = 1; i <= currentGameweek; i++) {
            GameweekTable gameweekTable = new GameweekTable(league, i);
            assignGameweekFlames(gameweekTable);
            assignGameweekPoop(gameweekTable);
            System.out.println("Gameweek " + i + " Flames: " + gameweekTable.returnTop3());
            System.out.println("Gameweek " + i + " Poop: " + gameweekTable.returnBottom3());
        }
    }
}
