/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import org.junit.Assert;
import org.junit.Test;

public class HexTest {

	@Test public void testToByteArray() {
		Assert.assertArrayEquals(new byte[] { (byte) 0x80, (byte) 0x86 }, Hex.toByteArray("8086"));
		Assert.assertArrayEquals(new byte[] {}, Hex.toByteArray(""));
		Assert.assertArrayEquals(null, Hex.toByteArray(null));
	}

	@Test public void testFromByteArray() {
		Assert.assertEquals("8086", Hex.fromByteArray(new byte[] { (byte) 0x80, (byte) 0x86 }));
		Assert.assertEquals("", Hex.fromByteArray(new byte[] {}));
		Assert.assertEquals(null, Hex.fromByteArray(null));
	}

}