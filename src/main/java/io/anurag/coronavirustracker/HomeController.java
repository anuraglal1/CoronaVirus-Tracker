package io.anurag.coronavirustracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases =  allStats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum();
		int newReportedCases =  allStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();

		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("newReportedCases", newReportedCases);
		return "home";
	}
}
