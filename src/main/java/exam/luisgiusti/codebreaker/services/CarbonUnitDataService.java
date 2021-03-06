package exam.luisgiusti.codebreaker.services;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.domain.Stats;

import java.util.Optional;

public interface CarbonUnitDataService {
	boolean existsInDB(String[] dna);
	long countHomoSuperior();
	long countHomoSapiens();
	CarbonUnit saveCarbonUnit(CarbonUnit carbonUnit);
	void deleteAll();
	Optional<Stats> getStats();
}
