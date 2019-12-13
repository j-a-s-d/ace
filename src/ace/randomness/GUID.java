/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.randomness;

import ace.Ace;
import java.util.UUID;

/**
 * Utility class for working with GUIDs.
 */
public class GUID extends Ace {

	/**
	 * Makes a random GUID and returns it as a UUID instance.
	 * 
	 * @return a UUID instance
	 */
	public static UUID make() {
		return UUID.randomUUID();
	}

	/**
	 * Makes a random GUID and returns it as its string representation.
	 * 
	 * @return the string representation of the created UUID instance
	 */
	public static String makeAsString() {
		return UUID.randomUUID().toString();
	}

}
