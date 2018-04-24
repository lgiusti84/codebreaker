package exam.luisgiusti.codebreaker.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatsTest {

	@Test
	public void getRatio() {
		long mutantCountMock = 40;
		long humanCountMock = 100;
		float expectedRatio = mutantCountMock / (float) humanCountMock;

		Stats stats = new Stats(mutantCountMock, humanCountMock);

		assertEquals(stats.getRatio(), expectedRatio, 0);
	}

	@Test
	public void getEmptyRatio() {
		long mutantCountMock = 0;
		long humanCountMock = 0;
		float expectedRatio = 1;

		Stats stats = new Stats(mutantCountMock, humanCountMock);

		assertEquals(stats.getRatio(), expectedRatio, 0);
	}

	@Test
	public void getZeroHumansRatio() {
		long mutantCountMock = 40;
		long humanCountMock = 0;
		float expectedRatio = Float.POSITIVE_INFINITY;

		Stats stats = new Stats(mutantCountMock, humanCountMock);

		assertEquals(stats.getRatio(), expectedRatio, 0);
	}
}