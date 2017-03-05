/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Immutable;
import java.util.Iterator;

public class Range extends Ace implements Immutable, Iterable<Integer> {

	public static Range downFrom(final int max) {
		return new Range(0, max, true);
	}

	public static Range upTo(final int max) {
		return new Range(0, max, false);
	}

	public static Range fromTo(final int from, final int to) {
		return from > to ? new Range(to, from, true) : new Range(from, to, false);
	}

	private final int _lower;
	private final int _upper;
	private final boolean _descending;

	private Range(final int from, final int to, final boolean descending) {
		_lower = from;
		_upper = to;
		_descending = descending;
	}

	public int getLowerBound() {
		return _lower;
	}

	public int getUpperBound() {
		return _upper;
	}

	public int cardinality() {
		return _upper - _lower + 1;
	}

	public boolean in(final int value) {
		return (value >= _lower) && (value <= _upper);
	}

	public int[] toArray() {
		final int numbersInRange = cardinality();
		final int[] result = new int[numbersInRange];
		for (int i = 0; i < numbersInRange; i++) {
			result[i] = i;
		}
		return result;
	}


	// NOTE: JDK 5 will not allow @Override annotations on implemented methods of an interface
	/*@Override*/ public Iterator<Integer> iterator() {
		return new Iterator() {
			int _index = _descending ? _upper : _lower;

			/*@Override*/ public boolean hasNext() {
				return _descending ? _index >= _lower : _index <= _upper;
			}

			/*@Override*/ public Integer next() {
				return hasNext() ? (_descending ? _index-- : _index++) : null;
			}

			/*@Override*/ public void remove() {
				//
			}
		};
	}

}
