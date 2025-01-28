import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public League league() {
//        System.out.println("Creating League Bean");
//        League league = new League("The One That Matters");
//        Writer writer = new Writer(league);
//        writer.addLeagueMembers("leaguePLayers.json");
        return new League("The One That Matters");
    }


}
