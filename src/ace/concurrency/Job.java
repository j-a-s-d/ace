/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.interfaces.Decisor;
import java.util.concurrent.*;

/**
 * Abstract job class for use with schedulers.
 */
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

	/**
	 * Constructor accepting the job name.
	 * 
	 * @param name 
	 */
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

	/**
	 * Gets the job name.
	 * 
	 * @return the job name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the job name.
	 * 
	 * @param value
	 * @return itself
	 */
	public Job setName(final String value) {
		_name = value;
		return this;
	}

	/**
	 * Gets the job runnable.
	 * 
	 * @return the job runnable
	 */
	protected Runnable getRunnable() {
		return _runnable;
	}

	/**
	 * Sets the job runnable.
	 * 
	 * @param value
	 * @return itself
	 */
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

	/**
	 * Gets the job schedule future.
	 * 
	 * @return the job schedule future
	 */
	protected ScheduledFuture<?> getScheduledFuture() {
		return _scheduledFuture;
	}

	/**
	 * Sets the job schedule future.
	 * 
	 * @param value
	 * @return itself
	 */
	protected Job setScheduledFuture(final ScheduledFuture<?> value) {
		_scheduledFuture = value;
		return this;
	}

	/**
	 * Attempts to cancels the job.
	 */
	public void cancel() {
		if (assigned(_scheduledFuture)) {
			_scheduledFuture.cancel(false);
		}
	}

	/**
	 * Cancels the job now, even by interrupting it.
	 */
	public void cancelNow() {
		if (assigned(_scheduledFuture)) {
			_scheduledFuture.cancel(true);
		}
	}

	/**
	 * Sets the job execution time with the specified time amount and unit, and indicating if it is post scheduled or not.
	 * 
	 * @param postScheduled
	 * @param unit
	 * @param amount
	 * @return itself
	 */
	protected Job setExecutionTime(final boolean postScheduled, final TimeUnit unit, final long amount) {
		_postScheduled = postScheduled;
		_timeUnit = unit;
		_timeAmount = amount;
		return this;
	}

	/**
	 * Determines if the job is post scheduled.
	 * 
	 * @return <tt>true</tt> if the job is post scheduled, <tt>false</tt> otherwise
	 */
	public boolean isPostScheduled() {
		return _postScheduled;
	}

	/**
	 * Gets the job time unit.
	 * 
	 * @return the job time unit
	 */
	public TimeUnit getTimeUnit() {
		return _timeUnit;
	}

	/**
	 * Gets the job time amount.
	 * 
	 * @return the job time amount
	 */
	public long getTimeAmount() {
		return _timeAmount;
	}

	/**
	 * Computes if the job is allowed to execute.
	 * 
	 * @return <tt>true</tt> if the job is allowed to execute, <tt>false</tt> otherwise
	 */
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

	/**
	 * Gets the job executions count.
	 * 
	 * @return the job executions count
	 */
	public long getExecutionsCount() {
		return _count;
	}

	/**
	 * Sets the job to be executed every few milliseconds, which amount is the specified.
	 * 
	 * @param value
	 * @return itself
	 */
	public Job eachMilliseconds(final long value) {
		return setExecutionTime(false, TimeUnit.MILLISECONDS, value);
	}

	/**
	 * Sets the job to be executed every few seconds, which amount is the specified.
	 * 
	 * @param value
	 * @return itself
	 */
	public Job eachSeconds(final long value) {
		return setExecutionTime(false, TimeUnit.SECONDS, value);
	}

	/**
	 * Sets the job to be executed a few milliseconds, which amount is the specified, after the last job finished its execution.
	 * 
	 * @param value
	 * @return itself
	 */
	public Job eachMillisecondsAfterLastJobFinished(final long value) {
		return setExecutionTime(true, TimeUnit.MILLISECONDS, value);
	}

	/**
	 * Sets the job to be executed a few seconds, which amount is the specified, after the last job finished its execution.
	 * 
	 * @param value
	 * @return itself
	 */
	public Job eachSecondsAfterLastJobFinished(final long value) {
		return setExecutionTime(true, TimeUnit.SECONDS, value);
	}

	/**
	 * Sets the job to be executed until the stop decision turns <tt>true</tt>.
	 * 
	 * @param decision
	 * @return itself
	 */
	public Job untilDecision(final Decisor decision) {
		_decision = decision;
		return this;
	}

	/**
	 * Sets the job to be executed until the count reaches 0.
	 * 
	 * @param count
	 * @return itself
	 */
	public Job untilTimes(final long count) {
		_times = count;
		return this;
	}

}
