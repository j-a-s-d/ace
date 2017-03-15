/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

public class SerialScheduler extends Scheduler {

	public SerialScheduler(final String name) {
		super(1, name);
	}

}
