/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * Scheduled thread pool wrapper class.
 */
public class Scheduler extends Ace {

	protected final HashMap<String, ScheduledFuture<?>> _jobs;
	protected final String _name;
	protected final ScheduledExecutorService _scheduler;
	protected final int _threads;

	/**
	 * Constructor accepting the maximum threads count and the name of the scheduler.
	 * 
	 * @param threads
	 * @param name 
	 */
	public Scheduler(final int threads, final String name) {
		_jobs = new HashMap<String, ScheduledFuture<?>>();
		_scheduler = Executors.newScheduledThreadPool(_threads = threads);
		_name = name;
	}

	/**
	 * Gets the scheduler name.
	 * 
	 * @return the scheduler name
	 */
	public final String getName() {
		return _name;
	}

	/**
	 * Gets the maximum threads count.
	 * 
	 * @return the maximum threads count
	 */
	public final int getMaxThreadCount() {
		return _threads;
	}

	/**
	 * Submits the specified job for execution.
	 * 
	 * @param job
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
	 */
	public final ScheduledFuture<?> submit(final Job job) {
		return submit(job, 0);
	}

	/**
	 * Submits the specified job for execution with the specified initial delay.
	 * 
	 * @param job
	 * @param initialDelay
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
	 */
	public final ScheduledFuture<?> submit(final Job job, final long initialDelay) {
		if (job.isPostScheduled()) {
			return _scheduler.scheduleWithFixedDelay(job.getRunnable(), initialDelay, job.getTimeAmount(), job.getTimeUnit());
		} else {
			return _scheduler.scheduleAtFixedRate(job.getRunnable(), initialDelay, job.getTimeAmount(), job.getTimeUnit());
		}
	}

	/**
	 * Shuts down the scheduler.
	 */
	public final void shutdown() {
		_scheduler.shutdown();
	}

}
