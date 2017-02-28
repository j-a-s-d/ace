/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

import java.util.List;

/**
 * This is the interface of the added-value exceptions handling mechanism provided by Ace.
 */
public interface ExceptionsHandler {

	// LAST EXCEPTION

	boolean hadException();

	Throwable getLastException();

	Thread getLastExceptionThread();

	long getLastExceptionTimestamp();

	void forgetLastException();

	void setLastException(final Throwable throwable);

	// EXCEPTIONS THROWN

	int getExceptionsCount();

	void forgetExceptionsThrown();

	List<Throwable> getExceptionsThrown();

	// EXCEPTIONS MONITOR
	
	void setExceptionsMonitor(final ExceptionsMonitor exceptionsMonitor);
	
	ExceptionsMonitor getExceptionsMonitor();
	
}
