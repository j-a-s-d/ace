/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utility class for working with exceptions.
 */
public class Exceptions extends Ace {

	public static String printStackTraceToString(final Throwable t) {
		return t != null ? new StringWriter() {{
			t.printStackTrace(new PrintWriter(this));
		}}.getBuffer().toString() : null;
	}

	public static final Exception asException(final Throwable t) {
		return t != null ? (t instanceof Exception ? (Exception) t : new Exception(t)) : null;
	}

	public static final Exception catchThrown(final Runnable r) {
		try {
			r.run();
			return null;
		} catch (final Exception e) {
			return e;
		}
	}

	public static final boolean silentRun(final Runnable r) {
		return !assigned(catchThrown(r));
	}

}
