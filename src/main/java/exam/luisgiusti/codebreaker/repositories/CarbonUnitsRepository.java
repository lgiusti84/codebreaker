package exam.luisgiusti.codebreaker.repositories;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarbonUnitsRepository extends CrudRepository<CarbonUnit, Long> {
	Boolean existsByDna(String[] dna);
	int countAllByIsHomoSuperior(Boolean isHomoSuperior);
	Optional<CarbonUnit> findByDna(String[] dna);
}
