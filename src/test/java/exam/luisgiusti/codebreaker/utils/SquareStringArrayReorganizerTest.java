package exam.luisgiusti.codebreaker.utils;


import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class SquareStringArrayReorganizerTest {

	@Test
	public void testRotate() {
		String[] initial = {"123", "456", "789"};

		List<String> result = SquareStringArrayReorganizer.rotate(initial);

		assertThat(result, hasSize(3));
		assertThat(result, anyOf(
				containsInAnyOrder("369", "258", "147"),
				containsInAnyOrder("963", "852", "741")
		));
	}

	@Test
	public void testDiagonalize() {
		String[] initial = {"123", "456", "789"};

		List<String> result = SquareStringArrayReorganizer.diagonalize(initial);

		assertThat(result, hasSize(10));
		assertThat(result, anyOf(
				containsInAnyOrder("159", "26", "3", "48", "7", "357", "68", "9", "24", "1"),
				containsInAnyOrder("159", "26", "3", "48", "7", "753", "86", "9", "42", "1"),
				containsInAnyOrder("951", "62", "3", "84", "7", "357", "68", "9", "24", "1"),
				containsInAnyOrder("951", "62", "3", "84", "7", "753", "86", "9", "42", "1")
		));
	}
}
