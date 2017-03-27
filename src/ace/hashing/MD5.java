/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.Ace;
import ace.binary.Hex;
import java.security.MessageDigest;

/**
 * Utility class for hashing with the MD5 algorithm.
 */
public class MD5 extends Ace {

	public static final byte[] digest(final byte[] bytes) {
		try {
			return MessageDigest.getInstance("MD5").digest(bytes);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static final byte[] digest(final String string) {
		try {
			return digest(string.getBytes());
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static final String hash(final byte[] bytes) {
		return Hex.fromByteArray(digest(bytes));
	}

	public static final String hash(final String string) {
		return Hex.fromByteArray(digest(string));
	}

}
