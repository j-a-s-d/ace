/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.containers.Maps;
import java.util.HashMap;

/**
 * Utility class for working with schedulers.
 */
public class Schedulers extends Ace {

	private static final HashMap<String, Scheduler> _schedulers = Maps.make();

	Schedulers() {}

	/**
	 * Creates a serial scheduler with the specified name.
	 * 
	 * @param name
	 * @return <tt>true</tt> if the scheduler was created, <tt>false</tt> otherwise
	 */
	public static synchronized boolean createSerialScheduler(final String name) {
		if (!_schedulers.containsKey(name)) {
			_schedulers.put(name, new SerialScheduler(name));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a parallel scheduler with the specified name and the specified maximum thread count.
	 * 
	 * @param name
	 * @param maxThreadCount
	 * @return <tt>true</tt> if the scheduler was created, <tt>false</tt> otherwise
	 */
	public static synchronized boolean createParallelScheduler(final String name, final int maxThreadCount) {
		if (!_schedulers.containsKey(name)) {
			_schedulers.put(name, new ParallelScheduler(maxThreadCount, name));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Submits the specified job to the scheduler with the specified name.
	 * 
	 * @param name
	 * @param job
	 * @return <tt>true</tt> if the job was submitted, <tt>false</tt> otherwise
	 */
	public static synchronized boolean submit(final String name, final Job job) {
		return submit(name, job, 0);
	}

	/**
	 * Submits the specified job to the scheduler with the specified name and with the initial delay.
	 * 
	 * @param name
	 * @param job
	 * @param initialDelay
	 * @return <tt>true</tt> if the job was submitted, <tt>false</tt> otherwise
	 */
	public static synchronized boolean submit(final String name, final Job job, final long initialDelay) {
		return assigned(job.setScheduledFuture(
			_schedulers.containsKey(name) ? _schedulers.get(name).submit(job, initialDelay) : null
		).getScheduledFuture());
	}

	/**
	 * Shuts down the scheduler with the specified name.
	 * 
	 * @param name
	 * @return <tt>true</tt> if the scheduler was shut down, <tt>false</tt> otherwise
	 */
	public static synchronized boolean shutdown(final String name) {
		if (_schedulers.containsKey(name)) {
			_schedulers.get(name).shutdown();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Shut down all the created schedulers.
	 */
	public static synchronized void shutdownAll() {
		for (final String name : _schedulers.keySet()) {
			shutdown(name);
		}
	}

}
