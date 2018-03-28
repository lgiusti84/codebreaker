package exam.luisgiusti.codebreaker.services.impls;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.repositories.CarbonUnitsRepository;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"repo", "default"})
public class CarbonUnitDataServiceRepoImpl implements CarbonUnitDataService {
	private CarbonUnitsRepository repo;
	private DNAAnalyzerService dnaAnalyzerService;

	@Autowired
	public CarbonUnitDataServiceRepoImpl(CarbonUnitsRepository repo, DNAAnalyzerService dnaAnalyzerService) {
		this.repo = repo;
		this.dnaAnalyzerService = dnaAnalyzerService;
	}
	
	@Override
	public boolean existsInDB(String[] dna) {
		return repo.existsByDna(dna);
	}

	@Override
	public int countHomoSuperior() {
		return repo.countAllByIsHomoSuperior(true);
	}

	@Override
	public int countHomoSapiens() {
		return repo.countAllByIsHomoSuperior(false);
	}

	@Override
	public void saveCarbonUnit(CarbonUnit carbonUnit) {
		String[] dna = carbonUnit.getDna();
		if(!existsInDB(dna)) {
			carbonUnit.setIsHomoSuperior(dnaAnalyzerService.isMutant(dna));
			repo.save(carbonUnit);
		}
	}
}
