package exam.luisgiusti.codebreaker.services;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;

public interface CarbonUnitDataService {
	boolean existsInDB(String[] dna);
	int countHomoSuperior();
	int countHomoSapiens();
	void saveCarbonUnit(CarbonUnit carbonUnit);
}
