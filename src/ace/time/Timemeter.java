/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import ace.Ace;
import ace.interfaces.Reseteable;

abstract class Timemeter extends Ace implements Reseteable {

	protected Long _begin = null;
	protected Long _end = null;

	/*@Override*/ public void reset() {
		_begin = null;
		_end = null;
	}

	protected final void begin(final long value) {
		_begin = value;
		_end = null;
	}

	protected final void end(final long value) {
		_end = value;
	}

	protected final long snapshot(final long value) {
		if (_begin == null) {
			return 0;
		}
		return value - _begin;
	}

	protected long measure() {
		if (_begin == null || _end == null) {
			return 0;
		}
		return _end - _begin;
	}

}
