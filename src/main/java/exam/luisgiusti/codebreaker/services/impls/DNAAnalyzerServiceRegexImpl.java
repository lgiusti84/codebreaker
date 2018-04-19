package exam.luisgiusti.codebreaker.services.impls;

import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import exam.luisgiusti.codebreaker.utils.SquareStringArrayReorganizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Profile({"regex", "default"})
public class DNAAnalyzerServiceRegexImpl implements DNAAnalyzerService {
	private static final String MUTANT_REGEX_PATTERN = "([ACTG])\\1\\1\\1";
	@Value("${value.minMutant4InLineCount}")
	private int minMutant4InLineCount;

	@Override
	public boolean isMutant(String[] dna) {
		Pattern pattern = Pattern.compile(MUTANT_REGEX_PATTERN);

		long count = 0;
		List<String> allDirections = new ArrayList<>();

		allDirections.addAll(Arrays.asList(dna));
		allDirections.addAll(SquareStringArrayReorganizer.rotate(dna));
		allDirections.addAll(SquareStringArrayReorganizer.diagonalize(dna));

		for(String str : allDirections) {
			int pos = 0;
			Matcher matcher = pattern.matcher(str);
			while(matcher.find(pos)) {
				count++;
				if(count >= minMutant4InLineCount) {
					return true;
				}
				pos = matcher.end();
			}
		}

		return false ;
	}

}