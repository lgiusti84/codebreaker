package exam.luisgiusti.codebreaker.repositories;

import exam.luisgiusti.codebreaker.TestConstants;
import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarbonUnitsRepositoryTest {
	@Autowired
	private CarbonUnitsRepository repo;
	@Autowired
	private TestEntityManager entityManager;
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
		entityManager.clear();
		mutant = new CarbonUnit(null, mutantDna, true);
		human = new CarbonUnit(null, humanDna, false);
	}

	@Test
	public void existsMutantByDna() {
		assertFalse(repo.existsByDna(mutantDna));

		entityManager.persist(mutant);
		entityManager.flush();
		assertTrue(repo.existsByDna(mutantDna));
	}

	@Test
	public void existsHumanByDna() {
		assertFalse(repo.existsByDna(humanDna));

		entityManager.persist(human);
		entityManager.flush();
		assertTrue(repo.existsByDna(humanDna));
	}

	@Test
	public void findByDna() {
		assertFalse(repo.findByDna(mutantDna).isPresent());

		entityManager.persist(mutant);
		entityManager.flush();

		assertTrue(repo.findByDna(mutantDna).isPresent());
	}

	@Test
	public void countAllByIsHomoSuperior() {
		assertEquals(0, repo.countAllByIsHomoSuperior(true));
		assertEquals(0, repo.countAllByIsHomoSuperior(false));

		entityManager.persist(mutant);
		entityManager.flush();
		assertEquals(1, repo.countAllByIsHomoSuperior(true));
		assertEquals(0, repo.countAllByIsHomoSuperior(false));
	}

	@Test
	public void countAllByIsNotHomoSuperior() {
		assertEquals(0, repo.countAllByIsHomoSuperior(false));
		assertEquals(0, repo.countAllByIsHomoSuperior(true));

		entityManager.persist(human);
		entityManager.flush();
		assertEquals(1, repo.countAllByIsHomoSuperior(false));
		assertEquals(0, repo.countAllByIsHomoSuperior(true));
	}

	@Test(expected = javax.persistence.PersistenceException.class)
	public void exceptionOnAddingRepeatedDNA() {
		entityManager.persist(mutant);
		entityManager.flush();
		CarbonUnit mutant2 = new CarbonUnit(null, mutant.getDna(), true);
		entityManager.persist(mutant2);
		entityManager.flush();
	}
}