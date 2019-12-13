/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.containers.Lists;
import ace.interfaces.ExceptionsHandler;
import ace.interfaces.ExceptionsMonitor;
import java.util.List;

/**
 * Local exception handler.
 */
public class LocalExceptionHandler extends Ace implements ExceptionsHandler {

	private int _exceptionsHistoryMaximum = 100;
	private final List<Throwable> _exceptionsThrown = Lists.make();
	private boolean _hadException = false;
	private Throwable _lastException = null;
	private Thread _lastExceptionThread;
	private long _lastExceptionTimestamp = -1;
	protected ExceptionsMonitor _exceptionsMonitor;

	@SuppressWarnings("ThrowableResultIgnored")
	private boolean addException(final Throwable throwable) {
		if (_exceptionsThrown.size() >= _exceptionsHistoryMaximum) {
			_exceptionsThrown.remove(0);
		}
		return _exceptionsThrown.add(throwable);
	}

	/**
	 * Catches a throwable.
	 * 
	 * @param thread
	 * @param throwable 
	 */
	protected void catchThrowable(final Thread thread, final Throwable throwable) {
		_hadException = (_lastException = throwable) != null ? addException(throwable) : false;
		_lastExceptionTimestamp = _hadException ? System.currentTimeMillis() : -1;
		_lastExceptionThread = thread;
		if (assigned(_exceptionsMonitor)) {
			_exceptionsMonitor.onExceptionCatched(_lastExceptionThread, _lastException, _lastExceptionTimestamp);
		}
	}

	/**
	 * Returns the maximum number in exceptions history.
	 * 
	 * @return the maximum of exceptions allowed in history
	 */
	public int getExceptionsHistoryMaximum() {
		return _exceptionsHistoryMaximum;
	}

	/**
	 * Sets the maximum number in exceptions history.
	 * 
	 * @param value 
	 */
	public void setExceptionsHistoryMaximum(final int value) {
		_exceptionsHistoryMaximum = value;
	}

	/**
	 * Drops all the caught exceptions.
	 */
	/*@Override*/ public final void forgetExceptionsThrown() {
		_exceptionsThrown.clear();
		_lastException = null;
		_hadException = false;
	}

	/**
	 * Retrieves all the caught exceptions.
	 * 
	 * @return the list of the exceptions thrown
	 */
	/*@Override*/ public final List<Throwable> getExceptionsThrown() {
		return _exceptionsThrown;
	}

    /**
     * Determines if the LEH has an exception caught.
     *
     * @return <tt>true</tt> if the LEH caught an exception
     */
	/*@Override*/ public final boolean hadException() {
		return _hadException;
	}

	/**
	 * Drops the last exception caught.
	 */
	/*@Override*/ public final void forgetLastException() {
		_exceptionsThrown.remove(_lastException);
		_lastException = null;
		_hadException = false;
	}

	/**
	 * Gets the last exception caught.
	 * 
	 * @return the last exception caught
	 */
	/*@Override*/ public final Throwable getLastException() {
		return _lastException;
	}

	/**
	 * Gets the last exception timestamp.
	 * 
	 * @return the last exception timestamp
	 */
	/*@Override*/ public final long getLastExceptionTimestamp() {
		return _lastExceptionTimestamp;
	}

	/**
	 * Gets the last exception thread.
	 * 
	 * @return the last exception thread
	 */
	/*@Override*/ public final Thread getLastExceptionThread() {
		return _lastExceptionThread;
	}

	/**
	 * Retrieves the number of caught exceptions.
	 * 
	 * @return the number of caught exceptions
	 */
	/*@Override*/ public int getExceptionsCount() {
		return _exceptionsThrown.size();
	}

	/**
	 * Adds the exception to the history and sets it as the last to be caught
	 * 
	 * @param throwable 
	 */
	/*@Override*/ public final void setLastException(final Throwable throwable) {
		catchThrowable(Thread.currentThread(), throwable);
	}

	/**
	 * Retrieves the exceptions monitor.
	 * 
	 * @return the exceptions monitor
	 */
	/*@Override*/ public ExceptionsMonitor getExceptionsMonitor() {
		return _exceptionsMonitor;
	}

	/**
	 * Sets the exceptions monitor.
	 * 
	 * @param exceptionsMonitor 
	 */
	/*@Override*/ public void setExceptionsMonitor(final ExceptionsMonitor exceptionsMonitor) {
		_exceptionsMonitor = exceptionsMonitor;
	}

}
