/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.Test;

public class StreamsTest {

	final String CONTENT = "test";
	final ByteArrayInputStream INPUT = new ByteArrayInputStream(CONTENT.getBytes());
	final ByteArrayOutputStream OUTPUT = new ByteArrayOutputStream();

	@Test public void testCopy_InputStream_OutputStream() {
		Assert.assertTrue(Streams.copy(INPUT, OUTPUT));
		Assert.assertTrue(new String(OUTPUT.toByteArray()).equals(CONTENT));
	}

	@Test public void testCopy_3args() {
		Assert.assertTrue(Streams.copy(INPUT, OUTPUT, 2));
		Assert.assertTrue(new String(OUTPUT.toByteArray()).equals(CONTENT));
	}

	@Test public void testDeepCopyToByteArray_InputStream() {
		Assert.assertArrayEquals(CONTENT.getBytes(), Streams.deepCopyToByteArray(INPUT));
	}

	@Test public void testDeepCopyToByteArray_InputStream_int() {
		Assert.assertArrayEquals(CONTENT.getBytes(), Streams.deepCopyToByteArray(INPUT, 2));
	}

	@Test public void testToByteArray() {
		Assert.assertArrayEquals(CONTENT.getBytes(), Streams.toByteArray(INPUT));
	}

	@Test public void testHasSameBytes() {
		Assert.assertTrue(Streams.hasSameBytes(new BufferedInputStream(INPUT), new BufferedInputStream(new ByteArrayInputStream(CONTENT.getBytes()))));
	}

	@Test public void testClose() {
		try {
			Streams.close(new ByteArrayInputStream(CONTENT.getBytes()));
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testCopySegment_4args() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Assert.assertTrue(Streams.copySegment(INPUT, 1, 2, baos));
		Assert.assertTrue(new String(baos.toByteArray()).equals("es"));
	}

	@Test public void testCopySegment_5args() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Assert.assertTrue(Streams.copySegment(INPUT, 1, 2, baos, 1));
		Assert.assertTrue(new String(baos.toByteArray()).equals("es"));
	}

}
