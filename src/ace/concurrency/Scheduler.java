/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class Scheduler extends Ace {

	protected final HashMap<String, ScheduledFuture<?>> _jobs;
	protected final String _name;
	protected final ScheduledExecutorService _scheduler;
	protected final int _threads;

	public Scheduler(final int threads, final String name) {
		_jobs = new HashMap<String, ScheduledFuture<?>>();
		_scheduler = Executors.newScheduledThreadPool(_threads = threads);
		_name = name;
	}

	public final String getName() {
		return _name;
	}

	public final int getMaxThreadCount() {
		return _threads;
	}

	public final ScheduledFuture<?> submit(final Job job) {
		return submit(job, 0);
	}

	public final ScheduledFuture<?> submit(final Job job, final long initialDelay) {
		if (job.isPostScheduled()) {
			return _scheduler.scheduleWithFixedDelay(job.getRunnable(), initialDelay, job.getTimeAmount(), job.getTimeUnit());
		} else {
			return _scheduler.scheduleAtFixedRate(job.getRunnable(), initialDelay, job.getTimeAmount(), job.getTimeUnit());
		}
	}

	public final void shutdown() {
		_scheduler.shutdown();
	}

}
