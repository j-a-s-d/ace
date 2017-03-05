/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import org.junit.Assert;
import org.junit.Test;

public class RangeTest {

	@Test public void testDownFrom() {
		final Integer[] a = new Integer[4];
		for (final int x : Range.downFrom(3)) {
			a[3 - x] = x;
		}
		Assert.assertArrayEquals(a, new Integer[] { 3, 2, 1, 0 });
	}

	@Test public void testUpTo() {
		final Integer[] a = new Integer[4];
		for (final int x : Range.upTo(3)) {
			a[x] = x;
		}
		Assert.assertArrayEquals(a, new Integer[] { 0, 1, 2, 3 });
	}

	@Test public void testFromTo() {
		final Integer[] a = new Integer[4];
		for (final int x : Range.fromTo(0, 3)) {
			a[x] = x;
		}
		Assert.assertArrayEquals(a, new Integer[] { 0, 1, 2, 3 });
		final Integer[] b = new Integer[4];
		for (final int x : Range.fromTo(3, 0)) {
			b[3 - x] = x;
		}
		Assert.assertArrayEquals(b, new Integer[] { 3, 2, 1, 0 });
	}

	@Test public void testIterator() {
		final Integer[] a = new Integer[4];
		for (final int x : Range.fromTo(0, 3)) {
			a[x] = x;
		}
		Assert.assertArrayEquals(a, new Integer[] { 0, 1, 2, 3 });
	}

	@Test public void testIn() {
		final Range range = Range.fromTo(0, 3);
		Assert.assertFalse(range.in(-1));
		Assert.assertTrue(range.in(0));
		Assert.assertTrue(range.in(1));
		Assert.assertTrue(range.in(2));
		Assert.assertTrue(range.in(3));
		Assert.assertFalse(range.in(4));
	}

	@Test public void testGetLowerBound() {
		Assert.assertEquals(0, Range.fromTo(0, 3).getLowerBound());
		Assert.assertEquals(0, Range.fromTo(3, 0).getLowerBound());
	}

	@Test public void testGetUpperBound() {
		Assert.assertEquals(3, Range.fromTo(0, 3).getUpperBound());
		Assert.assertEquals(3, Range.fromTo(3, 0).getUpperBound());
	}

	@Test public void testCardinality() {
		Assert.assertEquals(4, Range.fromTo(0, 3).cardinality());
	}

	@Test public void testToArray() {
		Assert.assertArrayEquals(new int[] { 0, 1, 2, 3 }, Range.fromTo(0, 3).toArray());
	}

}
