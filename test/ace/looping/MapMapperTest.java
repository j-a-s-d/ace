/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class MapMapperTest {

	final Map<String, Integer> M012 = new HashMap<String, Integer>() {
		{
			put("A", 0);
			put("B", 1);
			put("C", 2);
		}
	};
	final Map<String, Integer> M123 = new HashMap<String, Integer>() {
		{
			put("A", 1);
			put("B", 2);
			put("C", 3);
		}
	};

	@Test public void testMap() {
		Assert.assertEquals(M123, new MapMapper<String, Integer>(M012) {
			public Integer treat(final Integer item) {
				return item + 1;
			}
		}.map());
	}

}
