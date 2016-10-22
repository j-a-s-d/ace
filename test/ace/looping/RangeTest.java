/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

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

}