/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.math.Longs;
import org.junit.Assert;
import org.junit.Test;

public class Adler32Test {

	@Test public void testDigest_byteArr() {
		Assert.assertEquals(Longs.parse("1e89047e", 16, 0), Adler32.digest("hello world!".getBytes()));
	}

	@Test public void testDigest_String() {
		Assert.assertEquals(Longs.parse("1e89047e", 16, 0), Adler32.digest("hello world!"));
	}

	@Test public void testHash_byteArr() {
		Assert.assertEquals("1e89047e", Adler32.hash("hello world!".getBytes()));
	}

	@Test public void testHash_String() {
		Assert.assertEquals("1e89047e", Adler32.hash("hello world!"));
	}

}
