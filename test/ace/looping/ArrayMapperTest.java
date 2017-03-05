/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import org.junit.Assert;
import org.junit.Test;

public class ArrayMapperTest {

	final Integer[] A012 = new Integer[] { 0, 1, 2 };
	final Integer[] A123 = new Integer[] { 1, 2, 3 };

	@Test public void testGet() {
		Assert.assertArrayEquals(A123, new ArrayMapper<Integer>(A012) {
			public Integer treat(final Integer item) {
				return item + 1;
			}
		}.map());
	}

}
