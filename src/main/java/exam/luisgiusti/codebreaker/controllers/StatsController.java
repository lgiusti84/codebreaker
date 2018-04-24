package exam.luisgiusti.codebreaker.controllers;

import exam.luisgiusti.codebreaker.domain.Stats;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
		Optional<Stats> optionalStats = carbonUnitDataService.getStats();

		if(optionalStats.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body(optionalStats.get());
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}


	@PutMapping("reset")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity resetStats() {
		carbonUnitDataService.deleteAll();
		return ResponseEntity
				.status(HttpStatus.MOVED_PERMANENTLY)
				.header(HttpHeaders.LOCATION, "/stats/")
				.build();
	}
}
