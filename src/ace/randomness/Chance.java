/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.randomness;

import ace.Ace;
import java.util.Random;

/**
 * Utility class for working with randomness.
 */
public class Chance extends Ace {

	/**
	 * Gets a random boolean value.
	 * 
	 * @return a random boolean value
	 */
	public static synchronized boolean coinToss() {
		return new Random().nextBoolean();
	}

	/**
	 * Gets a random byte array with the length specified.
	 * 
	 * @param length
	 * @return a byte array
	 */
	public static synchronized byte[] getRandomBytes(final int length) {
		final byte[] result = new byte[length];
		new Random().nextBytes(result);
		return result;
	}

	/**
	 * Gets a random byte value between the specified min and max values.
	 * 
	 * @param min
	 * @param max
	 * @return a random byte value inside the range specified
	 */
	public static synchronized byte inRange(final byte min, final byte max) {
		return (byte) (new Random().nextInt(max - min + 1) + min);
	}

	/**
	 * Gets a random short value between the specified min and max values.
	 * 
	 * @param min
	 * @param max
	 * @return a random short value inside the range specified
	 */
	public static synchronized short inRange(final short min, final short max) {
		return (short) inRange((int) min, (int) max);
	}

	/**
	 * Gets a random int value between the specified min and max values.
	 * 
	 * @param min
	 * @param max
	 * @return a random int value inside the range specified
	 */
	public static synchronized int inRange(final int min, final int max) {
		return Math.abs((int) new Random().nextInt() % (max - min + 1)) + min;
	}

	/**
	 * Gets a random long value between the specified min and max values.
	 * 
	 * @param min
	 * @param max
	 * @return a random long value inside the range specified
	 */
	public static synchronized long inRange(final long min, final long max) {
		return Math.abs((long) new Random().nextLong() % (max - min + 1)) + min;
	}

	/**
	 * Gets a random float value between the specified min and max values.
	 * 
	 * @param min
	 * @param max
	 * @return a random float value inside the range specified
	 */
	public static synchronized float inRange(final float min, final float max) {
		return Math.abs((float) new Random().nextFloat() % (max - min + 1)) + min;
	}

	/**
	 * Gets a random double value between the specified min and max values.
	 * 
	 * @param min
	 * @param max
	 * @return a random double value inside the range specified
	 */
	public static synchronized double inRange(final double min, final double max) {
		return Math.abs((double) new Random().nextDouble() % (max - min + 1)) + min;
	}

}
