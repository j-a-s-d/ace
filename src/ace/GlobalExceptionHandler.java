/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

/**
 * Global exception handler.
 */
public class GlobalExceptionHandler extends LocalExceptionHandler {

	private Thread.UncaughtExceptionHandler _lastUncaughtExceptionHandler = null;
	private boolean _printUncaughtExceptionsStackTrace = true;

	public GlobalExceptionHandler() {
		registerAsDefaultUncaughtExceptionHandler();
	}

	private boolean setDefaultUncaughtExceptionHandler(final Thread.UncaughtExceptionHandler handler) {
		try {
			Thread.setDefaultUncaughtExceptionHandler(handler);
			return true;
		} catch (final Throwable e) {
			catchThrowable(Thread.currentThread(), e);
			return false;
		}
	}

	/*
	* Registers as the default uncaught exception handler.
	*/
	public final boolean registerAsDefaultUncaughtExceptionHandler() {
		_lastUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		return setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@SuppressWarnings("CallToPrintStackTrace")
			public void uncaughtException(final Thread thread, final Throwable throwable) {
				catchThrowable(thread, throwable);
				if (_printUncaughtExceptionsStackTrace) {
					throwable.printStackTrace();
				}
			}
		});
	}

	/*
	* Unregisters as the default uncaught exception handler by registering again the backup of the previous handler (which commonly will be null).
	*/
	public final boolean unregisterAsDefaultUncaughtExceptionHandler() {
		return setDefaultUncaughtExceptionHandler(_lastUncaughtExceptionHandler);
	}

	/**
	 * Prevents the GEH from printing the stack trace of uncaught exceptions.
	 * 
	 * If you need this, use it via:
	 * <code>((ace.GlobalExceptionHandler) ace.Ace.GEH).disableUncaughtExceptionsStackTracePrinting()</code>
	 */
	public void disableUncaughtExceptionsStackTracePrinting() {
		_printUncaughtExceptionsStackTrace = false;
	}

}
