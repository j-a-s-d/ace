/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ListsTest {

	@Test public void testHasContent() {
		Assert.assertTrue(Lists.hasContent(Lists.make("hello", "world")));
	}

	@Test public void testMake() {
		Assert.assertNotNull(Lists.make("hello", "world"));
	}

	@Test public void testMakeSynchronized() {
		Assert.assertNotNull(Lists.makeSynchronized("hello", "world"));
	}

	@Test public void testMakeLinked() {
		Assert.assertNotNull(Lists.makeLinked("hello", "world"));
	}

	@Test public void testMakeSynchronizedLinked() {
		Assert.assertNotNull(Lists.makeSynchronizedLinked("hello", "world"));
	}

	@Test public void testCombine() {
		Assert.assertEquals(4, Lists.combine(Lists.make("hello", "world"), Lists.make("test", "testing")).size());
	}

	@Test public void testCombineLinked() {
		Assert.assertEquals(4, Lists.combineLinked(Lists.make("hello", "world"), Lists.make("test", "testing")).size());
	}

	@Test public void testHasSameValueItems() {
		Assert.assertTrue(Lists.hasSameValueItems(Lists.make("abc", "abc", "abc")));
	}

	@Test public void testGet() {
		final List list = Lists.make("hello", "world");
		Assert.assertEquals("hello", Lists.get(list, 0));
		Assert.assertEquals("world", Lists.get(list, 1));
		Assert.assertEquals(null, Lists.get(list, 2));
	}

	@Test public void testLast() {
		Assert.assertEquals("world", Lists.last(Lists.make("hello", "world")));
	}

	@Test public void testFirstAndLast() {
		Assert.assertEquals(2, Lists.firstAndLast(Lists.make("hello", "abc", "world")).size());
	}

	@Test public void testFirstAndLastSynchronized() {
		Assert.assertEquals(2, Lists.firstAndLastSynchronized(Lists.make("hello", "abc", "world")).size());
	}

	@Test public void testFirstAndLastLinked() {
		Assert.assertEquals(2, Lists.firstAndLastLinked(Lists.make("hello", "abc", "world")).size());
	}

	@Test public void testFirstAndLastSynchronizedLinked() {
		Assert.assertEquals(2, Lists.firstAndLastSynchronizedLinked(Lists.make("hello", "abc", "world")).size());
	}

	@Test public void testExtractSublist() {
		final List<Integer> expected = Lists.make(1, 2, 3);
		int i = 0;
		for (final Integer x : Lists.extractSublist(Lists.make(0, 1, 2, 3, 4, 5), 1, 3)) {
			Assert.assertEquals(expected.get(i++), x);
		}
	}

	@Test public void testHas_List_GenericType() {
		Assert.assertTrue(Lists.has(Lists.make(1, 2, 3), 1, 2));
		Assert.assertFalse(Lists.has(Lists.make(4, 5, 6), 7, 8, 9));
	}

	@Test public void testHas_List_Collection() {
		Assert.assertTrue(Lists.has(Lists.make(1, 2, 3), Lists.make(1, 2)));
		Assert.assertFalse(Lists.has(Lists.make(4, 5, 6), Lists.make(7, 8, 9)));
	}

}
