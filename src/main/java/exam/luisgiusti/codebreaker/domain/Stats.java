package exam.luisgiusti.codebreaker.domain;

public class Stats {
	long countMutantDna;
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
