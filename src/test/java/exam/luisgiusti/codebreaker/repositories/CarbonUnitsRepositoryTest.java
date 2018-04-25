package exam.luisgiusti.codebreaker.repositories;

import exam.luisgiusti.codebreaker.TestConstants;
import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CarbonUnitsRepositoryTest {
	@Autowired
	private CarbonUnitsRepository repo;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private static String[] mutantDna;
	private static String[] humanDna;
	private CarbonUnit human;
	private CarbonUnit mutant;

	@BeforeClass
	public static void prepareVariables() {
		mutantDna = TestConstants.MUTANT_DNA;
		humanDna = TestConstants.HUMAN_DNA;
	}

	@Before
	public void setUp() {
		mutant = new CarbonUnit(mutantDna, true);
		human = new CarbonUnit(humanDna, false);

		mongoTemplate.dropCollection(CarbonUnit.class);
		mongoTemplate.createCollection(CarbonUnit.class);
	}

	@Test
	public void existsMutantById() {
		testExistsCarbonUnit(mutant);
	}

	@Test
	public void existsHumanById() {
		testExistsCarbonUnit(human);
	}

	private void testExistsCarbonUnit(CarbonUnit unit) {
		Optional<CarbonUnit> dbCU = repo.findById(unit.getId());
		assertFalse(dbCU.isPresent());

		repo.save(unit);

		dbCU = repo.findById(unit.getId());
		assertTrue(dbCU.isPresent());
	}

//	@Test
//	public void findByDna() {
//		assertFalse(repo.findById(mutant.getId()).isPresent());
//
//		repo.save(mutant);
//
//		assertTrue(repo.findById(mutant.getId()).isPresent());
//	}

	@Test
	public void countAllByIsHomoSuperior() {
		assertEquals(0, repo.countAllByIsHomoSuperior(true));
		assertEquals(0, repo.countAllByIsHomoSuperior(false));

		repo.save(mutant);

		assertEquals(1, repo.countAllByIsHomoSuperior(true));
		assertEquals(0, repo.countAllByIsHomoSuperior(false));
	}

	@Test
	public void countAllByIsNotHomoSuperior() {
		assertEquals(0, repo.countAllByIsHomoSuperior(false));
		assertEquals(0, repo.countAllByIsHomoSuperior(true));

		repo.save(human);

		assertEquals(1, repo.countAllByIsHomoSuperior(false));
		assertEquals(0, repo.countAllByIsHomoSuperior(true));
	}

	@Test
	public void exceptionOnAddingRepeatedDNA() {
		repo.save(mutant);
		long count = repo.countAllByIsHomoSuperior(true);

		CarbonUnit mutant2 = new CarbonUnit(mutant.getDna(), true);

		repo.save(mutant2);

		assertEquals(count, repo.countAllByIsHomoSuperior(true));
	}
}