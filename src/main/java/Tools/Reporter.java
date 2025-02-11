package Tools;

import Calculators.GameweekCalculator;
import POJOs.League;
import Tables.PointsOnBenchTable;
import org.springframework.beans.factory.annotation.Autowired;

public class Reporter {

    private final League league;
    LeagueManager leagueManager;
    PointsOnBenchTable pointsOnBenchTable;


    @Autowired
    public Reporter(League league) {
        this.league = league;
        leagueManager = new LeagueManager(league);
        pointsOnBenchTable = new PointsOnBenchTable(league);
    }

    public void weeklyReport(int currentGameweek) {
        leagueManager.calculateGameWeeks(currentGameweek);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("========================================================================");
        System.out.println("Gameweek " + currentGameweek);
        System.out.println("========================================================================");
        System.out.println("");

        GameweekCalculator gameweekCalculator = new GameweekCalculator(league, currentGameweek);
        gameweekCalculator.getGameweekMap().forEach((playerName, gameweekPoints) -> {
            System.out.println(playerName + ": " + gameweekPoints);
        });

        System.out.println("");
        System.out.println("Bag Fumbles of the Week (Most Points Left on Bench): ");
        pointsOnBenchTable.getPointsOnBenchTableWeekly(currentGameweek).forEach((playerName, points) -> {
            System.out.println(playerName + ": " + points);
        });
        System.out.println("");
        System.out.println("Flames of the Week: ");
        gameweekCalculator.getFlames().forEach((playerName, flames) -> {
            System.out.println(playerName + ": " + flames);
        });

        System.out.println("");
        System.out.println("Poop of the Week: ");
        gameweekCalculator.getPoop().forEach((playerName, poop) -> {
            System.out.println(playerName + ": " + poop);
        });

        System.out.println("");
        System.out.println("Player stats for the week: ");
        league.getMemberList().forEach(member -> {
            System.out.println(member.getPlayerName());
            System.out.println("Total Points: " + member.getGameweek(currentGameweek).getGameweekPoints());
            System.out.println("Points Left on Bench: " + member.getGameweek(currentGameweek).getPointsLeftOnBench());
            System.out.println("Flames gained: " + member.getGameweek(currentGameweek).getFlames());
            System.out.println("Poop gained: " + member.getGameweek(currentGameweek).getPoop());
                if (member.getGameweek(currentGameweek).wasChipPlayed()) {
                    System.out.println("Chip Used: " + member.getGameweek(currentGameweek).getChipPlayed());
                }
            System.out.println("");
        });
    }

    public void weekByWeekReport(int totalGameweeks) {
        for (int i = 1; i <= totalGameweeks; i++) {
            weeklyReport(i);
        }
    }

    public void pointsOnBenchReport() {
        System.out.println("========================================================================");
        System.out.println("Points Left on Bench Report");
        System.out.println("========================================================================");
        System.out.println("");


        pointsOnBenchTable.getPointsOnBenchTable().forEach((playerName, points) -> {
            System.out.println(playerName + ": " + points);
        });
    }

    public void overallReport() {
        System.out.println("========================================================================");
        System.out.println("Overall Report");
        System.out.println("========================================================================");
        System.out.println("");

        league.getMemberList().forEach(member -> {
            System.out.println(member.getPlayerName());
            System.out.println("Total Points: " + member.getTotalPoints());
            System.out.println("Total Flames: " + member.getTotalFlames());
            System.out.println("Total Poop: " + member.getTotalPoop());
            System.out.printf("Total Points Left on Bench: %d\n", member.getTotalPointsLeftOnBench());
            System.out.println("Chips Played: ");
                    member.getChipsPlayed().forEach((chipPlayed, gameweekPlayed) -> {
                        System.out.println("    " + chipPlayed + " : " + "Gameweek " + gameweekPlayed);
                    });
            System.out.println("");
        });
    }
}
