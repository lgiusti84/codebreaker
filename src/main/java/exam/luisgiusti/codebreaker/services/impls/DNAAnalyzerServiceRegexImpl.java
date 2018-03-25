package exam.luisgiusti.codebreaker.services.impls;

import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import exam.luisgiusti.codebreaker.utils.SquareStringArrayReorganizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
@Profile({"regex", "default"})
public class DNAAnalyzerServiceRegexImpl implements DNAAnalyzerService {
	private static final String MUTANT_REGEX_PATTERN = "([ACTG])\\1\\1\\1";
	@Value("${value.minMutant4InLineCount}")
	private int minMutant4InLineCount;

	@Override
	public boolean isMutant(String[] dna) {
		Stream<String> dnaStream = Arrays.stream(dna);
		Pattern pattern = Pattern.compile(MUTANT_REGEX_PATTERN);

		long count = dnaStream
				.filter(s -> pattern.matcher(s).find())
				.count();

		count += SquareStringArrayReorganizer.rotate(dna).stream()
				.filter(s -> pattern.matcher(s).find())
				.count();

		count += SquareStringArrayReorganizer.diagonalize(dna).stream()
				.filter(s -> pattern.matcher(s).find())
				.count();

		return count >= minMutant4InLineCount;
	}

}