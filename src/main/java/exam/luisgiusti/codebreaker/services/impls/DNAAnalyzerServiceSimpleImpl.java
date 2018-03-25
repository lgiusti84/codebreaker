package exam.luisgiusti.codebreaker.services.impls;

import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import exam.luisgiusti.codebreaker.utils.SquareStringArrayReorganizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("simple")
public class DNAAnalyzerServiceSimpleImpl implements DNAAnalyzerService {
	@Value("${value.minMutant4InLineCount}")
	private int minMutant4InLineCount;

	@Override
	public boolean isMutant(String[] dna) {
		int count = 0;

		count = updateCount(Arrays.asList(dna), count);
		if(count < minMutant4InLineCount) {
			count = updateCount(SquareStringArrayReorganizer.rotate(dna), count);
			if(count < minMutant4InLineCount) {
				count = updateCount(SquareStringArrayReorganizer.diagonalize(dna), count);
			}
		}

		return count >= minMutant4InLineCount;
	}

	private int updateCount(List<String> dna, int count) {
		for(String strLine : dna) {
			count = count4InLine(strLine, count);
			if(count >= minMutant4InLineCount) {
				return count;
			}
		}
		return count;
	}

	private int count4InLine(String strLine, int count) {
		int i;
		for (i = 3; i < strLine.length(); i++) {
			int j = 1;
			char iChar = strLine.charAt(i);
			while (j <= 3 && iChar == strLine.charAt(i - j)) {
				j++;
			}

			if (j == 4) {
				count++;
				i += 4;
			}
			if (count >= minMutant4InLineCount) {
				return count;
			}
		}
		return count;
	}

}
