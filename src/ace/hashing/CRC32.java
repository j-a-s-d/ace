/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.Ace;

/**
 * Utility class for hashing with the CRC32 checksum.
 */
public class CRC32 extends Ace {

	public static final long digest(final byte[] bytes) {
		return new java.util.zip.CRC32() {{
			update(bytes, 0, bytes.length);
		}}.getValue();
	}

	public static final long digest(final String string) {
		return digest(string.getBytes());
	}

	public static final String hash(final byte[] bytes) {
		return Long.toHexString(digest(bytes));
	}

	public static final String hash(final String string) {
		return hash(string.getBytes());
	}

}
