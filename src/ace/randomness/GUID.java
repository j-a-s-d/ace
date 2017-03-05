/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.randomness;

import ace.Ace;
import java.util.UUID;

/**
 * Utility class for working with GUIDs.
 */
public class GUID extends Ace {

	public static UUID make() {
		return UUID.randomUUID();
	}

	public static String makeAsString() {
		return UUID.randomUUID().toString();
	}

}
