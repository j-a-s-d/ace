/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.Sandboxed;

public class Runnables extends Ace {

	public static final Runnable makeDummy() {
		return new Runnable() {
			/*@Override*/ public void run() {
			}
		};
	}

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
