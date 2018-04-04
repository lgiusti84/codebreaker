package exam.luisgiusti.codebreaker.services;

import exam.luisgiusti.codebreaker.TestConstants;
import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.repositories.CarbonUnitsRepository;
import exam.luisgiusti.codebreaker.services.impls.CarbonUnitDataServiceRepoImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CarbonUnitDataServiceTest {
	@Mock
	private CarbonUnitsRepository repo;
	@Mock
	private DNAAnalyzerService analyzerService;
	@InjectMocks
	private CarbonUnitDataServiceRepoImpl service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void existsInDB() {
		when(repo.existsByDna(any(String[].class))).thenReturn(true);

		assertTrue(service.existsInDB(TestConstants.MUTANT_DNA));
		verify(repo, times(1)).existsByDna(any(String[].class));
	}

	@Test
	public void countHomoSuperior() {
		when(repo.countAllByIsHomoSuperior(anyBoolean())).thenReturn(1);

		assertEquals(1, service.countHomoSuperior());
		verify(repo, times(1)).countAllByIsHomoSuperior(anyBoolean());
	}

	@Test
	public void countHomoSapiens() {
		when(repo.countAllByIsHomoSuperior(anyBoolean())).thenReturn(1);

		assertEquals(1, service.countHomoSapiens());
		verify(repo, times(1)).countAllByIsHomoSuperior(anyBoolean());
	}

	@Test
	public void saveCarbonUnit() {
		String[] dna = TestConstants.MUTANT_DNA;
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		CarbonUnit savedCu = new CarbonUnit(1L, dna, true);

		when(repo.save(any(CarbonUnit.class))).thenReturn(savedCu);
		when(repo.existsByDna(any(String[].class))).thenReturn(false);
		when(analyzerService.isMutant(any(String[].class))).thenReturn(true);

		service.saveCarbonUnit(cu);
//		CarbonUnit result = service.saveCarbonUnit(cu);
//		assertArrayEquals(dna, result.getDna());
//		assertNotNull(result.getId());
//		assertNotNull(result.getIsHomoSuperior());

		verify(repo, times(1)).existsByDna(any(String[].class));
		verify(analyzerService, times(1)).isMutant(any(String[].class));
		verify(repo, times(1)).save(any(CarbonUnit.class));
		verifyNoMoreInteractions(repo, analyzerService);
	}

	@Test
	public void saveExistingCarbonUnit() {
		String[] dna = TestConstants.MUTANT_DNA;
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		CarbonUnit savedCu = new CarbonUnit(1L, dna, true);
		CarbonUnit result;

		when(repo.save(any(CarbonUnit.class))).thenReturn(savedCu);
		when(repo.existsByDna(any(String[].class))).thenReturn(true);
		when(repo.findByDna(any(String[].class))).thenReturn(Optional.of(savedCu));
		when(analyzerService.isMutant(any(String[].class))).thenReturn(true);

		service.saveCarbonUnit(cu);

		verify(repo, times(1)).existsByDna(any(String[].class));
		verify(repo, times(1)).findByDna(any(String[].class));
		verifyNoMoreInteractions(repo, analyzerService);
	}

	@Test
	public void deleteAll() {
		service.deleteAll();

		verify(repo, times(1)).deleteAll();
		verifyNoMoreInteractions(repo);
	}
}