package exam.luisgiusti.codebreaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Stats {
	long countMutantDna;
	long countHumanDna;

	public float getRatio() {
		if(countHumanDna + countMutantDna == 0) {
			return 1f;
		} else {
			return countMutantDna / (float) countHumanDna;
		}
	}
}
