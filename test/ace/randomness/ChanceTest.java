/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.randomness;

import org.junit.Assert;
import org.junit.Test;

public class ChanceTest {

	@Test public void testCoinToss() {
		Assert.assertTrue(Chance.coinToss() || true);
	}

	@Test public void testGetRandomBytes() {
		Assert.assertEquals(3, Chance.getRandomBytes(3).length);
	}

	@Test public void testInRange_byte_byte() {
		final byte b = Chance.inRange((byte) 0x00, (byte) 0x02);
		Assert.assertTrue(b < (byte) 0x03);
	}

	@Test public void testInRange_short_short() {
		final short s = Chance.inRange((short) 0, (short) 2);
		Assert.assertTrue(s < (short) 3);
	}

	@Test public void testInRange_int_int() {
		final int s = Chance.inRange(0, 2);
		Assert.assertTrue(s < 3);
		final int x = Chance.inRange(400, 1400);
		Assert.assertTrue(x > 399);
		Assert.assertTrue(x < 1401);
	}

	@Test public void testInRange_long_long() {
		final long s = Chance.inRange(0, 2);
		Assert.assertTrue(s < 3);
	}

	@Test public void testInRange_float_float() {
		final float s = Chance.inRange(0.0f, 2.0f);
		Assert.assertTrue(s < 3.0f);
	}

	@Test public void testInRange_double_double() {
		final double s = Chance.inRange(0.0d, 2.0d);
		Assert.assertTrue(s < 3.0d);
	}

}
