package Backend.Config;

import Backend.POJOs.League;
import Backend.Tools.Writer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public League league() {
        return new League();
    }

    @Bean
    public Writer writer(League league) {
        return new Writer(league);
    }


}
