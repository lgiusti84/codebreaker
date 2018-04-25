package exam.luisgiusti.codebreaker.repositories;

import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonUnitsRepository extends MongoRepository<CarbonUnit, String> {
	long countAllByIsHomoSuperior(Boolean isHomoSuperior);
}
