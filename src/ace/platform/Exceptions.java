/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utility class for working with exceptions.
 */
public class Exceptions extends Ace {

	/**
	 * Prints the stack trace of the specified throwable to a string.
	 * 
	 * @param t
	 * @return the resulting string
	 */
	public static String printStackTraceToString(final Throwable t) {
		return t != null ? new StringWriter() {{
			t.printStackTrace(new PrintWriter(this));
		}}.getBuffer().toString() : null;
	}

	/**
	 * Gets the specified throwable as an exception instance.
	 * 
	 * @param t
	 * @return the specified throwable as an exception instance
	 */
	public static final Exception asException(final Throwable t) {
		return t != null ? (t instanceof Exception ? (Exception) t : new Exception(t)) : null;
	}

	/**
	 * Runs the specified runnable instance and returns the catched exception if any.
	 * 
	 * @param r
	 * @return the catched exception if any, <tt>null</tt> otherwise
	 */
	public static final Exception catchThrown(final Runnable r) {
		try {
			r.run();
			return null;
		} catch (final Exception e) {
			return e;
		}
	}

	/**
	 * Runs the specified runnable instance in a silent mode (discarding the exception handling).
	 * 
	 * @param r
	 * @return <tt>true</tt> if the specified runnable instance ran without throwing exceptions, <tt>false</tt> otherwise
	 */
	public static final boolean silentRun(final Runnable r) {
		return !assigned(catchThrown(r));
	}

}
