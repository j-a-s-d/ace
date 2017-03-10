/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import org.junit.Assert;
import org.junit.Test;

public class FileAppenderTest {

	private final FileAppender _appender = new FileAppender(TemporaryFiles.create());

	@Test public void testReset() {
		try {
			_appender.reset();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testAppend_String() {
		Assert.assertTrue(_appender.append("test"));
	}

	@Test public void testAppend_byteArr() {
		Assert.assertTrue(_appender.append("testing".getBytes()));
	}

	@Test public void testCloseFile() {
		try {
			_appender.closeFile();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testClose() {
		try {
			_appender.close();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
