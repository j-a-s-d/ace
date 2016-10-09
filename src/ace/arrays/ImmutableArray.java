/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;
import ace.interfaces.Immutable;
import java.util.Iterator;

/**
 * Useful immutable array class.
 */
public class ImmutableArray<T> extends Ace implements Immutable, Iterable<T> {

	private final T[] _array;

	public ImmutableArray(final T... values) {
		_array = values;
	}

	public T[] asNativeArray() {
		return _array;
	}

	public boolean hasContent() {
		return _array.length > 0;
	}

	public boolean has(final T value) {
		for (final T v : _array) {
			if (v.equals(value) || v == value) {
				return true;
			}
		}
		return false;
	}

	public T head() {
		return _array.length > 0 ? _array[0] : null;
	}

	public T tail() {
		return _array.length > 0 ? _array[_array.length - 1] : null;
	}

	public T get(final int index) {
		return _array.length > 0 ? _array[index] : null;
	}

	public int size() {
		return _array.length;
	}


	// NOTE: JDK 5 will not allow @Override annotations on implemented methods of an interface
	/*@Override*/ public Iterator<T> iterator() {
		return new Iterator() {
			int _index = 0;

			/*@Override*/ public boolean hasNext() {
				return _index < _array.length;
			}

			/*@Override*/ public T next() {
				return hasNext() ? _array[_index++] : null;
			}

			/*@Override*/ public void remove() {
				//
			}
		};
	}

}
