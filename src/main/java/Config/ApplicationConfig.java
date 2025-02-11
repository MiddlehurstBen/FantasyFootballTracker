package Config;

import POJOs.League;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public League league() {
        return new League("The One That Matters");
    }


}
