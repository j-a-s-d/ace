/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class SHA256Test {

	@Test public void testDigest_byteArr() {
		Assert.assertArrayEquals(Hex.toByteArray("7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9"), SHA256.digest("hello world!".getBytes()));
	}

	@Test public void testDigest_String() {
		Assert.assertArrayEquals(Hex.toByteArray("7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9"), SHA256.digest("hello world!"));
	}

	@Test public void testHash_byteArr() {
		Assert.assertEquals("7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9", SHA256.hash("hello world!".getBytes()));
	}

	@Test public void testHash_String() {
		Assert.assertEquals("7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9", SHA256.hash("hello world!"));
	}

}
