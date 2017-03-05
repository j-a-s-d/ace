/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import org.junit.Assert;
import org.junit.Test;

public class ImmutableArrayTest {

	@Test public void testAsNativeArray() {
		final Integer[] expected = new Integer[] { 1, 2, 3 };
		Assert.assertArrayEquals(expected, new ImmutableArray<Integer>(expected).asNativeArray());
	}

	@Test public void testNotEmpty() {
		Assert.assertEquals(false, new ImmutableArray<Integer>().hasContent());
		Assert.assertEquals(true, new ImmutableArray<Integer>(1, 2, 3).hasContent());
	}

	@Test public void testHas() {
		Assert.assertEquals(false, new ImmutableArray<Integer>().has(1));
		Assert.assertEquals(true, new ImmutableArray<Integer>(1, 2, 3).has(1));
	}

	@Test public void testHead() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		Assert.assertEquals(o1, new ImmutableArray<Object>(o1, o2).head());
	}

	@Test public void testTail() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		Assert.assertEquals(o2, new ImmutableArray<Object>(o1, o2).tail());
	}

	@Test public void testGet() {
		Assert.assertEquals("ABC", new ImmutableArray<String>("ABC", "DEF", "GHI").get(0));
	}

	@Test public void testSize() {
		Assert.assertEquals(3, new ImmutableArray<Integer>(1, 2, 3).size());
	}

	@Test public void testIterator() {
		int result = 0;
		for (final Integer i : new ImmutableArray<Integer>(1, 2, 3)) {
			result += i;
		}
		Assert.assertEquals(6, result);
	}

}
