/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.randomness;

import ace.Ace;
import java.util.Random;

/**
 * Utility class for working with randomness.
 */
public class Chance extends Ace {

	public static synchronized boolean coinToss() {
		return new Random().nextBoolean();
	}

	public static synchronized byte[] getRandomBytes(final int bytes) {
		final byte[] result = new byte[bytes];
		new Random().nextBytes(result);
		return result;
	}

	public static synchronized byte inRange(final byte min, final byte max) {
		return (byte) (new Random().nextInt(max - min + 1) + min);
	}

	public static synchronized short inRange(final short min, final short max) {
		return (short) inRange((int) min, (int) max);
	}

	public static synchronized int inRange(final int min, final int max) {
		return Math.abs((int) new Random().nextInt() % (max - min + 1) + min);
	}

	public static synchronized long inRange(final long min, final long max) {
		return Math.abs((long) new Random().nextLong() % (max - min + 1) + min);
	}

	public static synchronized float inRange(final float min, final float max) {
		return Math.abs((float) new Random().nextFloat() % (max - min + 1) + min);
	}

	public static synchronized double inRange(final double min, final double max) {
		return Math.abs((double) new Random().nextDouble() % (max - min + 1) + min);
	}

}
