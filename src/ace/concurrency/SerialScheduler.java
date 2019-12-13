/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

/**
 * Serial scheduler class.
 */
public class SerialScheduler extends Scheduler {

	/**
	 * Constructor accepting the name of the scheduler.
	 * 
	 * @param name 
	 */
	public SerialScheduler(final String name) {
		super(1, name);
	}

}
