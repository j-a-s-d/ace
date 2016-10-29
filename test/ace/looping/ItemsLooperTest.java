/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.arrays.GenericArrays;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class ItemsLooperTest {

	@Test public void testArray() {
		final Integer[] a = new Integer[] { 0, 1, 2 };
		new ItemsLooper<Integer>(a) {
			public void process(final Integer item) {
				Assert.assertTrue(GenericArrays.contains(a, item));
			}
		};
	}

	@Test public void testCollection() {
		final ArrayList<Integer> a = new ArrayList<Integer>() {{ add(0); add(1); add(2); }};
		new ItemsLooper<Integer>(a) {
			public void process(final Integer item) {
				Assert.assertTrue(a.contains(item));
			}
		};
	}

}
