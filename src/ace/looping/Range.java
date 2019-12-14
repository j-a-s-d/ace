/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Immutable;
import java.util.Iterator;

/**
 * Utility class for working with ranges.
 */
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

	/**
	 * Gets the lower bound of this range.
	 * 
	 * @return the lower bound of this range
	 */
	public int getLowerBound() {
		return _lower;
	}

	/**
	 * Gets the upper bound of this range.
	 * 
	 * @return the upper bound of this range
	 */
	public int getUpperBound() {
		return _upper;
	}

	/**
	 * Gets the cardinality (amount of numbers) of this range.
	 * 
	 * @return the cardinality (amount of numbers) of this range
	 */
	public int cardinality() {
		return _upper - _lower + 1;
	}

	/**
	 * Determines if the specified value is inside this range.
	 * 
	 * @param value
	 * @return <tt>true</tt> if the specified value is inside this range, <tt>false</tt> otherwise
	 */
	public boolean in(final int value) {
		return (value >= _lower) && (value <= _upper);
	}

	/**
	 * Gets this range as an int array.
	 * 
	 * @return this range as an int array
	 */
	public int[] toArray() {
		final int numbersInRange = cardinality();
		final int[] result = new int[numbersInRange];
		for (int i = 0; i < numbersInRange; i++) {
			result[i] = i;
		}
		return result;
	}

	/**
	 * Gets the iterator of this range.
	 * 
	 * @return the iterator of this range
	 */
	/*@Override*/ public Iterator<Integer> iterator() { // NOTE: JDK 5 will not allow @Override annotations on implemented methods of an interface
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
