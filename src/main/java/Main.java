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

        writer.addLeagueMembers("leaguePLayers.json");

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
            GameweekTable gameweekTable = new GameweekTable(league, j);
            System.out.println("");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("");
            System.out.println("Gameweek Table " + j + ": ");
            gameweekTable.getGameweekMap().forEach((key, value) -> System.out.println(key + ": " + value));
        }

    }
}
