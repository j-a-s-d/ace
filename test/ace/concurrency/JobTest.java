/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.interfaces.Decisor;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

public class JobTest {

	final Job _job = new Job("test") {
		/*@Override*/ public void run() {
		}
	};

	@Test public void testGetName() {
		Assert.assertEquals("test", _job.getName());
	}

	@Test public void testSetName() {
		Assert.assertEquals(_job, _job.setName("name"));
		Assert.assertEquals("name", _job.getName());
		Assert.assertEquals(_job, _job.setName("test"));
	}

	@Test public void testGetRunnable() {
		Assert.assertNotNull(_job.getRunnable());
	}

	@Test public void testSetRunnable() {
		Runnable r;
		Assert.assertNotNull(r = _job.getRunnable());
		Assert.assertEquals(_job, _job.setRunnable(Runnables.makeDummy()));
	}

	@Test public void testGetScheduledFuture() {
		Assert.assertNull(_job.getScheduledFuture());
	}

	@Test public void testSetScheduledFuture() {
		Assert.assertEquals(_job, _job.setScheduledFuture(_job.getScheduledFuture()));
	}

	@Test public void testCancel() {
		try {
			_job.cancel();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testCancelNow() {
		try {
			_job.cancelNow();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSetExecutionTime() {
		Assert.assertEquals(_job, _job.setExecutionTime(false, TimeUnit.SECONDS, 1));
	}

	@Test public void testIsPostScheduled() {
		Assert.assertFalse(_job.isPostScheduled());
	}

	@Test public void testGetTimeUnit() {
		Assert.assertEquals(TimeUnit.SECONDS, _job.getTimeUnit());
	}

	@Test public void testGetTimeAmount() {
		Assert.assertEquals(1, _job.getTimeAmount());
	}

	@Test public void testAllow() {
		Assert.assertTrue(_job.allow());
	}

	@Test public void testGetExecutionsCount() {
		Assert.assertEquals(0, _job.getExecutionsCount());
	}

	@Test public void testEachMilliseconds() {
		Assert.assertEquals(_job, _job.eachMilliseconds(1000));
	}

	@Test public void testEachSeconds() {
		Assert.assertEquals(_job, _job.eachSeconds(1));
	}

	@Test public void testEachMillisecondsAfterLastJobFinished() {
		Assert.assertEquals(_job, _job.eachMillisecondsAfterLastJobFinished(1000));
	}

	@Test public void testEachSecondsAfterLastJobFinished() {
		Assert.assertEquals(_job, _job.eachSecondsAfterLastJobFinished(1));
	}

	@Test public void testUntilDecision() {
		Assert.assertEquals(_job, _job.untilDecision(new Decisor() {
			/*@Override*/ public boolean decide(final Object... parameters) {
				return true;
			}
		}));
	}

	@Test public void testUntilTimes() {
		Assert.assertEquals(_job, _job.untilTimes(1));
	}

}
