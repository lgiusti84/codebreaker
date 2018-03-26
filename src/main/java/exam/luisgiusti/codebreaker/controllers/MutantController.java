package exam.luisgiusti.codebreaker.controllers;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
	public ResponseEntity checkMutant(@Valid @RequestBody CarbonUnit carbonUnit) {
		String[] dna = carbonUnit.getDna();
		HttpStatus status;
		String body;
		if(dnaAnalyzer.isMutant(dna)) {
			status = HttpStatus.OK;
			body = "Welcome to the brotherhood fellow Mutant!";
		} else {
			status = HttpStatus.FORBIDDEN;
			body = "No humans allowed! We'll come for you later";
		}
		return ResponseEntity
				.status(status)
				.body(body);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleWrongType() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String body = "You are a strange being, we have no quarrel with you (yet)";
		return ResponseEntity
				.status(status)
				.body(body);
	}
}
