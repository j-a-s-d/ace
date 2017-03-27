/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.binary.Hex;
import ace.text.Strings;
import java.io.BufferedInputStream;
import org.junit.Assert;
import org.junit.Test;

public class DigesterTest {

	final String sHW = "hello world!";
	final byte[] bHW = sHW.getBytes();
	final Digester _sha1 = new Digester("SHA-1");
	final Digester _sha256 = new Digester("SHA-256");
	final Digester _md5 = new Digester("MD5");

	@Test public void testGetAlgorithm() {
		Assert.assertEquals("MD5", _md5.getAlgorithm());
		Assert.assertEquals("SHA-1", _sha1.getAlgorithm());
		Assert.assertEquals("SHA-256", _sha256.getAlgorithm());
	}

	@Test public void testGetProvider() {
		Assert.assertNotNull(_md5.getProvider());
		Assert.assertNotNull(_sha1.getProvider());
		Assert.assertNotNull(_sha256.getProvider());
	}

	@Test public void testGetDigestLength() {
		Assert.assertEquals(new Integer(16), _md5.getDigestLength());
		Assert.assertEquals(new Integer(20), _sha1.getDigestLength());
		Assert.assertEquals(new Integer(32), _sha256.getDigestLength());
	}

	@Test public void testReset() {
		Assert.assertNotNull(_sha1.reset());
	}

	@Test public void testDigest_String() {
		Assert.assertNotNull(_sha1.digest(sHW));
	}

	@Test public void testDigest_byteArr() {
		Assert.assertNotNull(_sha1.digest(bHW));
	}

	@Test public void testDigest_BufferedInputStream_int() {
		Assert.assertNotNull(_sha1.digest(new BufferedInputStream(Strings.toInputStream(sHW)), sHW.length()));
	}

	@Test public void testGetAsByteArray() {
		Assert.assertArrayEquals(Hex.toByteArray("430ce34d020724ed75a196dfc2ad67c77772d169"), _sha1.digest(new BufferedInputStream(Strings.toInputStream(sHW)), sHW.length()).getAsByteArray());
		Assert.assertArrayEquals(Hex.toByteArray("7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9"), _sha256.digest(new BufferedInputStream(Strings.toInputStream(sHW)), sHW.length()).getAsByteArray());
		Assert.assertArrayEquals(Hex.toByteArray("fc3ff98e8c6a0d3087d515c0473f8677"), _md5.digest(new BufferedInputStream(Strings.toInputStream(sHW)), sHW.length()).getAsByteArray());
	}

	@Test public void testGetAsString() {
		Assert.assertEquals("430ce34d020724ed75a196dfc2ad67c77772d169", _sha1.digest(sHW).getAsString());
		Assert.assertEquals("7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9", _sha256.digest(sHW).getAsString());
		Assert.assertEquals("fc3ff98e8c6a0d3087d515c0473f8677", _md5.digest(sHW).getAsString());
	}

}
