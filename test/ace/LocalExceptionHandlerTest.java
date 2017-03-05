/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.interfaces.ExceptionsMonitor;
import org.junit.Assert;
import org.junit.Test;

public class LocalExceptionHandlerTest {

	@Test public void testGetExceptionsHistoryMaximum() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		Assert.assertEquals(100, geh.getExceptionsHistoryMaximum());
	}

	@Test public void testSetExceptionsHistoryMaximum() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setExceptionsHistoryMaximum(42);
		Assert.assertEquals(42, geh.getExceptionsHistoryMaximum());
	}

	@Test public void testSetLastException() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setLastException(new Throwable() {});
		Assert.assertNotNull(geh.getLastException());
	}

	@Test public void testGetLastException() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		final Exception e = new Exception("123");
		geh.setLastException(e);
		Assert.assertEquals(e, geh.getLastException());
	}

	@Test public void testHadException() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setLastException(new Throwable() {});
		Assert.assertTrue(geh.hadException());
	}

	@Test public void testGetExceptionsCount() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setLastException(new Throwable() {});
		geh.setLastException(new Exception() {});
		Assert.assertEquals(2, geh.getExceptionsCount());
	}

	@Test public void testForgetExceptionsThrown() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
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
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setLastException(new Throwable() {});
		if (geh.hadException()) {
			geh.forgetLastException();
			Assert.assertFalse(geh.hadException());
		} else {
			Assert.fail();
		}
	}

	@Test public void testGetLastExceptionThread() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setLastException(new Throwable() {});
		Assert.assertEquals(Thread.currentThread(), geh.getLastExceptionThread());
	}

	@Test public void testGetLastExceptionTimestamp() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		Assert.assertEquals(-1, geh.getLastExceptionTimestamp());
		geh.setLastException(new Throwable() {});
		Assert.assertNotEquals(-1, geh.getLastExceptionTimestamp());
	}

	@Test public void testGetExceptionsThrown() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		geh.setLastException(new Throwable() {});
		geh.setLastException(new Exception() {});
		Assert.assertEquals(2, geh.getExceptionsThrown().size());
	}

	@Test public void testGetExceptionsMonitor() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
		Assert.assertNull(geh.getExceptionsMonitor());
	}

	boolean resultSetExceptionsMonitor = false;
	@Test public void testSetExceptionsMonitor() {
		final LocalExceptionHandler geh = new LocalExceptionHandler();
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