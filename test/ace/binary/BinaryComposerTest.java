/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import ace.text.Strings;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class BinaryComposerTest {

	@Test public void testReset() {
		Assert.assertEquals("", new BinaryComposer("test").reset().getAsString());
	}

	@Test public void testSet_byteArr() {
		Assert.assertArrayEquals(BYTES.ALL, new BinaryComposer().set(BYTES.ALL).get());
	}

	@Test public void testSet_charArr() {
		Assert.assertArrayEquals(new char[] { 't', 'e', 's', 't'}, new BinaryComposer().set(new char[] { 't', 'e', 's', 't'}).getAsCharArray());
	}

	@Test public void testSet_String() {
		Assert.assertEquals("test", new BinaryComposer().set("test").getAsString());
	}

	@Test public void testSet_InputStream_int() {
		Assert.assertEquals("te", new BinaryComposer().set(Strings.toInputStream("test"), 2).getAsString());
	}

	@Test public void testSet_InputStream() {
		Assert.assertEquals("test", new BinaryComposer().set(Strings.toInputStream("test")).getAsString());
	}

	@Test public void testSet_File() {
		Assert.assertNull(new BinaryComposer(new File(".")).get());
	}

	@Test public void testGet() {
		Assert.assertArrayEquals(new byte[] { 't', 'e', 's', 't' }, new BinaryComposer("test").get());
	}

	@Test public void testGetAsCharArray() {
		Assert.assertArrayEquals(new char[] { 't', 'e', 's', 't' }, new BinaryComposer("test").getAsCharArray());
	}

	@Test public void testGetAsString() {
		Assert.assertEquals("test", new BinaryComposer(new byte[] { 't', 'e', 's', 't' }).getAsString());
	}

	@Test public void testGetAsHexString() {
		Assert.assertEquals(Hex.fromByteArray(new byte[] { 't', 'e', 's', 't' }), new BinaryComposer(new byte[] { 't', 'e', 's', 't' }).getAsHexString());
	}

	@Test public void testAppend_byteArr() {
		Assert.assertEquals("testing", new BinaryComposer(new char[] { 't', 'e', 's', 't' }).append(new byte[] { 'i', 'n', 'g' }).getAsString());
	}

	@Test public void testAppend_charArr() {
		Assert.assertEquals("testing", new BinaryComposer(new byte[] { 't', 'e', 's', 't' }).append(new char[] { 'i', 'n', 'g' }).getAsString());
	}

	@Test public void testAppend_String() {
		Assert.assertEquals("testing", new BinaryComposer(new byte[] { 't', 'e', 's', 't' }).append("ing").getAsString());
	}

	@Test public void testAppend_InputStream_int() {
		Assert.assertEquals("test", new BinaryComposer("te").append(Strings.toInputStream("st"), 2).getAsString());
	}

	@Test public void testSize() {
		Assert.assertEquals(4, new BinaryComposer(new byte[] { 't', 'e', 's', 't' }).size());
	}

}
