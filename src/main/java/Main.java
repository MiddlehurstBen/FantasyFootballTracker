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
        int currentGameweek = 23;


        Reporter reporter = new Reporter(league);
        reporter.weeklyReport(currentGameweek);

        reporter.overallReport();

        reporter.pointsOnBenchReport();

    }
}
