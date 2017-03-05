/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import org.junit.Assert;
import org.junit.Test;

public class LongsTest {

	@Test public void testAdd() {
		Assert.assertEquals(10L, Longs.add(1L, 2L, 3L, 4L));
	}

	@Test public void testSubtract() {
		final long l = 3L;
		Assert.assertEquals(-3L, Longs.subtract(l));
		Assert.assertEquals(0L, Longs.subtract(l, l));
		Assert.assertEquals(-3L, Longs.subtract(l, l, l));
		Assert.assertEquals(-6L, Longs.subtract(l, l, l, l));
	}

	@Test public void testMax() {
		Assert.assertEquals(2L, Longs.max(1L, 2L));
	}

	@Test public void testMin() {
		Assert.assertEquals(1L, Longs.min(1L, 2L));
	}

	@Test public void testBoxedAdd() {
		Assert.assertEquals(new Long(1), Longs.boxedAdd(1L));
		Assert.assertEquals(new Long(10), Longs.boxedAdd(1L, 2L, 3L, 4L));
	}

	@Test public void testBoxedSubtract() {
		final Long l = 3L;
		Assert.assertEquals(new Long(-3), Longs.boxedSubtract(l));
		Assert.assertEquals(new Long(0), Longs.boxedSubtract(l, l));
		Assert.assertEquals(new Long(-3), Longs.boxedSubtract(l, l, l));
		Assert.assertEquals(new Long(-6), Longs.boxedSubtract(l, l, l, l));
	}

	@Test public void testBoxedMax() {
		Assert.assertEquals(new Long(2), Longs.boxedMax(1L, 2L));
	}

	@Test public void testBoxedMin() {
		Assert.assertEquals(new Long(1), Longs.boxedMin(1L, 2L));
	}

	@Test public void testIsEven() {
		Assert.assertTrue(Longs.isEven(2));
		Assert.assertFalse(Longs.isEven(1));
	}

	@Test public void testIsOdd() {
		Assert.assertTrue(Longs.isOdd(1));
		Assert.assertFalse(Longs.isOdd(2));
	}

	@Test public void testIsDivisibleBy() {
		Assert.assertTrue(Longs.isDivisibleBy(4, 2));
		Assert.assertFalse(Longs.isDivisibleBy(5, 3));
	}

	@Test public void testBoxedIsEven() {
		Assert.assertTrue(Longs.boxedIsEven(new Long(2)));
		Assert.assertFalse(Longs.boxedIsEven(new Long(1)));
		Assert.assertFalse(Longs.boxedIsEven(null));
	}

	@Test public void testBoxedIsOdd() {
		Assert.assertTrue(Longs.boxedIsOdd(new Long(1)));
		Assert.assertFalse(Longs.boxedIsOdd(new Long(2)));
		Assert.assertFalse(Longs.boxedIsOdd(null));
	}

	@Test public void testBoxedIsDivisibleBy() {
		Assert.assertTrue(Longs.boxedIsDivisibleBy(new Long(4), new Long(2)));
		Assert.assertFalse(Longs.boxedIsDivisibleBy(new Long(5), new Long(3)));
		Assert.assertFalse(Longs.boxedIsDivisibleBy(new Long(5), null));
		Assert.assertFalse(Longs.boxedIsDivisibleBy(null, new Long(3)));
		Assert.assertFalse(Longs.boxedIsDivisibleBy(null, null));
	}

	@Test public void testParse_String_long() {
		Assert.assertEquals(45L, Longs.parse("45", 0L));
		Assert.assertEquals(0L, Longs.parse("abc", 0L));
	}

	@Test public void testParse_3args() {
		Assert.assertEquals(254L, Longs.parse("FE", 16, 0L));
		Assert.assertEquals(0L, Longs.parse("abc", 10, 0L));
	}

	@Test public void testBoxedParse_String_Long() {
		Assert.assertEquals(new Long(123), Longs.boxedParse("123", 0L));
		Assert.assertEquals(new Long(0), Longs.boxedParse("abc", new Long(0)));
	}

	@Test public void testBoxedParse_3args() {
		Assert.assertEquals(new Long(254), Longs.boxedParse("FE", 16, 0L));
		Assert.assertEquals(new Long(0), Longs.boxedParse("abc", 10, new Long(0)));
	}

}
