/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.randomness;

import org.junit.Assert;
import org.junit.Test;

public class GUIDTest {

	@Test public void testMake() {
		Assert.assertNotNull(GUID.make());
	}

	@Test public void testMakeAsString() {
		Assert.assertNotNull(GUID.makeAsString());
	}

}
