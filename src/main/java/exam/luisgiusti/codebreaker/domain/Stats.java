package exam.luisgiusti.codebreaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stats {
	int countMutantDna;
	int countHumanDna;
	float ratio;
}
