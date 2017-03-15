/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.containers.Maps;
import java.util.HashMap;

public class Schedulers extends Ace {

	private static final HashMap<String, Scheduler> _schedulers = Maps.make();

	Schedulers() {}

	public static synchronized boolean createSerialScheduler(final String name) {
		if (!_schedulers.containsKey(name)) {
			_schedulers.put(name, new SerialScheduler(name));
			return true;
		} else {
			return false;
		}
	}

	public static synchronized boolean createParallelScheduler(final String name, final int maxThreadCount) {
		if (!_schedulers.containsKey(name)) {
			_schedulers.put(name, new ParallelScheduler(maxThreadCount, name));
			return true;
		} else {
			return false;
		}
	}

	public static synchronized boolean submit(final String name, final Job job) {
		return submit(name, job, 0);
	}

	public static synchronized boolean submit(final String name, final Job job, final long initialDelay) {
		return assigned(job.setScheduledFuture(
			_schedulers.containsKey(name) ? _schedulers.get(name).submit(job, initialDelay) : null
		).getScheduledFuture());
	}

	public static synchronized boolean shutdown(final String name) {
		if (_schedulers.containsKey(name)) {
			_schedulers.get(name).shutdown();
			return true;
		} else {
			return false;
		}
	}

	public static synchronized void shutdownAll() {
		for (final String name : _schedulers.keySet()) {
			shutdown(name);
		}
	}

}
