/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import org.junit.Assert;
import org.junit.Test;

public class GlobalExceptionHandlerTest {

	@Test public void testRegisterAsDefaultUncaughtExceptionHandler() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler();
		Assert.assertTrue(geh.registerAsDefaultUncaughtExceptionHandler());
	}

	@Test public void testUnregisterAsDefaultUncaughtExceptionHandler() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler();
		Assert.assertTrue(geh.registerAsDefaultUncaughtExceptionHandler());
		Assert.assertTrue(geh.unregisterAsDefaultUncaughtExceptionHandler());
	}

	@Test public void testDisableUncaughtExceptionsStackTracePrinting() {
		final GlobalExceptionHandler geh = new GlobalExceptionHandler();
		geh.disableUncaughtExceptionsStackTracePrinting();
		Assert.assertNotNull(geh);
	}

}