package exam.luisgiusti.codebreaker.controllers;

import exam.luisgiusti.codebreaker.domain.Stats;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats/")
public class StatsController {
	private CarbonUnitDataService carbonUnitDataService;

	@Autowired
	public StatsController(CarbonUnitDataService carbonUnitDataService) {
		this.carbonUnitDataService = carbonUnitDataService;
	}

	@GetMapping("")
	public ResponseEntity getStats() {
		int countMutants = carbonUnitDataService.countHomoSuperior();
		int countHumans = carbonUnitDataService.countHomoSapiens();

		if(countHumans + countMutants > 0) {
			float ratio = (float) countMutants / (countHumans + countMutants);

			Stats mutantStats = new Stats(countMutants, countHumans, ratio);

			return ResponseEntity
					.status(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body(mutantStats);
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body("There are no records in our database.");
		}
	}


	@GetMapping("reset")
	public ResponseEntity resetStats() {
		carbonUnitDataService.deleteAll();
		return ResponseEntity
				.status(HttpStatus.MOVED_PERMANENTLY)
				.header(HttpHeaders.LOCATION, "/stats/")
				.build();
	}
}
