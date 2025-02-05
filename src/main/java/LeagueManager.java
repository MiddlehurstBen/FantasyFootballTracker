import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class LeagueManager {

    private final League league;

    @Autowired
    LeagueManager(League league) {
        this.league = league;
    }

    public void assignGameweekFlames(GameweekCalculator gameweekCalculator) {
        List<String> top3 = gameweekCalculator.returnTop3PointsWeekly();
        for (int i = 0; i < top3.size(); i++) {
            league.getMember(top3.get(i)).getGameweek(gameweekCalculator.gameweekNumber).setFlames(3 - i);
            league.getMember(top3.get(i)).addFlames(3 - i);
        }
    }

    public void assignGameweekPoop(GameweekCalculator gameweekCalculator) {
        List<String> bottom3 = gameweekCalculator.returnBottom3PointsWeekly();
        for (int i = 0; i < bottom3.size(); i++) {
            league.getMember(bottom3.get(i)).getGameweek(gameweekCalculator.getGameweekNumber()).setPoop(3 - i);
            league.getMember(bottom3.get(i)).addPoop(3 - i);
        }
    }

    public void addPointsLeftOnBench(GameweekCalculator gameweekCalculator) {
        Map<String, Integer> benchPoints = gameweekCalculator.pointsLeftOnBenchMap(gameweekCalculator.getGameweekNumber());
        benchPoints.forEach((playerName, points) -> league.getMember(playerName).addPointsLeftOnBench(points));
    }

    public void calculateGameWeeks(int currentGameweek) {
        for (int i = 1; i <= currentGameweek; i++) {
            GameweekCalculator gameweekCalculator = new GameweekCalculator(league, i);
            assignGameweekFlames(gameweekCalculator);
            assignGameweekPoop(gameweekCalculator);
            addPointsLeftOnBench(gameweekCalculator);
        }
    }
}
