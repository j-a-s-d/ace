/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.interfaces.ExceptionsMonitor;
import org.junit.Assert;
import org.junit.Test;

public class GlobalExceptionHandlerTest {

	@Test public void testGetExceptionsHistoryMaximum() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		Assert.assertEquals(100, geh.getExceptionsHistoryMaximum());
	}

	@Test public void testSetExceptionsHistoryMaximum() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setExceptionsHistoryMaximum(42);
		Assert.assertEquals(42, geh.getExceptionsHistoryMaximum());
	}

	@Test public void testRegisterAsDefaultUncaughtExceptionHandler() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		Assert.assertTrue(geh.registerAsDefaultUncaughtExceptionHandler());
	}

	@Test public void testUnregisterAsDefaultUncaughtExceptionHandler() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		Assert.assertTrue(geh.registerAsDefaultUncaughtExceptionHandler());
		Assert.assertTrue(geh.unregisterAsDefaultUncaughtExceptionHandler());
	}

	@Test public void testSetLastException() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		Assert.assertNotNull(geh.getLastException());
	}

	@Test public void testGetLastException() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		final Exception e = new Exception("123");
		geh.setLastException(e);
		Assert.assertEquals(e, geh.getLastException());
	}

	@Test public void testHadException() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		Assert.assertTrue(geh.hadException());
	}

	@Test public void testGetExceptionsCount() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		geh.setLastException(new Exception() {});
		Assert.assertEquals(2, geh.getExceptionsCount());
	}

	@Test public void testForgetExceptionsThrown() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		geh.setLastException(new Exception() {});
		if (geh.hadException()) {
			geh.forgetExceptionsThrown();
			Assert.assertEquals(0, geh.getExceptionsCount());
		} else {
			Assert.fail();
		}
	}

	@Test public void testForgetLastException() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		if (geh.hadException()) {
			geh.forgetLastException();
			Assert.assertFalse(geh.hadException());
		} else {
			Assert.fail();
		}
	}

	@Test public void testGetLastExceptionThread() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		Assert.assertEquals(Thread.currentThread(), geh.getLastExceptionThread());
	}

	@Test public void testGetLastExceptionTimestamp() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		Assert.assertEquals(-1, geh.getLastExceptionTimestamp());
		geh.setLastException(new Throwable() {});
		Assert.assertNotEquals(-1, geh.getLastExceptionTimestamp());
	}

	@Test public void testGetExceptionsThrown() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setLastException(new Throwable() {});
		geh.setLastException(new Exception() {});
		Assert.assertEquals(2, geh.getExceptionsThrown().size());
	}

	@Test public void testGetExceptionsMonitor() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		Assert.assertNull(geh.getExceptionsMonitor());
	}

	boolean resultSetExceptionsMonitor = false;
	@Test public void testSetExceptionsMonitor() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler(false);
		geh.setExceptionsMonitor(new ExceptionsMonitor() {
			public void onExceptionCatched(final Thread thread, final Throwable throwable, final long timestamp) {
				resultSetExceptionsMonitor = true;
			}
		});
		Assert.assertNotNull(geh.getExceptionsMonitor());
		geh.setLastException(new Exception("test") {});
		Assert.assertTrue(resultSetExceptionsMonitor);
	}

}