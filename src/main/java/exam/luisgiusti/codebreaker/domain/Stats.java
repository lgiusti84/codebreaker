package exam.luisgiusti.codebreaker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Stats {
	@JsonProperty("count_mutant_dna")
	long countMutantDna;
	@JsonProperty("count_human_dna")
	long countHumanDna;

	public float getRatio() {
		if(countHumanDna + countMutantDna == 0) {
			return 1f;
		} else {
			return countMutantDna / (float) countHumanDna;
		}
	}
}
