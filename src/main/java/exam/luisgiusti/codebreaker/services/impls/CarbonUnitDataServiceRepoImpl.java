package exam.luisgiusti.codebreaker.services.impls;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.domain.Stats;
import exam.luisgiusti.codebreaker.repositories.CarbonUnitsRepository;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
	public long countHomoSuperior() {
		return repo.countAllByIsHomoSuperior(true);
	}

	@Override
	public long countHomoSapiens() {
		return repo.countAllByIsHomoSuperior(false);
	}

	@Override
	public CarbonUnit saveCarbonUnit(CarbonUnit carbonUnit) {
		String[] dna = carbonUnit.getDna();
		carbonUnit.setIsHomoSuperior(dnaAnalyzerService.isMutant(dna));
		return repo.save(carbonUnit);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public Optional<Stats> getStats() {
		long countMutants = countHomoSuperior();
		long countHumans = countHomoSapiens();

		if(countHumans + countMutants > 0) {
			return Optional.of(new Stats(countMutants, countHumans));
		}
		return Optional.empty();
	}
}
