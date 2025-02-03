import org.springframework.beans.factory.annotation.Autowired;

public class Reporter {

    private final League league;
    LeagueManager leagueManager;

    @Autowired
    Reporter(League league) {
        this.league = league;
    }

    public void weeklyReport(int currentGameweek) {
        leagueManager.calculateGameWeeks(currentGameweek);
        for (int i = 1; i <= currentGameweek; i++) {
            league.getMemberList().forEach(member -> {
                System.out.println("Gameweek " + i + " - " + member.getPlayerName() + " - " + member.getGameweek(i).getPoints());
            });
        }
    }
}
