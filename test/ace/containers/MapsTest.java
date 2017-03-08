/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.text.Strings;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Assert;
import org.junit.Test;

public class MapsTest {

	@Test public void testIsAssignedNonEmptyMap() {
		final Map<String, Integer> map = Maps.make();
		map.put("A", 1);
		Assert.assertTrue(Maps.isAssignedNonEmptyMap(map));
	}

	@Test public void testMake_0args() {
		Assert.assertNotNull(Maps.make());
	}

	@Test public void testMake_int() {
		Assert.assertNotNull(Maps.make(100));
	}

	@Test public void testMake_Map() {
		Assert.assertNotNull(Maps.make(Maps.make()));
	}

	@Test public void testMakeSynchronized_0args() {
		Assert.assertNotNull(Maps.makeSynchronized());
	}

	@Test public void testMakeSynchronized_int() {
		Assert.assertNotNull(Maps.makeSynchronized(100));
	}

	@Test public void testMakeSynchronized_Map() {
		Assert.assertNotNull(Maps.makeSynchronized(Maps.make()));
	}

	@Test public void testMakeLinked_0args() {
		Assert.assertNotNull(Maps.makeLinked());
	}

	@Test public void testMakeLinked_int() {
		Assert.assertNotNull(Maps.makeLinked(100));
	}

	@Test public void testMakeLinked_Map() {
		Assert.assertNotNull(Maps.makeLinked(Maps.make()));
	}

	@Test public void testMakeSynchronizedLinked_0args() {
		Assert.assertNotNull(Maps.makeSynchronizedLinked());
	}

	@Test public void testMakeSynchronizedLinked_int() {
		Assert.assertNotNull(Maps.makeSynchronizedLinked(100));
	}

	@Test public void testMakeSynchronizedLinked_Map() {
		Assert.assertNotNull(Maps.makeSynchronizedLinked(Maps.make()));
	}

	@Test public void testMakeWeak_0args() {
		Assert.assertNotNull(Maps.makeWeak());
	}

	@Test public void testMakeWeak_int() {
		Assert.assertNotNull(Maps.makeWeak(1));
	}

	@Test public void testMakeWeak_Map() {
		Assert.assertNotNull(Maps.makeWeak(Maps.make()));
	}

	@Test public void testMakeSynchronizedWeak_0args() {
		Assert.assertNotNull(Maps.makeSynchronizedWeak());
	}

	@Test public void testMakeSynchronizedWeak_int() {
		Assert.assertNotNull(Maps.makeSynchronizedWeak(100));
	}

	@Test public void testMakeSynchronizedWeak_Map() {
		Assert.assertNotNull(Maps.makeSynchronizedWeak(Maps.make()));
	}

	@Test public void testMakeTree_0args() {
		Assert.assertNotNull(Maps.makeTree());
	}

	@Test public void testMakeTree_Map() {
		Assert.assertNotNull(Maps.makeTree(Maps.make()));
	}

	@Test public void testMakeSynchronizedTree_0args() {
		Assert.assertNotNull(Maps.makeSynchronizedTree());
	}

	@Test public void testMakeSynchronizedTree_Map() {
		Assert.assertNotNull(Maps.makeSynchronizedTree(Maps.make()));
	}

	@Test public void testMakeConcurrent_0args() {
		Assert.assertNotNull(Maps.makeConcurrent());
	}

	@Test public void testMakeConcurrent_int() {
		Assert.assertNotNull(Maps.makeConcurrent(1));
	}

	@Test public void testMakeConcurrent_Map() {
		Assert.assertNotNull(Maps.makeConcurrent(Maps.make()));
	}

	@Test public void testGetStringKeysAsStringArray() {
		final Map<String, Integer> map = Maps.make();
		map.put("A", 0);
		map.put("B", 1);
		map.put("C", 2);
		final String[] values = Maps.getStringKeysAsStringArray(map);
		Assert.assertTrue(Strings.in("A", values) && Strings.in("B", values) && Strings.in("C", values));
	}

	@Test public void testHasKeys_Map_GenericType() {
		final Map<String, Integer> map = Maps.make();
		map.put("A", 0);
		map.put("B", 1);
		map.put("C", 2);
		Assert.assertTrue(Maps.hasKeys(map, new String[] { "A", "B", "C" }));
	}

	@Test public void testHasKeys_Map_Collection() {
		final Map<String, Integer> map = Maps.make();
		map.put("A", 0);
		map.put("B", 1);
		map.put("C", 2);
		Assert.assertTrue(Maps.hasKeys(map, new ArrayList() {{ add("A"); add("B"); add("C"); }}));
	}

	@Test public void testSortByValues() {
		final Map<String, Integer> map = Maps.make();
		map.put("A", 11);
		map.put("B", 99);
		map.put("C", 40);
		map.put("D", 10);
		map.put("E", 95);
		map.put("F", 33);
		map.put("G", 42);
		final Map<String, Integer> ordered = Maps.sortByValues(map);
		final String[] keys = new String[] { "D", "A", "F", "C", "G", "E", "B" };
		int i = 0;
		for (final Entry<String, Integer> entry : ordered.entrySet()) {
			Assert.assertTrue(entry.getKey().equals(keys[i++]));
		}
	}

}
