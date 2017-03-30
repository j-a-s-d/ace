/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import org.junit.Assert;
import org.junit.Test;

public class RunnablesTest {

	@Test public void testMakeDummy() {
		Assert.assertNotNull(Runnables.makeDummy());
	}

	boolean flag = false;
	@Test public void testRun() {
		Runnables.run(new Runnable() {
			/*@Override*/ public void run() {
				flag = true;
			}
		});
		Assert.assertTrue(flag);
	}

}
