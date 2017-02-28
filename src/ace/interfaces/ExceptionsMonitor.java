/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * This is interface is part of the added-value exceptions handling mechanism provided by Ace.
 */
public interface ExceptionsMonitor {
	
	void onExceptionCatched(final Thread  thread, final Throwable throwable, final long timestamp);
	
}
