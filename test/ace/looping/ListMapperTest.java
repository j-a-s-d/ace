/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ListMapperTest {

	final List<Integer> L123 = new ArrayList<Integer>() {
		{
			add(1);
			add(2);
			add(3);
		}
	};
	final List<Integer> L012 = new ArrayList<Integer>() {
		{
			add(0);
			add(1);
			add(2);
		}
	};

	@Test public void testGet() {
		Assert.assertEquals(L123, new ListMapper<Integer>(L012) {
			public Integer treat(final Integer item) {
				return item + 1;
			}
		}.map());
	}

}
