/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import ace.Ace;
import ace.constants.STRINGS;
import ace.containers.Maps;
import ace.interfaces.Reseteable;
import java.util.HashMap;

/**
 * Useful sample profiler class with double precision, errors counting and time stamping.
 */
public class SampleProfiler extends Ace implements Reseteable {

	public static final String PRECISION_SUFFIX_NONE = STRINGS.EMPTY;
	public static final String PRECISION_SUFFIX_MILLISECS = "-ms";
	public static final String PRECISION_SUFFIX_NANOSECS = "-ns";

	public static final String TIMESTAMP_SUFFIX = "-ts";

	private static final String METRIC_TOTAL = "total-";
	public static final String METRIC_TOTAL_COUNT = METRIC_TOTAL + "count";
	public static final String METRIC_TOTAL_ERROR = METRIC_TOTAL + "error";
	public static final String METRIC_LAST_ERROR_TIMESTAMP = "last-err" + TIMESTAMP_SUFFIX;
	private static final String METRIC_CURRENT_PREFIX = "perf-cur";
	public static final String METRIC_CURRENT_NOSUFFIX = METRIC_CURRENT_PREFIX + PRECISION_SUFFIX_NONE;
	public static final String METRIC_CURRENT_MILLISECS = METRIC_CURRENT_PREFIX + PRECISION_SUFFIX_MILLISECS;
	public static final String METRIC_CURRENT_NANOSECS = METRIC_CURRENT_PREFIX + PRECISION_SUFFIX_NANOSECS;
	public static final String METRIC_CURRENT_TIMESTAMP = METRIC_CURRENT_PREFIX + TIMESTAMP_SUFFIX;
	private static final String METRIC_MINIMUM_PREFIX = "perf-min";
	public static final String METRIC_MINIMUM_NOSUFFIX = METRIC_MINIMUM_PREFIX + PRECISION_SUFFIX_NONE;
	public static final String METRIC_MINIMUM_MILLISECS = METRIC_MINIMUM_PREFIX + PRECISION_SUFFIX_MILLISECS;
	public static final String METRIC_MINIMUM_NANOSECS = METRIC_MINIMUM_PREFIX + PRECISION_SUFFIX_NANOSECS;
	public static final String METRIC_MINIMUM_TIMESTAMP = METRIC_MINIMUM_PREFIX + TIMESTAMP_SUFFIX;
	private static final String METRIC_MAXIMUM_PREFIX = "perf-max";
	public static final String METRIC_MAXIMUM_NOSUFFIX = METRIC_MAXIMUM_PREFIX + PRECISION_SUFFIX_NONE;
	public static final String METRIC_MAXIMUM_MILLISECS = METRIC_MAXIMUM_PREFIX + PRECISION_SUFFIX_MILLISECS;
	public static final String METRIC_MAXIMUM_NANOSECS = METRIC_MAXIMUM_PREFIX + PRECISION_SUFFIX_NANOSECS;
	public static final String METRIC_MAXIMUM_TIMESTAMP = METRIC_MAXIMUM_PREFIX + TIMESTAMP_SUFFIX;
	private static final String METRIC_AVERAGE_PREFIX = "perf-avg";
	public static final String METRIC_AVERAGE_NOSUFFIX = METRIC_AVERAGE_PREFIX + PRECISION_SUFFIX_NONE;
	public static final String METRIC_AVERAGE_MILLISECS = METRIC_AVERAGE_PREFIX + PRECISION_SUFFIX_MILLISECS;
	public static final String METRIC_AVERAGE_NANOSECS = METRIC_AVERAGE_PREFIX + PRECISION_SUFFIX_NANOSECS;
	private static final String METRIC_AVERAGE_MINIMUM_PREFIX = "perf-avg-min";
	public static final String METRIC_AVERAGE_MINIMUM_NOSUFFIX = METRIC_AVERAGE_MINIMUM_PREFIX + PRECISION_SUFFIX_NONE;
	public static final String METRIC_AVERAGE_MINIMUM_MILLISECS = METRIC_AVERAGE_MINIMUM_PREFIX + PRECISION_SUFFIX_MILLISECS;
	public static final String METRIC_AVERAGE_MINIMUM_NANOSECS = METRIC_AVERAGE_MINIMUM_PREFIX + PRECISION_SUFFIX_NANOSECS;
	public static final String METRIC_AVERAGE_MINIMUM_TIMESTAMP = METRIC_AVERAGE_MINIMUM_PREFIX + TIMESTAMP_SUFFIX;
	private static final String METRIC_AVERAGE_MAXIMUM_PREFIX = "perf-avg-max";
	public static final String METRIC_AVERAGE_MAXIMUM_NOSUFFIX = METRIC_AVERAGE_MAXIMUM_PREFIX + PRECISION_SUFFIX_NONE;
	public static final String METRIC_AVERAGE_MAXIMUM_MILLISECS = METRIC_AVERAGE_MAXIMUM_PREFIX + PRECISION_SUFFIX_MILLISECS;
	public static final String METRIC_AVERAGE_MAXIMUM_NANOSECS = METRIC_AVERAGE_MAXIMUM_PREFIX + PRECISION_SUFFIX_NANOSECS;
	public static final String METRIC_AVERAGE_MAXIMUM_TIMESTAMP = METRIC_AVERAGE_MAXIMUM_PREFIX + TIMESTAMP_SUFFIX;

	private long _cnt;
	private long _err_timestamp;
	private long _err;
	private long _max_timestamp;
	private double _max;
	private long _min_timestamp;
	private double _min;
	private long _avg_max_timestamp;
	private double _avg_max;
	private long _avg_min_timestamp;
	private double _avg_min;
	private double _avg;
	private long _cur_timestamp;
	private double _cur;

	private String _precisionSuffix = PRECISION_SUFFIX_MILLISECS;

	/**
	 * Default constructor.
	 */
	public SampleProfiler() {
		this(null);
	}

	/**
	 * Constructor accepting a sample profiler as source from where copy the initial state.
	 * 
	 * @param source
	 */
	public SampleProfiler(final SampleProfiler source) {
		if (assigned(source)) {
			_precisionSuffix = source._precisionSuffix;
			_cnt = source._cnt;
			_err = source._err;
			_err_timestamp = source._err_timestamp;
			_max = source._max;
			_max_timestamp = source._max_timestamp;
			_min_timestamp = source._min_timestamp;
			_avg = source._avg;
			_avg_max_timestamp = source._avg_max_timestamp;
			_avg_min_timestamp = source._avg_min_timestamp;
		}
	}

	/**
	 * Indicates if the precision suffix is none.
	 * 
	 * @return <tt>true</tt> if the precision suffix is none, <tt>false</tt> otherwise
	 */
	public boolean isPrecisionSuffixNone() {
		return PRECISION_SUFFIX_NONE.equals(_precisionSuffix);
	}

	/**
	 * Indicates if the precision suffix is milliseconds.
	 * 
	 * @return <tt>true</tt> if the precision suffix is milliseconds, <tt>false</tt> otherwise
	 */
	public boolean isPrecisionSuffixMilliseconds() {
		return PRECISION_SUFFIX_MILLISECS.equals(_precisionSuffix);
	}

	/**
	 * Indicates if the precision suffix is nanoseconds.
	 * 
	 * @return <tt>true</tt> if the precision suffix is nanoseconds, <tt>false</tt> otherwise
	 */
	public boolean isPrecisionSuffixNanoseconds() {
		return PRECISION_SUFFIX_NANOSECS.equals(_precisionSuffix);
	}

	/**
	 * Sets the precision suffix as none.
	 * 
	 * @return itself
	 */
	public SampleProfiler setPrecisionSuffixToNone() {
		_precisionSuffix = PRECISION_SUFFIX_NONE;
		return this;
	}

	/**
	 * Sets the precision suffix as milliseconds.
	 * 
	 * @return itself
	 */
	public SampleProfiler setPrecisionSuffixToMilliseconds() {
		_precisionSuffix = PRECISION_SUFFIX_MILLISECS;
		return this;
	}

	/**
	 * Sets the precision suffix as nanoseconds.
	 * 
	 * @return itself
	 */
	public SampleProfiler setPrecisionSuffixToNanoseconds() {
		_precisionSuffix = PRECISION_SUFFIX_NANOSECS;
		return this;
	}

	/**
	 * Resets this sample profiler.
	 */
	/*@Override*/ public void reset() {
		_cnt = 0;
		_err = 0;
		_err_timestamp = 0;
		_max = 0;
		_max_timestamp = 0;
		_min_timestamp = 0;
		_avg = 0;
		_avg_max_timestamp = 0;
		_avg_min_timestamp = 0;
	}

	/**
	 * Takes a sample to profile by processing a boolean success flag and a long value.
	 * 
	 * @param success
	 * @param value 
	 */
	public void sample(final boolean success, final long value) {
		_cur = value;
		_cur_timestamp = System.currentTimeMillis();
		if (_cnt > 0) {
			if (value > _max) {
				_max = value;
				_max_timestamp = _cur_timestamp;
			}
			if (_min > value) {
				_min = value;
				_min_timestamp = _cur_timestamp;
			}
			_avg = ((_avg * _cnt) + value) / (_cnt + 1);
			if (_cnt == 1 || _avg > _avg_max) {
				_avg_max = _avg;
				_avg_max_timestamp = _cur_timestamp;
			}
			if (_cnt == 1 || _avg_min > _avg) {
				_avg_min = _avg;
				_avg_min_timestamp = _cur_timestamp;
			}
		} else {
			_avg = _min = _max = value;
			_min_timestamp = _max_timestamp = _cur_timestamp;
		}
		if (!success) {
			_err++;
			_err_timestamp = _cur_timestamp;
		}
		_cnt++;
	}

	/**
	 * Gets the last sampled (current) long value.
	 * 
	 * @return the last sampled (current) long value
	 */
	public long getCurrent() {
		return (long) _cur;
	}

	/**
	 * Gets the minimum sampled long value.
	 * 
	 * @return the minimum sampled long value
	 */
	public long getMinimum() {
		return (long) _min;
	}

	/**
	 * Gets the maximum sampled long value.
	 * 
	 * @return the maximum sampled long value
	 */
	public long getMaximum() {
		return (long) _max;
	}

	/**
	 * Gets the average sampled long value.
	 * 
	 * @return the average sampled long value
	 */
	public long getAverage() {
		return (long) _avg;
	}

	/**
	 * Gets the average minimum sampled long value.
	 * 
	 * @return the average minimum sampled long value
	 */
	public long getAverageMinimum() {
		return (long) _avg_min;
	}

	/**
	 * Gets the average maximum sampled long value.
	 * 
	 * @return the average maximum sampled long value
	 */
	public long getAverageMaximum() {
		return (long) _avg_max;
	}

	/**
	 * Gets the last sampled (current) double precision value.
	 * 
	 * @return the last sampled (current) double precision value
	 */
	public double getPreciseCurrent() {
		return _cur;
	}

	/**
	 * Gets the minimum sampled double precision value.
	 * 
	 * @return the minimum sampled double precision value
	 */
	public double getPreciseMinimum() {
		return _min;
	}

	/**
	 * Gets the maximum sampled double precision value.
	 * 
	 * @return the maximum sampled double precision value
	 */
	public double getPreciseMaximum() {
		return _max;
	}

	/**
	 * Gets the average sampled double precision value.
	 * 
	 * @return the average sampled double precision value
	 */
	public double getPreciseAverage() {
		return _avg;
	}

	/**
	 * Gets the average minimum sampled double precision value.
	 * 
	 * @return the average minimum sampled double precision value
	 */
	public double getPreciseAverageMinimum() {
		return _avg_min;
	}

	/**
	 * Gets the average maximum sampled double precision value.
	 * 
	 * @return the average maximum sampled double precision value
	 */
	public double getPreciseAverageMaximum() {
		return _avg_max;
	}

	/**
	 * Gets the last sampled (current) time stamp.
	 * 
	 * @return the last sampled (current) time stamp
	 */
	public long getCurrentTimestamp() {
		return _cur_timestamp;
	}

	/**
	 * Gets the minimum sampled time stamp.
	 * 
	 * @return the minimum sampled time stamp
	 */
	public long getMinimumTimestamp() {
		return _min_timestamp;
	}

	/**
	 * Gets the maximum sampled time stamp.
	 * 
	 * @return the maximum sampled time stamp
	 */
	public long getMaximumTimestamp() {
		return _max_timestamp;
	}

	/**
	 * Gets the average minimum sampled time stamp.
	 * 
	 * @return the average minimum sampled time stamp
	 */
	public long getAverageMinimumTimestamp() {
		return _avg_min_timestamp;
	}

	/**
	 * Gets the average maximum sampled time stamp.
	 * 
	 * @return the average maximum sampled time stamp
	 */
	public long getAverageMaximumTimestamp() {
		return _avg_max_timestamp;
	}

	/**
	 * Gets the last error sampled time stamp.
	 * 
	 * @return the last error sampled time stamp
	 */
	public long getLastErrorTimestamp() {
		return _err_timestamp;
	}

	/**
	 * Gets the count of sampled errors.
	 * 
	 * @return the count of sampled errors
	 */
	public long getTotalErrors() {
		return _err;
	}

	/**
	 * Gets the count of processed samples.
	 * 
	 * @return the count of processed samples
	 */
	public long getTotalCount() {
		return _cnt;
	}

	/**
	 * Gets a snapshot hash map of the current state values.
	 * 
	 * @return a snapshot hash map of the current state values
	 */
	public HashMap<String, Long> snapshot() {
		final HashMap<String, Long> result = Maps.make();
		if (PRECISION_SUFFIX_MILLISECS.equals(_precisionSuffix)) {
			result.put(METRIC_CURRENT_MILLISECS, (long) _cur);
			result.put(METRIC_MINIMUM_MILLISECS, (long) _min);
			result.put(METRIC_MAXIMUM_MILLISECS, (long) _max);
			result.put(METRIC_AVERAGE_MILLISECS, (long) _avg);
			result.put(METRIC_AVERAGE_MINIMUM_MILLISECS, (long) _avg_min);
			result.put(METRIC_AVERAGE_MAXIMUM_MILLISECS, (long) _avg_max);
		} else if (PRECISION_SUFFIX_NANOSECS.equals(_precisionSuffix)) {
			result.put(METRIC_CURRENT_NANOSECS, (long) _cur);
			result.put(METRIC_MINIMUM_NANOSECS, (long) _min);
			result.put(METRIC_MAXIMUM_NANOSECS, (long) _max);
			result.put(METRIC_AVERAGE_NANOSECS, (long) _avg);
			result.put(METRIC_AVERAGE_MINIMUM_NANOSECS, (long) _avg_min);
			result.put(METRIC_AVERAGE_MAXIMUM_NANOSECS, (long) _avg_max);
		} else {
			result.put(METRIC_CURRENT_NOSUFFIX, (long) _cur);
			result.put(METRIC_MINIMUM_NOSUFFIX, (long) _min);
			result.put(METRIC_MAXIMUM_NOSUFFIX, (long) _max);
			result.put(METRIC_AVERAGE_NOSUFFIX, (long) _avg);
			result.put(METRIC_AVERAGE_MINIMUM_NOSUFFIX, (long) _avg_min);
			result.put(METRIC_AVERAGE_MAXIMUM_NOSUFFIX, (long) _avg_max);
		}
		result.put(METRIC_CURRENT_TIMESTAMP, _cur_timestamp);
		result.put(METRIC_MINIMUM_TIMESTAMP, _min_timestamp);
		result.put(METRIC_MAXIMUM_TIMESTAMP, _max_timestamp);
		result.put(METRIC_AVERAGE_MINIMUM_TIMESTAMP, _avg_min_timestamp);
		result.put(METRIC_AVERAGE_MAXIMUM_TIMESTAMP, _avg_max_timestamp);
		result.put(METRIC_LAST_ERROR_TIMESTAMP, _err_timestamp);
		result.put(METRIC_TOTAL_ERROR, _err);
		result.put(METRIC_TOTAL_COUNT, _cnt);
		return result;
	}

	/**
	 * Gets a snapshot hash map of the current state double precision values.
	 * 
	 * @return a snapshot hash map of the current state double precision values
	 */
	public HashMap<String, Double> snapshotPrecisely() {
		final HashMap<String, Double> result = Maps.make();
		if (PRECISION_SUFFIX_MILLISECS.equals(_precisionSuffix)) {
			result.put(METRIC_CURRENT_MILLISECS, _cur);
			result.put(METRIC_MINIMUM_MILLISECS, _min);
			result.put(METRIC_MAXIMUM_MILLISECS, _max);
			result.put(METRIC_AVERAGE_MILLISECS, _avg);
			result.put(METRIC_AVERAGE_MINIMUM_MILLISECS, _avg_min);
			result.put(METRIC_AVERAGE_MAXIMUM_MILLISECS, _avg_max);
		} else if (PRECISION_SUFFIX_NANOSECS.equals(_precisionSuffix)) {
			result.put(METRIC_CURRENT_NANOSECS, _cur);
			result.put(METRIC_MINIMUM_NANOSECS, _min);
			result.put(METRIC_MAXIMUM_NANOSECS, _max);
			result.put(METRIC_AVERAGE_NANOSECS, _avg);
			result.put(METRIC_AVERAGE_MINIMUM_NANOSECS, _avg_min);
			result.put(METRIC_AVERAGE_MAXIMUM_NANOSECS, _avg_max);
		} else {
			result.put(METRIC_CURRENT_NOSUFFIX, _cur);
			result.put(METRIC_MINIMUM_NOSUFFIX, _min);
			result.put(METRIC_MAXIMUM_NOSUFFIX, _max);
			result.put(METRIC_AVERAGE_NOSUFFIX, _avg);
			result.put(METRIC_AVERAGE_MINIMUM_NOSUFFIX, _avg_min);
			result.put(METRIC_AVERAGE_MAXIMUM_NOSUFFIX, _avg_max);
		}
		result.put(METRIC_CURRENT_TIMESTAMP, (double) _cur_timestamp);
		result.put(METRIC_MINIMUM_TIMESTAMP, (double) _min_timestamp);
		result.put(METRIC_MAXIMUM_TIMESTAMP, (double) _max_timestamp);
		result.put(METRIC_AVERAGE_MINIMUM_TIMESTAMP, (double) _avg_min_timestamp);
		result.put(METRIC_AVERAGE_MAXIMUM_TIMESTAMP, (double) _avg_max_timestamp);
		result.put(METRIC_LAST_ERROR_TIMESTAMP, (double) _err_timestamp);
		result.put(METRIC_TOTAL_ERROR, (double) _err);
		result.put(METRIC_TOTAL_COUNT, (double) _cnt);
		return result;
	}

}
