/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

import java.util.List;

/**
 * This is the interface of an added-value exceptions handling mechanism embedded into Ace.
 */
public interface ExceptionsHandler {

	// LAST EXCEPTION

	boolean hadException();

	Exception getLastException();

	Thread getLastExceptionThread();

	long getLastExceptionTimestamp();

	void forgetLastException();

	void setLastException(final Throwable throwable);

	// EXCEPTIONS THROWN

	int getExceptionsCount();

	void forgetExceptionsThrown();

	List<Exception> getExceptionsThrown();

}
