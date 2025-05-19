package Website;

import Backend.Tools.LeagueManager;
import Backend.Tools.Reporter;
import Backend.Tools.Writer;
import Backend.POJOs.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final Writer writer;
    private final League league;
    private final Reporter reporter;

    @Autowired
    public HomeController(Writer writer, League league) {
        this.league = league;
        this.writer = writer;
        this.reporter = new Reporter(league);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the website!");
        return "home"; // This should match the name of your HTML file (home.html)
    }

    @PostMapping("/submit")
    public String submitLeagueCode(@RequestParam("leagueCode") int leagueCode, Model model) {
        // Use the leagueCode to populate the league data
        writer.addLeagueMembersFromLeagueID(leagueCode);

        // Add a success message to the model
        model.addAttribute("message", "League data successfully populated for League ID: " + leagueCode);

        return "home"; // Return to the home page
    }
}