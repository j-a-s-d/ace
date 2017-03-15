/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import ace.Ace;
import ace.containers.Maps;
import ace.interfaces.Reseteable;
import java.util.HashMap;

public class SampleProfiler extends Ace implements Reseteable {

	public static final String PRECISION_SUFFIX_NONE = "";
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
	private long _max;
	private long _min_timestamp;
	private long _min;
	private long _avg_max_timestamp;
	private long _avg_max;
	private long _avg_min_timestamp;
	private long _avg_min;
	private long _avg;
	private long _cur_timestamp;
	private long _cur;

	private String _precisionSuffix = PRECISION_SUFFIX_MILLISECS;

	public SampleProfiler() {
		this(null);
	}

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

	public boolean isPrecisionSuffixNone() {
		return PRECISION_SUFFIX_NONE.equals(_precisionSuffix);
	}

	public boolean isPrecisionSuffixMilliseconds() {
		return PRECISION_SUFFIX_MILLISECS.equals(_precisionSuffix);
	}

	public boolean isPrecisionSuffixNanoseconds() {
		return PRECISION_SUFFIX_NANOSECS.equals(_precisionSuffix);
	}

	public SampleProfiler setPrecisionSuffixToNone() {
		_precisionSuffix = PRECISION_SUFFIX_NONE;
		return this;
	}

	public SampleProfiler setPrecisionSuffixToMilliseconds() {
		_precisionSuffix = PRECISION_SUFFIX_MILLISECS;
		return this;
	}

	public SampleProfiler setPrecisionSuffixToNanoseconds() {
		_precisionSuffix = PRECISION_SUFFIX_NANOSECS;
		return this;
	}

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
			_min = _max = value;
			_min_timestamp = _max_timestamp = _cur_timestamp;
		}
		if (!success) {
			_err++;
			_err_timestamp = _cur_timestamp;
		}
		_cnt++;
	}

	public long getCurrent() {
		return _cur;
	}

	public long getMinimum() {
		return _min;
	}

	public long getMaximum() {
		return _max;
	}

	public long getAverage() {
		return _avg;
	}

	public long getAverageMinimum() {
		return _avg_min;
	}

	public long getAverageMaximum() {
		return _avg_max;
	}

	public long getCurrentTimestamp() {
		return _cur_timestamp;
	}

	public long getMinimumTimestamp() {
		return _min_timestamp;
	}

	public long getMaximumTimestamp() {
		return _max_timestamp;
	}

	public long getAverageMinimumTimestamp() {
		return _avg_min_timestamp;
	}

	public long getAverageMaximumTimestamp() {
		return _avg_max_timestamp;
	}

	public long getLastErrorTimestamp() {
		return _err_timestamp;
	}

	public long getTotalErrors() {
		return _err;
	}

	public long getTotalCount() {
		return _cnt;
	}

	public HashMap<String, Long> snapshot() {
		final HashMap<String, Long> result = Maps.make();
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

}
