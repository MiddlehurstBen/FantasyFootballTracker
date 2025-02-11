package Tables;

import POJOs.League;
import POJOs.Member;
import Tools.TableSorter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class PointsOnBenchTable {

    private int gameweekNumber;
    private Map<String, Integer> pointsOnBenchTable = new HashMap<>();

    private Map<String, Integer> pointsOnBenchTableWeekly = new HashMap<>();


    League league;
    TableSorter tableSorter = new TableSorter();

    @Autowired
    public PointsOnBenchTable(League league) {
        this.league = league;
    }

    public Map<String, Integer> getPointsOnBenchTable() {
        setPointsOnBenchTable();
        return tableSorter.sortMapByValueDescending(pointsOnBenchTable);
    }

    private void setPointsOnBenchTable() {
        for (Member player : league.getMemberList()) {
            pointsOnBenchTable.put(player.getPlayerName(), player.getTotalPointsLeftOnBench());
        }
    }

    public Map<String, Integer> getPointsOnBenchTableWeekly(int gameweekNumber) {
        setPointsOnBenchTableWeekly(gameweekNumber);
        return tableSorter.returnTop3FromMap(pointsOnBenchTableWeekly);
    }

    private void setPointsOnBenchTableWeekly(int gameweekNumber) {
        for (Member player : league.getMemberList()) {
            pointsOnBenchTableWeekly.put(player.getPlayerName(), player.getGameweek(gameweekNumber).getPointsLeftOnBench());
        }
    }
}
