/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;
import ace.interfaces.Immutable;
import java.util.Iterator;

/**
 * Useful immutable array class.
 */
public class ImmutableArray<T> extends Ace implements Immutable, Iterable<T> {

	private final T[] _array;

	/**
	 * Default constructor taking any amount of values.
	 * 
	 * @param values
	 */
	public ImmutableArray(final T... values) {
		_array = values;
	}

	/**
	 * Gets this array instance as native array.
	 * 
	 * @return the array instance content as a native array
	 */
	public T[] asNativeArray() {
		return _array;
	}

	/**
	 * Indicates if this array instance has content.
	 * 
	 * @return <tt>true</tt> if this array instance has more than 0 elements, <tt>false</tt> otherwise
	 */
	public boolean hasContent() {
		return _array.length > 0;
	}

	/**
	 * Indicates if this array instance contains the provided value.
	 * 
	 * @param value
	 * @return <tt>true</tt> if this array instance contains the provided value, <tt>false</tt> otherwise
	 */
	public boolean has(final T value) {
		for (final T v : _array) {
			if (v.equals(value) || v == value) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieves the first element if any.
	 * 
	 * @return the first element if any or <tt>null</tt> otherwise
	 */
	public T head() {
		return _array.length > 0 ? _array[0] : null;
	}

	/**
	 * Retrieves the last element if any.
	 * 
	 * @return the last element if any or <tt>null</tt> otherwise
	 */
	public T tail() {
		return _array.length > 0 ? _array[_array.length - 1] : null;
	}

	/**
	 * Retrieves the element at the specified index.
	 * 
	 * @return the element at the specified index or <tt>null</tt> if the array has not content
	 */
	public T get(final int index) {
		return _array.length > 0 ? _array[index] : null;
	}

	/**
	 * Retrieves this array instance size.
	 * 
	 * @return this array instance size
	 */
	public int size() {
		return _array.length;
	}

	/**
	 * Retrieves this array instance iterator.
	 * 
	 * @return this array instance iterator
	 */
	/*@Override*/ public Iterator<T> iterator() { // NOTE: JDK 5 will not allow @Override annotations on implemented methods of an interface
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
