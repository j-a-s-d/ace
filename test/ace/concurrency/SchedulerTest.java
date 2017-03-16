/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import org.junit.Assert;
import org.junit.Test;

public class SchedulerTest {

	final Scheduler _scheduler = new Scheduler(1, "test");
	final Job _job = new Job("job") {
		/*@Override*/ public void run() {
		}
	};

	@Test public void testGetName() {
		Assert.assertEquals("test", _scheduler.getName());
	}

	@Test public void testGetMaxThreadCount() {
		Assert.assertEquals(1, _scheduler.getMaxThreadCount());
	}

	@Test public void testSubmit_Job() {
		Assert.assertNotNull(_scheduler.submit(_job));
	}

	@Test public void testSubmit_Job_long() {
		Assert.assertNotNull(_scheduler.submit(_job, 10));
	}

	@Test public void testShutdown() {
		try {
			_scheduler.shutdown();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

}
