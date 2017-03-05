/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.interfaces.ExceptionsHandler;
import ace.interfaces.ExceptionsMonitor;
import java.util.ArrayList;
import java.util.List;

/**
 * Local exception handler.
 */
public class LocalExceptionHandler extends Ace implements ExceptionsHandler {

	private int _exceptionsHistoryMaximum = 100;
	private final List<Throwable> _exceptionsThrown = new ArrayList();
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

	protected void catchThrowable(final Thread thread, final Throwable throwable) {
		_hadException = (_lastException = throwable) != null ? addException(throwable) : false;
		_lastExceptionTimestamp = _hadException ? System.currentTimeMillis() : -1;
		_lastExceptionThread = thread;
		if (assigned(_exceptionsMonitor)) {
			_exceptionsMonitor.onExceptionCatched(_lastExceptionThread, _lastException, _lastExceptionTimestamp);
		}
	}

	public int getExceptionsHistoryMaximum() {
		return _exceptionsHistoryMaximum;
	}

	public void setExceptionsHistoryMaximum(final int value) {
		_exceptionsHistoryMaximum = value;
	}

	/*@Override*/ public final void forgetExceptionsThrown() {
		_exceptionsThrown.clear();
	}

	/*@Override*/ public final List<Throwable> getExceptionsThrown() {
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

	/*@Override*/ public final Throwable getLastException() {
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

	/*@Override*/ public ExceptionsMonitor getExceptionsMonitor() {
		return _exceptionsMonitor;
	}

	/*@Override*/ public void setExceptionsMonitor(final ExceptionsMonitor exceptionsMonitor) {
		_exceptionsMonitor = exceptionsMonitor;
	}

}
