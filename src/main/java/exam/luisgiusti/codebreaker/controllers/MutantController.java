package exam.luisgiusti.codebreaker.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.domain.CarbonUnit.Input;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
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
	private CarbonUnitDataService dataService;

	@Autowired
	public MutantController(DNAAnalyzerService dnaAnalyzer, CarbonUnitDataService dataService) {
		this.dnaAnalyzer = dnaAnalyzer;
		this.dataService = dataService;
	}

	@PostMapping
	@RequestMapping("")
	public ResponseEntity checkMutant(@Valid @RequestBody @JsonView(Input.class) CarbonUnit carbonUnit) {
		String[] dna = carbonUnit.getDna();
		boolean isMutant = dnaAnalyzer.isMutant(dna);

		carbonUnit.setIsHomoSuperior(isMutant);
		dataService.saveCarbonUnit(carbonUnit);

		HttpStatus status;
		String body;
		if(isMutant) {
			status = HttpStatus.OK;
			body = "Welcome to the brotherhood fellow Mutant!";
		} else {
			status = HttpStatus.FORBIDDEN;
			body = "No humans allowed! We'll come for you later";
		}
		return ResponseEntity.status(status).body(body);
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
