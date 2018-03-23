package exam.luisgiusti.codebreaker.services.impls;

import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.springframework.stereotype.Service;

@Service
public class DNAAnalyzerServiceSimpleImpl implements DNAAnalyzerService {

	@Override
	public boolean isMutant(String[] dna) {
		int count = 0;

		for(String strLine : dna)
			for(int i = 3; i < strLine.length(); i++) {
				int j = 1;
				char iChar = strLine.charAt(i);
				while(j <= 3 && iChar == strLine.charAt(i - j)) {
					j++;
				}

				if (j == 4) {
					count++;
				}
				if (count == 2) {
					return true;
				}
			}
		return false;
	}
}
