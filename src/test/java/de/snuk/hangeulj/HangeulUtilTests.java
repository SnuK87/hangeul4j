package de.snuk.hangeulj;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HangeulUtilTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNormalize() {
		String input = "긱";
		String expected = "긱";
		String actual = HangeulUtil.normalize(input.toCharArray()[0]);
		assertEquals(expected, actual);
	}

	@Test
	public void testToJamo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testComposeSyllable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testConvertFinalToInitial() {
//		HangeulUtil.convertFinalToInitial(input)
		fail("Not yet implemented"); // TODO
	}

}
