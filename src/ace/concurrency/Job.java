/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.interfaces.Decisor;
import java.util.concurrent.*;

public abstract class Job extends Ace implements Runnable {

	protected String _name;
	protected long _count;
	protected TimeUnit _timeUnit;
	protected long _timeAmount;
	protected Decisor _decision;
	protected Long _times;
	protected Runnable _runnable;
	protected ScheduledFuture<?> _scheduledFuture;
	protected boolean _postScheduled;

	@SuppressWarnings("LeakingThisInConstructor")
	public Job(final String name) {
		_name = name;
		_count = 0;
		_decision = null;
		_times = null;
		_postScheduled = false;
		_timeUnit = TimeUnit.SECONDS;
		_timeAmount = 1;
		setRunnable(this);
	}

	public String getName() {
		return _name;
	}

	public Job setName(final String value) {
		_name = value;
		return this;
	}

	protected Runnable getRunnable() {
		return _runnable;
	}

	protected Job setRunnable(final Runnable value) {
		_runnable = new Runnable() {
			/*@Override*/ public final void run() {
				if (allow()) {
					value.run();
				} else {
					_scheduledFuture.cancel(true);
				}
			}
		};
		return this;
	}

	protected ScheduledFuture<?> getScheduledFuture() {
		return _scheduledFuture;
	}

	protected Job setScheduledFuture(final ScheduledFuture<?> value) {
		_scheduledFuture = value;
		return this;
	}

	public void cancel() {
		if (assigned(_scheduledFuture)) {
			_scheduledFuture.cancel(false);
		}
	}

	public void cancelNow() {
		if (assigned(_scheduledFuture)) {
			_scheduledFuture.cancel(true);
		}
	}

	protected Job setExecutionTime(final boolean postScheduled, final TimeUnit unit, final long amount) {
		_postScheduled = postScheduled;
		_timeUnit = unit;
		_timeAmount = amount;
		return this;
	}

	public boolean isPostScheduled() {
		return _postScheduled;
	}

	public TimeUnit getTimeUnit() {
		return _timeUnit;
	}

	public long getTimeAmount() {
		return _timeAmount;
	}

	public synchronized boolean allow() {
		if (assigned(_times) && _count >= _times) {
			return false;
		}
		if (assigned(_decision) && _decision.decide()) {
			return false;
		}
		_count++;
		return true;
	}

	public long getExecutionsCount() {
		return _count;
	}

	public Job eachMilliseconds(final long value) {
		return setExecutionTime(false, TimeUnit.MILLISECONDS, value);
	}

	public Job eachSeconds(final long value) {
		return setExecutionTime(false, TimeUnit.SECONDS, value);
	}

	public Job eachMillisecondsAfterLastJobFinished(final long value) {
		return setExecutionTime(true, TimeUnit.MILLISECONDS, value);
	}

	public Job eachSecondsAfterLastJobFinished(final long value) {
		return setExecutionTime(true, TimeUnit.SECONDS, value);
	}

	public Job untilDecision(final Decisor decision) {
		_decision = decision;
		return this;
	}

	public Job untilTimes(final long count) {
		_times = count;
		return this;
	}

}
