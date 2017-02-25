/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionsTest {

	@Test public void testPrintStackTraceToString() {
		try {
			int i = 1 / 0;
		} catch (final Throwable t) {
			Assert.assertNotNull(Exceptions.printStackTraceToString(t));
		}
	}

	@Test public void testCatchThrown() {
		Assert.assertNotNull(Exceptions.catchThrown(new Runnable() {
			public void run() {
				int i = 1 / 0;
			}
		}));
	}

	@Test public void testSilentRun() {
		Assert.assertFalse(Exceptions.silentRun(new Runnable() {
			public void run() {
				int i = 1 / 0;
			}
		}));
	}

	@Test public void testAsException() {
		Assert.assertTrue(Exceptions.asException(new Throwable() {}) instanceof Exception);
		Assert.assertFalse(Exceptions.asException(null) instanceof Exception);
	}

}