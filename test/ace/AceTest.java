/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import org.junit.Assert;
import org.junit.Test;

public class AceTest {

	@Test public void testAssigned() {
		Assert.assertEquals(true, Ace.assigned(Ace.EMPTY_OBJECT));
		Assert.assertEquals(false, Ace.assigned(Ace.EMPTY_OBJECT, null));
		Assert.assertEquals(false, Ace.assigned(null, null, null));
		Assert.assertEquals(false, Ace.assigned(null));
		Assert.assertEquals(true, Ace.assigned());
		String[] test1 = null;
		Assert.assertEquals(false, Ace.assigned(test1));
		String[] test2 = {};
		Assert.assertEquals(true, Ace.assigned(test2));
		String[] test3 = {""};
		Assert.assertEquals(true, Ace.assigned(test3));
		String[] test4 = {null};
		Assert.assertEquals(false, Ace.assigned(test4));
		String[] test5 = {"", null};
		Assert.assertEquals(false, Ace.assigned(test5));
	}

	@Test public void testEnsure() {
		Assert.assertEquals(Ace.EMPTY_OBJECT, Ace.ensure(null, Ace.EMPTY_OBJECT));
	}

}
