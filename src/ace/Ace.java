/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.app.SemanticVersion;
import ace.interfaces.ExceptionsHandler;

/**
 * Ace toolkit class.
 */
public class Ace {

	// GLOBAL
	/**
	 * Ace toolkit version.
	 */
	public static final SemanticVersion VERSION = SemanticVersion.fromString("0.2.8");

	/**
	 * Ace toolkit DEVELOPMENT mode flag.
	 */
	public static boolean DEVELOPMENT = false;

	// OBJECTS
	/**
	 * Empty object constant.
	 */
	public static final Object EMPTY_OBJECT = new Object();

	/**
	 * Checks if all of the specified objects are not null.
	 *
	 * @param objects
	 * @return Returns true if all of the specified objects are not null, otherwise it returns false.
	 */
	public static final boolean assigned(final Object... objects) {
		boolean result = objects.length > 0;
		for (final Object o : objects) {
			result &= o != null;
		}
		return result;
	}

	/**
	 * Ensures a value in the output.
	 *
	 * @param <T>
	 * @param value
	 * @param defaultValue
	 * @return If the specified value is not null returns value, otherwise it returns the specified default value.
	 */
	public static final <T> T ensure(final T value, final T defaultValue) {
		return value != null ? value : defaultValue;
	}

	// SYSTEM
	/**
	 * Exits with -1 error code.
	 */
	public static final void halt() {
		exit(-1);
	}

	/**
	 * Exits with the specified error code.
	 *
	 * @param errorCode
	 */
	public static final void exit(final int errorCode) {
		System.exit(errorCode);
	}

	/**
	 * If Ace is in DEVELOPMENT mode, prints a line with the specified value and returns it. Otherwise it just returns the specified value.
	 *
	 * @param <T>
	 * @param value
	 * @return It always returns the passed value.
	 */
	public static final <T> T debug(final T value) {
		if (DEVELOPMENT) {
			System.out.println(value);
		}
		return value;
	}

	/**
	 * Prints a line with specified value.
	 *
	 * @param <T>
	 * @param value
	 */
	public static final <T> void print(final T value) {
		System.out.println(value);
	}

	/**
	 * Freezes the current thread for a period of the specified number of milliseconds.
	 * 
	 * @param milliseconds
	 * @return Returns true if the nap was completed or false if interrupted by another thread.
	 */
	public boolean nap(final long milliseconds) {
		try {
			Thread.sleep(milliseconds);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Freezes the current thread for a period of the specified number of milliseconds plus the specified number of nanoseconds (in the 0..999999 range).
	 * 
	 * @param milliseconds
	 * @param nanoseconds
	 * @return Returns true if the nap was completed or false if interrupted by another thread or the specified nanoseconds are not in the valid range.
	 */
	public boolean nap(final long milliseconds, final int nanoseconds) {
		try {
			Thread.sleep(milliseconds, nanoseconds);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	// EXCEPTIONS

	/**
	 * This is the global exceptions handler.
	 */
	public static final ExceptionsHandler GEH = new GlobalExceptionHandler();

}
