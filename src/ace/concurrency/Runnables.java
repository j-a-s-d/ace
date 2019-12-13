/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.Sandboxed;

/**
 * Utility class for working with runnables.
 */
public class Runnables extends Ace {

	/**
	 * Creates an empty runnable with nothing to execute.
	 * 
	 * @return the created runnable
	 */
	public static final Runnable makeDummy() {
		return new Runnable() {
			/*@Override*/ public void run() {
			}
		};
	}

	/**
	 * Runs the specified runnable inside a Sandboxed instance.
	 * 
	 * @param r
	 * @return <tt>true</tt> if the runnable was executed successfully and without exceptions, <tt>false </tt> otherwise
	 */
	public static final boolean run(final Runnable r) {
		return new Sandboxed<Boolean>() {
			@Override public Boolean run() throws Exception {
				if (assigned(r)) {
					r.run();
					return true;
				}
				return false;
			}
		}.go(Boolean.FALSE);
	}

}
