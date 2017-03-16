/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import org.junit.Assert;
import org.junit.Test;

public class SerialSchedulerTest {

	@Test public void test() {
		Assert.assertNotNull(new SerialScheduler("serial0"));
	}

}
