/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.Ace;

/**
 * Utility class for hashing with the Adler32 checksum.
 */
public class Adler32 extends Ace {

	/**
	 * Digests the specified byte array using the Adler32 algorithm.
	 * 
	 * @param bytes
	 * @return the long resulting hash value
	 */
	public static final long digest(final byte[] bytes) {
		return new java.util.zip.Adler32() {{
			update(bytes, 0, bytes.length);
		}}.getValue();
	}

	/**
	 * Digests the specified string using the Adler32 algorithm.
	 * 
	 * @param string
	 * @return the long resulting hash value
	 */
	public static final long digest(final String string) {
		return digest(string.getBytes());
	}

	/**
	 * Digests the specified byte array using the Adler32 algorithm.
	 * 
	 * @param bytes
	 * @return the hex string representation of the long resulting hash value
	 */
	public static final String hash(final byte[] bytes) {
		return Long.toHexString(digest(bytes));
	}

	/**
	 * Digests the specified string using the Adler32 algorithm.
	 * 
	 * @param string
	 * @return the hex string representation of the long resulting hash value
	 */
	public static final String hash(final String string) {
		return hash(string.getBytes());
	}

}
