/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import org.junit.Assert;
import org.junit.Test;

public class IntegersTest {

	@Test public void testAdd() {
		Assert.assertEquals(10, Integers.add(1, 2, 3, 4));
	}

	@Test public void testSubtract() {
		final int i = 3;
		Assert.assertEquals(-3, Integers.subtract(i));
		Assert.assertEquals(0, Integers.subtract(i, i));
		Assert.assertEquals(-3, Integers.subtract(i, i, i));
		Assert.assertEquals(-6, Integers.subtract(i, i, i, i));
	}

	@Test public void testMax() {
		Assert.assertEquals(2, Integers.max(1, 2));
	}

	@Test public void testMin() {
		Assert.assertEquals(1, Integers.min(1, 2));
	}

	@Test public void testBoxedAdd() {
		Assert.assertEquals(new Integer(1), Integers.boxedAdd(1));
		Assert.assertEquals(new Integer(10), Integers.boxedAdd(1, 2, 3, 4));
	}

	@Test public void testBoxedSubtract() {
		final Integer i = 3;
		Assert.assertEquals(new Integer(-3), Integers.boxedSubtract(i));
		Assert.assertEquals(new Integer(0), Integers.boxedSubtract(i, i));
		Assert.assertEquals(new Integer(-3), Integers.boxedSubtract(i, i, i));
		Assert.assertEquals(new Integer(-6), Integers.boxedSubtract(i, i, i, i));
	}

	@Test public void testBoxedMax() {
		Assert.assertEquals(new Integer(2), Integers.boxedMax(1, 2));
	}

	@Test public void testBoxedMin() {
		Assert.assertEquals(new Integer(1), Integers.boxedMin(1, 2));
	}

	@Test public void testIsEven() {
		Assert.assertTrue(Integers.isEven(2));
		Assert.assertFalse(Integers.isEven(1));
	}

	@Test public void testIsOdd() {
		Assert.assertTrue(Integers.isOdd(1));
		Assert.assertFalse(Integers.isOdd(2));
	}

	@Test public void testIsDivisibleBy() {
		Assert.assertTrue(Integers.isDivisibleBy(4, 2));
		Assert.assertFalse(Integers.isDivisibleBy(5, 3));
	}

	@Test public void testBoxedIsEven() {
		Assert.assertTrue(Integers.boxedIsEven(new Integer(2)));
		Assert.assertFalse(Integers.boxedIsEven(new Integer(1)));
		Assert.assertFalse(Integers.boxedIsEven(null));
	}

	@Test public void testBoxedIsOdd() {
		Assert.assertTrue(Integers.boxedIsOdd(new Integer(1)));
		Assert.assertFalse(Integers.boxedIsOdd(new Integer(2)));
		Assert.assertFalse(Integers.boxedIsOdd(null));
	}

	@Test public void testBoxedIsDivisibleBy() {
		Assert.assertTrue(Integers.boxedIsDivisibleBy(new Integer(4), new Integer(2)));
		Assert.assertFalse(Integers.boxedIsDivisibleBy(new Integer(5), new Integer(3)));
		Assert.assertFalse(Integers.boxedIsDivisibleBy(new Integer(5), null));
		Assert.assertFalse(Integers.boxedIsDivisibleBy(null, new Integer(3)));
		Assert.assertFalse(Integers.boxedIsDivisibleBy(null, null));
	}

	@Test public void testParse_String_int() {
		Assert.assertEquals(45, Integers.parse("45", 0));
		Assert.assertEquals(0, Integers.parse("abc", 0));
	}

	@Test public void testParse_3args() {
		Assert.assertEquals(254, Integers.parse("FE", 16, 0));
		Assert.assertEquals(0, Integers.parse("abc", 10, 0));
	}

	@Test public void testBoxedParse_String_Integer() {
		Assert.assertEquals(new Integer(123), Integers.boxedParse("123", 0));
		Assert.assertEquals(new Integer(0), Integers.boxedParse("abc", new Integer(0)));
	}

	@Test public void testBoxedParse_3args() {
		Assert.assertEquals(new Integer(254), Integers.boxedParse("FE", 16, 0));
		Assert.assertEquals(new Integer(0), Integers.boxedParse("abc", 10, new Integer(0)));
	}

}
