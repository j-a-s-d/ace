/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.math.Longs;
import org.junit.Assert;
import org.junit.Test;

public class CRC32Test {

	@Test public void testDigest_byteArr() {
		Assert.assertEquals(Longs.parse("3b4c26d", 16, 0), CRC32.digest("hello world!".getBytes()));
	}

	@Test public void testDigest_String() {
		Assert.assertEquals(Longs.parse("3b4c26d", 16, 0), CRC32.digest("hello world!"));
	}

	@Test public void testHash_byteArr() {
		Assert.assertEquals("3b4c26d", CRC32.hash("hello world!".getBytes()));
	}

	@Test public void testHash_String() {
		Assert.assertEquals("3b4c26d", CRC32.hash("hello world!"));
	}

}
