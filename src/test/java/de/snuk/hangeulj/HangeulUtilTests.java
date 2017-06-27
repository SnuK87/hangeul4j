package de.snuk.hangeulj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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
	String a = "각";
	String b = "쇒";
	String c = "힣";

	String z = "test";

	a = HangeulUtil.normalize(a.charAt(0));
	b = HangeulUtil.normalize(b.charAt(0));
	c = HangeulUtil.normalize(c.charAt(0));

	z = HangeulUtil.normalize(z.charAt(0));

	assertThat(HangeulUtil.toJamo(a.charAt(0))).isEqualTo(0);
	assertThat(HangeulUtil.toJamo(a.charAt(1))).isEqualTo(0);
	assertThat(HangeulUtil.toJamo(a.charAt(2))).isEqualTo(1);

	assertThat(HangeulUtil.toJamo(b.charAt(0))).isEqualTo(9);
	assertThat(HangeulUtil.toJamo(b.charAt(1))).isEqualTo(10);
	assertThat(HangeulUtil.toJamo(b.charAt(2))).isEqualTo(14);

	assertThat(HangeulUtil.toJamo(c.charAt(0))).isEqualTo(18);
	assertThat(HangeulUtil.toJamo(c.charAt(1))).isEqualTo(20);
	assertThat(HangeulUtil.toJamo(c.charAt(2))).isEqualTo(27);

	assertThat(HangeulUtil.toJamo(z.charAt(0))).isEqualTo(-1);
    }

    @Test
    public void testComposeSyllable() {
	char expected = '각';
	char actual = HangeulUtil.composeSyllable(0, 0, 1);

	assertThat(actual).isEqualTo(expected);
    }
}
