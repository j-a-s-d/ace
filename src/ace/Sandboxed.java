/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.interfaces.ExceptionsHandler;

/**
 * Useful class to assist you wrap code that can throw exceptions and to retrieve a default value if that's the case.
 */
public abstract class Sandboxed<T> extends LocalExceptionHandler {

	public static boolean USE_GEH = true;

	private final ExceptionsHandler _exceptionsHandler;

	public Sandboxed() {
		this(USE_GEH);
	}

	public Sandboxed(final boolean forwardExceptionsToGEH) {
		_exceptionsHandler = (forwardExceptionsToGEH ? GEH : this);
	}

	public final <T> T go(final T defaultValue) {
		try {
			_exceptionsHandler.forgetLastException();
			return (T) this.run();
		} catch (final Exception e) {
			_exceptionsHandler.setLastException(e);
			return defaultValue;
		}
	}

	public final <T> T go() {
		// NOTE: the following cast is required in JDK 5.0 to avoid a known inference failure http://bugs.java.com/view_bug.do?bug_id=6302954
		return (T) go(null);
	}

	public abstract <T> T run() throws Exception;

}
