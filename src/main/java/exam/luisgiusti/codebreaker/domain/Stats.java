package exam.luisgiusti.codebreaker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
	@JsonProperty("count_mutant_dna")
	long countMutantDna;
	@JsonProperty("count_human_dna")
	long countHumanDna;

	public Stats() { }

	public Stats(long countMutantDna, long countHumanDna) {
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
	}

	public long getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public long getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public float getRatio() {
		if(countHumanDna + countMutantDna == 0) {
			return 1f;
		} else {
			return countMutantDna / (float) countHumanDna;
		}
	}
}
