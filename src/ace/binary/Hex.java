/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import ace.Ace;
import java.util.Formatter;

/**
 * Utility class for working with hex strings.
 */
public class Hex extends Ace {

	/**
	 * Converts the hex string provided to its corresponding byte array.
	 * 
	 * @param string
	 * @return the resulting byte array
	 */
	public final static byte[] toByteArray(final String string) {
		if (string != null) {
			final byte[] r = new byte[string.length() / 2];
			for (int i = 0; i < string.length(); i += 2) {
				r[i / 2] = (byte) Integer.parseInt(string.substring(i, 2 + i), 16);
			}
			return r;
		}
		return null;
	}

	/**
	 * Converts the byte array to its corresponding hex string.
	 * 
	 * @param bytes
	 * @return the resulting hex string
	 */
	public final static String fromByteArray(final byte[] bytes) {
		if (bytes != null) {
			final Formatter formatter = new Formatter();
			for (final byte b : bytes) {
				formatter.format("%02x", b);
			}
			return formatter.toString();
		}
		return null;
	}

}
