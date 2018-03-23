package exam.luisgiusti.codebreaker.controllers;

import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant/")
public class MutantController {
	private DNAAnalyzerService dnaAnalyzer;

	@Autowired
	public MutantController(DNAAnalyzerService dnaAnalyzer) {
		this.dnaAnalyzer = dnaAnalyzer;
	}

	@PostMapping
	@RequestMapping("")
	public ResponseEntity checkMutant(String[] dna) {
		HttpStatus status = dnaAnalyzer.isMutant(dna) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status).build();
	}
}
