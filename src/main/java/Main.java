import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    static League league;

    @Autowired
    Main(League league) {
        Main.league = league;
    }

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        League league = context.getBean(League.class);

        Writer writer = new Writer(league);

        writer.addLeagueMembersFromLeagueID(625213);

        LeagueManager leagueManager = new LeagueManager(league);
        leagueManager.calculateGameWeeks(23);

        System.out.println("");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("");

        System.out.println("Flames Table");
        league.getFlamesTable().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("Poop Table");
        league.getPoopTable().forEach((key, value) -> System.out.println(key + ": " + value));


        for (int j = 1 ; j < 24 ;  j++) {
            GameweekCalculator gameweekCalculator = new GameweekCalculator(league, j);
            System.out.println("");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("");
            System.out.println("Gameweek Table " + j + ": ");
            gameweekCalculator.getGameweekMap().forEach((key, value) -> System.out.println(key + ": " + value));
        }

    }
}
