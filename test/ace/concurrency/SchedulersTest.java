/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import org.junit.Assert;
import org.junit.Test;

public class SchedulersTest {

	private final Job _dummyJob = new Job("dummyJob") {
		/*@Override*/ public void run() {
		}
	};

	@Test public void testCreateSerialScheduler() {
		Assert.assertTrue(Schedulers.createSerialScheduler("serial1"));
	}

	@Test public void testCreateParallelScheduler() {
		Assert.assertTrue(Schedulers.createParallelScheduler("parallel1", 10));
	}

	@Test public void testSubmit_String_Job() {
		Assert.assertTrue(Schedulers.createSerialScheduler("serial2"));
		Assert.assertTrue(Schedulers.submit("serial2", _dummyJob));
	}

	@Test public void testSubmit_3args() {
		Assert.assertTrue(Schedulers.createSerialScheduler("serial3"));
		Assert.assertTrue(Schedulers.submit("serial3", _dummyJob, 100));
	}

	@Test public void testShutdown() {
		Assert.assertFalse(Schedulers.shutdown("inexistent"));
		Assert.assertTrue(Schedulers.createSerialScheduler("serial4"));
		Assert.assertTrue(Schedulers.shutdown("serial4"));
	}

	@Test public void testShutdownAll() {
		try {
			Schedulers.shutdownAll();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

}
