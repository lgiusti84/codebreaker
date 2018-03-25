package exam.luisgiusti.codebreaker.controllers;

import exam.luisgiusti.codebreaker.exceptions.WrongDNAFormatException;
import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@RestController
@RequestMapping("/mutant/")
public class MutantController {
	private static final String DNA_REGEX_PATTERN = "^[AGTC]+$";
	private DNAAnalyzerService dnaAnalyzer;

	@Autowired
	public MutantController(DNAAnalyzerService dnaAnalyzer) {
		this.dnaAnalyzer = dnaAnalyzer;
	}

	@PostMapping
	@RequestMapping("")
	public ResponseEntity checkMutant(@RequestBody String[] dna) {
		validateDNA(dna);
		HttpStatus status = dnaAnalyzer.isMutant(dna) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status).build();
	}

	@ExceptionHandler(WrongDNAFormatException.class)
	public ResponseEntity handleWrongType() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).build();
	}

	private void validateDNA(String[] dna) {
		int length = dna.length;
		Pattern pattern = Pattern.compile(DNA_REGEX_PATTERN);
		Stream<String> dnaStream = Arrays.stream(dna);

		boolean wrongDNA = length < 4 || dnaStream.anyMatch(s -> s.length() != length || !pattern.matcher(s).find() );

		if(wrongDNA) {
			throw new WrongDNAFormatException();
		}
	}
}
