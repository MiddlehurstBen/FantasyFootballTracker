package Website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Backend.Config", "Website"})
public class WebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);  // Start the Spring Boot application
    }
}
