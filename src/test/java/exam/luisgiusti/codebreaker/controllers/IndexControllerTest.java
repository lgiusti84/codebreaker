package exam.luisgiusti.codebreaker.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IndexControllerTest {
	private IndexController controller;

	@Before
	public void setUp() {
		controller = new IndexController();
	}

	@Test
	public void getIndex() {
		String viewName = controller.getIndex();
		assertEquals("index.html", viewName);
	}

}