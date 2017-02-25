/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.interfaces.ExceptionsHandler;
import ace.platform.Exceptions;
import java.util.ArrayList;

/**
 * Global exception handler.
 */
public class GlobalExceptionHandler extends Ace implements ExceptionsHandler {

	private Thread.UncaughtExceptionHandler _lastUncaughtExceptionHandler = null;
	private final boolean _printUncaughtExceptionsStackTrace;
	private int _exceptionsHistoryMaximum = 100;
	private final ArrayList<Exception> _exceptionsThrown = new ArrayList();
	private boolean _hadException = false;
	private Exception _lastException = null;
	private Thread _lastExceptionThread;
	private long _lastExceptionTimestamp = -1;

	public GlobalExceptionHandler(final int exceptionsHistoryMaximum, final boolean handleUncaughtExceptions, final boolean printUncaughtExceptionsStackTrace) {
		_exceptionsHistoryMaximum = exceptionsHistoryMaximum;
		_printUncaughtExceptionsStackTrace = printUncaughtExceptionsStackTrace;
		if (handleUncaughtExceptions) {
			registerAsDefaultUncaughtExceptionHandler();
		}
	}

	@SuppressWarnings("ThrowableResultIgnored")
	private boolean addException(final Exception e) {
		if (_exceptionsThrown.size() >= _exceptionsHistoryMaximum) {
			_exceptionsThrown.remove(0);
		}
		return _exceptionsThrown.add(e);
	}

	private void catchThrowable(final Thread thread, final Throwable throwable) {
		final Exception e = Exceptions.asException(throwable);
		_hadException = (_lastException = e) != null ? addException(e) : false;
		_lastExceptionTimestamp = _hadException ? System.currentTimeMillis() : -1;
		_lastExceptionThread = thread;
	}

	public int getExceptionsHistoryMaximum() {
		return _exceptionsHistoryMaximum;
	}

	public void setExceptionsHistoryMaximum(final int value) {
		_exceptionsHistoryMaximum = value;
	}

	private boolean setDefaultUncaughtExceptionHandler(final Thread.UncaughtExceptionHandler handler) {
		try {
			Thread.setDefaultUncaughtExceptionHandler(handler);
			return true;
		} catch (final Exception e) {
			catchThrowable(Thread.currentThread(), e);
			return false;
		}
	}

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

	public final boolean unregisterAsDefaultUncaughtExceptionHandler() {
		return setDefaultUncaughtExceptionHandler(_lastUncaughtExceptionHandler);
	}

	/*@Override*/ public final void forgetExceptionsThrown() {
		_exceptionsThrown.clear();
	}

	/*@Override*/ public final ArrayList<Exception> getExceptionsThrown() {
		return _exceptionsThrown;
	}

	/*@Override*/ public final boolean hadException() {
		return _hadException;
	}

	/*@Override*/ public final void forgetLastException() {
		_exceptionsThrown.remove(_lastException);
		_lastException = null;
		_hadException = false;
	}

	/*@Override*/ public final Exception getLastException() {
		return _lastException;
	}

	/*@Override*/ public final long getLastExceptionTimestamp() {
		return _lastExceptionTimestamp;
	}

	/*@Override*/ public final Thread getLastExceptionThread() {
		return _lastExceptionThread;
	}

	/*@Override*/ public int getExceptionsCount() {
		return _exceptionsThrown.size();
	}

	/*@Override*/ public final void setLastException(final Throwable throwable) {
		catchThrowable(Thread.currentThread(), throwable);
	}

}
