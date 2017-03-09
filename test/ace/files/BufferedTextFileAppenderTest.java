/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import org.junit.Assert;
import org.junit.Test;

public class BufferedTextFileAppenderTest {

	private static final BufferedTextFileAppender btfa = new BufferedTextFileAppender(TemporaryFiles.create());

	@Test public void testGetBufferSize() {
		Assert.assertEquals(BufferedTextFileAppender.DEFAULT_BUFFER_SIZE, btfa.getBufferSize());
	}

	@Test public void testSetBufferSize() {
		Assert.assertNotNull(btfa.setBufferSize(1000));
		Assert.assertEquals(1000, btfa.getBufferSize());
		Assert.assertNotNull(btfa.setBufferSize(BufferedTextFileAppender.DEFAULT_BUFFER_SIZE));
	}

	@Test public void testGetAppendix() {
		Assert.assertNotNull(btfa.setAppendix("\n"));
		Assert.assertEquals("\n", btfa.getAppendix());
	}

	@Test public void testSetAppendix() {
		Assert.assertNotNull(btfa.setAppendix("\n"));
		Assert.assertEquals("\n", btfa.getAppendix());
	}

	@Test public void testWrite_boolean_StringArr() {
		Assert.assertNotNull(btfa.write(1 > 0, "hello", "world"));
	}

	@Test public void testWrite_StringArr() {
		Assert.assertNotNull(btfa.write("hello", "world"));
	}

	@Test public void testWrite_boolean_String() {
		Assert.assertNotNull(btfa.write(1 > 0, "hello"));
	}

	@Test public void testWrite_String() {
		Assert.assertNotNull(btfa.write("hello"));
	}

	@Test public void testFlush() {
		try {
			btfa.flush();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testClose() {
		try {
			btfa.close();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

}
