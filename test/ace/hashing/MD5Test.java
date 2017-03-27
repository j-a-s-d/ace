/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class MD5Test {

	@Test public void testDigest_byteArr() {
		Assert.assertArrayEquals(Hex.toByteArray("fc3ff98e8c6a0d3087d515c0473f8677"), MD5.digest("hello world!".getBytes()));
	}

	@Test public void testDigest_String() {
		Assert.assertArrayEquals(Hex.toByteArray("fc3ff98e8c6a0d3087d515c0473f8677"), MD5.digest("hello world!"));
		
	}

	@Test public void testHash_byteArr() {
		Assert.assertEquals("fc3ff98e8c6a0d3087d515c0473f8677", MD5.hash("hello world!".getBytes()));
	}

	@Test public void testHash_String() {
		Assert.assertEquals("fc3ff98e8c6a0d3087d515c0473f8677", MD5.hash("hello world!"));
	}

}
