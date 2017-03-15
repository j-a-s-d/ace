/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import org.junit.Assert;
import org.junit.Test;

public class SelfSpawnedTest {

	@Test public void testSomeMethod() {
		new SelfSpawned() {
			/*Override*/ public void run() {
				Assert.assertNotNull(this);
			}
		};
	}

}
