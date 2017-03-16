/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.interfaces.Evaluable;
import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.Test;

public class ThreadsTest {

	@Test public void testGetExecutorService() {
		try {
			Threads.getExecutorService(Threads.DEFAULT);
		} catch (final Exception e) {
			Assert.fail();
		}
		Assert.assertNotNull(Threads.getExecutorService(Threads.SINGLE));
		Assert.assertNotNull(Threads.getExecutorService(Threads.UNLIMITED));
	}

	@Test public void testGetThreadStackTraceElement() {
		Assert.assertNotNull(Threads.getThreadStackTraceElement(Thread.currentThread(), 0));
	}

	@Test public void testGetCurrentThreadStackTraceElement() {
		Assert.assertNotNull(Threads.getCurrentThreadStackTraceElement(0));
	}

	@Test public void testGetThreadByName() {
		Assert.assertEquals(Thread.currentThread(), Threads.getThreadByName(Thread.currentThread().getName()));
	}

	@Test public void testExistsThread() {
		Assert.assertTrue(Threads.existsThread(Thread.currentThread().getName()));
	}

	@Test public void testMakeDummyRunnable() {
		Assert.assertNotNull(Threads.makeDummyRunnable());
	}

	@Test public void testCreateWaiter() {
		Assert.assertNotNull(Threads.createWaiter(10, null));
		Assert.assertNotNull(Threads.createWaiter(10, new Callable<Void>() {
			/*@Override*/ public Void call() throws Exception {
				return null;
			}
		}));
	}

	@Test public void testDelayedEvaluation() {
		try {
			Assert.assertNull(Threads.delayedEvaluation(10, null, 1, 2, 3));
		} catch (final Exception e) {
			Assert.fail();
		}
		try {
			Assert.assertTrue(Threads.delayedEvaluation(10, new Evaluable() {
				public Object evaluate(final Object... parameters) throws Exception {
					return parameters.length == 3;
				}
			}, 1, 2, 3));
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testDelayedSandboxedEvaluation() {
		Assert.assertNull(Threads.delayedSandboxedEvaluation(10, true, null, 1, 2, 3));
		Assert.assertTrue(Threads.delayedSandboxedEvaluation(10, false, new Evaluable() {
			public Object evaluate(final Object... parameters) throws Exception {
				return parameters.length == 3;
			}
		}, 1, 2, 3));
		Assert.assertFalse(Threads.delayedSandboxedEvaluation(10, false, new Evaluable() {
			public Object evaluate(final Object... parameters) throws Exception {
				throw new Exception();
			}
		}, 1, 2, 3));
	}

	@Test public void testDelayedRun() {
		try {
			Threads.delayedRun(10, Threads.makeDummyRunnable());
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testDelayedSpawn_long_Runnable() {
		try {
			Threads.delayedSpawn(10, Threads.makeDummyRunnable());
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testDelayedSpawn_3args() {
		try {
			Threads.delayedSpawn(10, Threads.makeDummyRunnable(), "dummy");
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testIsolatedSpawn_Runnable() {
		try {
			Threads.isolatedSpawn(Threads.makeDummyRunnable());
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testIsolatedSpawn_Runnable_String() {
		try {
			Threads.isolatedSpawn(Threads.makeDummyRunnable(), "dummy");
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testIsolatedSpawn_int_Runnable() {
		try {
			Threads.isolatedSpawn(2, Threads.makeDummyRunnable());
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testIsolatedSpawn_3args() {
		try {
			Threads.isolatedSpawn(2, Threads.makeDummyRunnable(), 10);
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testIsolatedSpawn_4args() {
		try {
			Threads.isolatedSpawn(2, Threads.makeDummyRunnable(), 10, "prefix");
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSpawn_Runnable() {
		try {
			Threads.spawn(Threads.makeDummyRunnable());
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSpawn_Runnable_String() {
		try {
			Threads.spawn(Threads.makeDummyRunnable(), "dummy");
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSpawn_int_Runnable() {
		try {
			Threads.spawn(2, Threads.makeDummyRunnable());
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSpawn_3args() {
		try {
			Threads.spawn(2, Threads.makeDummyRunnable(), 10);
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSpawn_4args() {
		try {
			Threads.spawn(2, Threads.makeDummyRunnable(), 10, "prefix");
		} catch (final Exception e) {
			Assert.fail();
		}
	}

}
