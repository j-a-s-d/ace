/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class SHA1Test {

	@Test public void testDigest_byteArr() {
		Assert.assertArrayEquals(Hex.toByteArray("430ce34d020724ed75a196dfc2ad67c77772d169"), SHA1.digest("hello world!".getBytes()));
	}

	@Test public void testDigest_String() {
		Assert.assertArrayEquals(Hex.toByteArray("430ce34d020724ed75a196dfc2ad67c77772d169"), SHA1.digest("hello world!"));
	}

	@Test public void testHash_byteArr() {
		Assert.assertEquals("430ce34d020724ed75a196dfc2ad67c77772d169", SHA1.hash("hello world!".getBytes()));
	}

	@Test public void testHash_String() {
		Assert.assertEquals("430ce34d020724ed75a196dfc2ad67c77772d169", SHA1.hash("hello world!"));
	}

}
