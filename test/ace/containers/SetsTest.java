/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import java.util.LinkedHashSet;
import org.junit.Assert;
import org.junit.Test;

public class SetsTest {

	final LinkedHashSet<String> _set = new LinkedHashSet() {
		{
			add("A");
			add("B");
			add("C");
		}
	};

	@Test public void testHasContent() {
		Assert.assertTrue(Sets.hasContent(_set));
	}

	@Test public void testMake() {
		Assert.assertNotNull(Sets.make(1, 2, 3));
	}

	@Test public void testMakeSynchronized() {
		Assert.assertNotNull(Sets.makeSynchronized(1, 2, 3));
	}

	@Test public void testMakeLinked() {
		Assert.assertNotNull(Sets.makeLinked(1, 2, 3));
	}

	@Test public void testMakeSynchronizedLinked() {
		Assert.assertNotNull(Sets.makeSynchronizedLinked(1, 2, 3));
	}

	@Test public void testCombine() {
		Assert.assertEquals(4, Sets.combine(Sets.make(1, 2), Sets.make(3, 4)).size());
	}

	@Test public void testCombineLinked() {
		Assert.assertEquals(4, Sets.combineLinked(Sets.make(1, 2), Sets.make(3, 4)).size());
	}

	@Test public void testMakeTree_0args() {
		Assert.assertNotNull(Sets.makeTree());
	}

	@Test public void testMakeTree_Set() {
		Assert.assertNotNull(Sets.makeTree(Sets.makeTree()));
	}

	@Test public void testMakeSynchronizedTree_0args() {
		Assert.assertNotNull(Sets.makeSynchronizedTree());
	}

	@Test public void testMakeSynchronizedTree_Set() {
		Assert.assertNotNull(Sets.makeSynchronizedTree(Sets.makeTree()));
	}

	@Test public void testGetAsStringArray() {
		Assert.assertArrayEquals(new String[] { "A", "B", "C" }, Sets.getAsStringArray(_set));
	}

	@Test public void testHas_Set_GenericType() {
		Assert.assertTrue(Sets.has(_set, "A", "B"));
		Assert.assertFalse(Sets.has(_set, "D", "E"));
	}

	@Test public void testHas_Set_Collection() {
		Assert.assertTrue(Sets.has(_set, Sets.make("A", "B")));
		Assert.assertFalse(Sets.has(_set, Sets.make("D", "E")));
	}

}
