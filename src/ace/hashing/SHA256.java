/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.Ace;
import ace.binary.Hex;
import java.security.MessageDigest;

/**
 * Utility class for hashing with the SHA-256 algorithm.
 */
public class SHA256 extends Ace {

	/**
	 * Digests the specified byte array using the SHA256 algorithm.
	 * 
	 * @param bytes
	 * @return the array of bytes for the resulting hash value
	 */
	public static final byte[] digest(final byte[] bytes) {
		try {
			return MessageDigest.getInstance("SHA-256").digest(bytes);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Digests the specified string using the SHA256 algorithm.
	 * 
	 * @param string
	 * @return the array of bytes for the resulting hash value
	 */
	public static final byte[] digest(final String string) {
		return digest(string.getBytes());
	}

	/**
	 * Digests the specified byte array using the SHA256 algorithm.
	 * 
	 * @param bytes
	 * @return the hex string representation of the array of bytes for the resulting hash value
	 */
	public static final String hash(final byte[] bytes) {
		return Hex.fromByteArray(digest(bytes));
	}

	/**
	 * Digests the specified string using the SHA256 algorithm.
	 * 
	 * @param string
	 * @return the hex string representation of the array of bytes for the resulting hash value
	 */
	public static final String hash(final String string) {
		return Hex.fromByteArray(digest(string));
	}

}
