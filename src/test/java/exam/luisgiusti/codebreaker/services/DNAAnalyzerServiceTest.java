package exam.luisgiusti.codebreaker.services;

import exam.luisgiusti.codebreaker.TestConstants;
import exam.luisgiusti.codebreaker.services.impls.DNAAnalyzerServiceRegexImpl;
import exam.luisgiusti.codebreaker.services.impls.DNAAnalyzerServiceSimpleImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DNAAnalyzerServiceTest {

	private DNAAnalyzerService regexService;
	private DNAAnalyzerService simpleService;

	@Before
	public void setUp() {
		regexService = new DNAAnalyzerServiceRegexImpl();
		simpleService = new DNAAnalyzerServiceSimpleImpl();
		ReflectionTestUtils.setField(regexService, "minMutant4InLineCount", 2);
		ReflectionTestUtils.setField(simpleService, "minMutant4InLineCount", 2);
	}

	@Test
	public void isMutantDna1() {
		assertTrue(regexService.isMutant(TestConstants.MUTANT_DNA));
		assertTrue(simpleService.isMutant(TestConstants.MUTANT_DNA));
	}

	@Test
	public void isMutantDna2() {
		assertTrue(regexService.isMutant(TestConstants.MUTANT_DNA_2));
		assertTrue(simpleService.isMutant(TestConstants.MUTANT_DNA_2));
	}

	@Test
	public void isHumanDna() {
		assertFalse(regexService.isMutant(TestConstants.HUMAN_DNA));
		assertFalse(simpleService.isMutant(TestConstants.HUMAN_DNA));
	}
}