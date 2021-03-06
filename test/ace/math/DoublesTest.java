/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import org.junit.Assert;
import org.junit.Test;

public class DoublesTest {

	@Test public void testAdd() {
		Assert.assertEquals(11d, Doubles.add(1.1d, 2.2d, 3.3d, 4.4d), 0d);
	}

	@Test public void testSubtract() {
		final double d = 3.3d;
		Assert.assertEquals(-3.3d, Doubles.subtract(d), 0d);
		Assert.assertEquals(0d, Doubles.subtract(d, d), 0d);
		Assert.assertEquals(-3.3d, Doubles.subtract(d, d, d), 0d);
		Assert.assertEquals(-6.6d, Doubles.subtract(d, d, d, d), 0d);
	}

	@Test public void testMax() {
		Assert.assertEquals(2.2d, Doubles.max(1.1d, 2.2d), 0d);
	}

	@Test public void testMin() {
		Assert.assertEquals(1.1d, Doubles.min(1.1d, 2.2d), 0d);
	}

	@Test public void testBoxedAdd() {
		Assert.assertEquals(new Double(1.1), Doubles.boxedAdd(1.1d));
		Assert.assertEquals(new Double(11), Doubles.boxedAdd(1.1d, 2.2d, 3.3d, 4.4d));
	}

	@Test public void testBoxedSubtract() {
		final Double d = 3.3d;
		Assert.assertEquals(new Double(-3.3), Doubles.boxedSubtract(d));
		Assert.assertEquals(new Double(0), Doubles.boxedSubtract(d, d));
		Assert.assertEquals(new Double(-3.3), Doubles.boxedSubtract(d, d, d));
		Assert.assertEquals(new Double(-6.6), Doubles.boxedSubtract(d, d, d, d));
	}

	@Test public void testBoxedMax() {
		Assert.assertEquals(new Double(2.2), Doubles.boxedMax(1.1d, 2.2d));
	}

	@Test public void testBoxedMin() {
		Assert.assertEquals(new Double(1.1), Doubles.boxedMin(1.1d, 2.2d));
	}

	@Test public void testIsDivisibleBy() {
		Assert.assertTrue(Doubles.isDivisibleBy(4d, 2d));
		Assert.assertFalse(Doubles.isDivisibleBy(5d, 3d));
	}

	@Test public void testBoxedIsDivisibleBy() {
		Assert.assertTrue(Doubles.boxedIsDivisibleBy(new Double(4), new Double(2)));
		Assert.assertFalse(Doubles.boxedIsDivisibleBy(new Double(5), new Double(3)));
		Assert.assertFalse(Doubles.boxedIsDivisibleBy(new Double(5), null));
		Assert.assertFalse(Doubles.boxedIsDivisibleBy(null, new Double(3)));
		Assert.assertFalse(Doubles.boxedIsDivisibleBy(null, null));
	}

	@Test public void testParse() {
		Assert.assertEquals(4.56d, Doubles.parse("4.56", 0d), 0d);
		Assert.assertEquals(0d, Doubles.parse("abc", 0d), 0d);
	}

	@Test public void testBoxedParse() {
		Assert.assertEquals(new Double(1.23d), Doubles.boxedParse("1.23", 0d), 0d);
		Assert.assertEquals(new Double(0), Doubles.boxedParse("abc", new Double(0)), 0d);
	}

}
