/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

/**
 * Parallel scheduler class.
 */
public class ParallelScheduler extends Scheduler {

	/**
	 * Constructor accepting the maximum threads count and the name of the scheduler.
	 * 
	 * @param threads
	 * @param name 
	 */
	public ParallelScheduler(final int threads, final String name) {
		super(threads, name);
	}

}
