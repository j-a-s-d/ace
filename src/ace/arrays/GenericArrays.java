/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Utility class for working with generic arrays.
 */
public class GenericArrays extends Ace {

	// COMPOSITION

	/**
	 * Creates an array with the specified values as content.
	 * 
	 * @param <T>
	 * @param items
	 * @return the new array
	 */
	public static final <T> T[] make(final T... items) {
		return items;
	}

	/**
	 * Creates an array of the specified class type and with the specified length.
	 * 
	 * @param clazz
	 * @param length
	 * @return the new array
	 */
	public static final <T> T[] fromClass(final Class<?> clazz, final int length) {
		return clazz != null && length > -1 ? (T[]) Array.newInstance(clazz, length) : null;
	}

	/**
	 * Creates an array with the specified collection values as content.
	 * 
	 * @param <T>
	 * @param collection
	 * @return the new array
	 */
	public static final <T> T[] fromCollection(final Collection<T> collection) {
		return collection != null && collection.size() > 0 ? collection.toArray((T[]) fromClass(collection.iterator().next().getClass(), collection.size())) : null;
	}

	// CONTENT

	/**
	 * Indicates if the specified array has one or more elements.
	 * 
	 * @param <T>
	 * @param array
	 * @return <tt>true</tt> if the specified array has one or more elements, <tt>false</tt> otherwise
	 */
	public static final <T> boolean hasContent(final T[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * Indicates if the specified array does not have any null elements.
	 * 
	 * @param <T>
	 * @param array
	 * @return <tt>true</tt> if all of the elements of the specified array are assigned (not null), <tt>false</tt> otherwise
	 */
	public static final <T> boolean nullFree(final T[] array) {
		if (hasContent(array)) {
			boolean result = true;
			for (final Object o : array) {
				result &= o != null;
			}
			return result;
		}
		return false;
	}

	/**
	 * Returns the amount of assigned elements (not null) in the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @return the amount of assigned elements (not null) in the specified array
	 */
	public static final <T> int countNotNull(final T[] array) {
		int result = 0;
		if (hasContent(array)) {
			for (final Object o : array) {
				if (o != null) {
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * Returns a new array instance containing only the assigned (not null) elements of the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @return the new array
	 */
	public static final <T> T[] filterNull(final T[] array) {
		return fromCollection(new ArrayList<T>() {{
			for (final T element : array) {
				if (element != null) {
					add(element);
				}
			}
		}});
	}

	/**
	 * Indicates if the specified array contains the specified value.
	 * 
	 * @param <T>
	 * @param array
	 * @param value
	 * @return <tt>true</tt> if the specified array contains the specified value, <tt>false</tt> otherwise
	 */
	public static final <T> boolean contains(final T[] array, final T value) {
		if (hasContent(array)) {
			if (value == null) {
				for (final T element : array) {
					if (element == null) {
						return true;
					}
				}
			} else {
				for (final T element : array) {
					if (element == value || value.equals(element)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns the element in the specified index of the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @param index
	 * @return the element in the specified index of the specified array or <tt>null</tt> if the index does not exist
	 */
	public static final <T> T get(final T[] array, final int index) {
		return hasContent(array) && array.length > index && index >= 0 ? array[index] : null;
	}

	/**
	 * Returns the first element in the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @return the first element in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static final <T> T first(final T[] array) {
		return hasContent(array) ? array[0] : null;
	}

	/**
	 * Returns the last element in the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @return the last element in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static final <T> T last(final T[] array) {
		return hasContent(array) ? array[array.length - 1] : null;
	}

	/**
	 * Returns an array containing the first and the last elements in the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @return an array containing the first and the last elements in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static final <T> T[] firstAndLast(final T[] array) {
		return hasContent(array) ? make(array[0], array[array.length - 1]) : null;
	}

	/**
	 * Returns an array containing the elements of the specified array except the specified amount of elements from the beginning.
	 * 
	 * @param <T>
	 * @param array
	 * @param count
	 * @return the resulting array
	 */
	public static <T> T[] chopBeginning(final T[] array, final int count) {
		T[] result = null;
		if (nullFree(array)) {
			final ArrayList<T> list = new ArrayList(Arrays.asList(array));
			int x = 0;
			while (count > x++ && !list.isEmpty()) {
				list.remove(0);
			}
			result = fromCollection(list);
		}
		return result;
	}

	/**
	 * Returns an array containing the elements of the specified array except the specified amount of elements from the ending.
	 * 
	 * @param <T>
	 * @param array
	 * @param count
	 * @return the resulting array
	 */
	public static <T> T[] chopEnding(final T[] array, final int count) {
		T[] result = null;
		if (nullFree(array)) {
			final ArrayList<T> list = new ArrayList(Arrays.asList(array));
			int x = 0;
			while (count > x++ && !list.isEmpty()) {
				list.remove(list.size() - 1);
			}
			result = fromCollection(list);
		}
		return result;
	}

	/**
	 * Returns an array containing the elements of the specified array except the specified amount of elements from the beginning and the ending.
	 * 
	 * @param <T>
	 * @param array
	 * @param count
	 * @return the resulting array
	 */
	public static <T> T[] chopBoth(final T[] array, final int count) {
		T[] result = null;
		if (nullFree(array)) {
			final ArrayList<T> list = new ArrayList(Arrays.asList(array));
			int x = 0;
			while (count > x++ && !list.isEmpty()) {
				list.remove(0);
				list.remove(list.size() - 1);
			}
			result = fromCollection(list);
		}
		return result;
	}

	/**
	 * Returns an array containing the elements of the specified array plus the specified elements.
	 * 
	 * @param <T>
	 * @param array
	 * @param elements
	 * @return the resulting array
	 */
	public static final <T> T[] append(final T[] array, final T... elements) {
		return concat(array, elements);
	}

	/**
	 * Concatenates the specified arrays into a new array.
	 * 
	 * @param <T>
	 * @param arrays
	 * @return the resulting array
	 */
	public static final <T> T[] concat(final T[]... arrays) {
		return fromCollection(new ArrayList<T>() {{
			for (final T[] array : arrays) {
				for (final T element : array) {
					add(element);
				}
			}
		}});
	}

	/**
	 * Retrieves an array containing the elements in the specified segment of the specified array.
	 * 
	 * @param <T>
	 * @param buffer
	 * @param offset
	 * @param length
	 * @return the resulting array
	 */
	public static <T> T[] copy(final T[] buffer, final int offset, final int length) {
		if (buffer == null || buffer.length == 0 || offset < 0 || length < 0 || offset + length > buffer.length) {
			return null;
		}
		final T[] result = (T[]) Array.newInstance(buffer[0].getClass(), length);
		int index = 0;
		for (int i = offset; i < offset + length; i++) {
			result[index++] = buffer[i];
		}
		return result;
	}

	/**
	 * Retrieves an array containing the elements in the specified segment of the specified array but in the reverse order.
	 * 
	 * @param <T>
	 * @param buffer
	 * @param offset
	 * @param length
	 * @return the resulting array
	 */
	public static <T> T[] invertedCopy(final T[] buffer, final int offset, final int length) {
		if (buffer == null || buffer.length == 0 || offset < 0 || length < 0 || offset + length > buffer.length) {
			return null;
		}
		final T[] result = (T[]) Array.newInstance(buffer[0].getClass(), length);
		int index = length - 1;
		for (int i = offset; (index > -1) && (i < offset + length); i++) {
			result[index--] = buffer[i];
		}
		return result;
	}

	/**
	 * Reverses the specified array.
	 * 
	 * @param <T>
	 * @param array 
	 */
	public static <T> void reverse(final T[] array) {
		if (array != null) {
			int i = 0;
			int j = array.length - 1;
			T tmp;
			while (j > i) {
				tmp = array[j];
				array[j] = array[i];
				array[i] = tmp;
				j--;
				i++;
			}
		}
	}

	/**
	 * Returns the index of the specified sequence in the specified array starting to search in the specified offset.
	 * 
	 * @param <T>
	 * @param buffer
	 * @param startOffset
	 * @param sequence
	 * @return the index of the sequence
	 */
	public static final <T> int indexOf(final T[] buffer, final int startOffset, final T[] sequence) {
		if (buffer != null && buffer.length > 0 && sequence != null && sequence.length > 0) {
			for (int i = startOffset; i < buffer.length - sequence.length + 1; ++i) {
				boolean found = true;
				for (int j = 0; j < sequence.length; ++j) {
					if ((buffer[i + j] == null && sequence[j] != null)
						|| (!buffer[i + j].equals(sequence[j]))) {
						found = false;
						break;
					}
				}
				if (found) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Returns the index of the specified sequence in the specified array.
	 * 
	 * @param <T>
	 * @param buffer
	 * @param sequence
	 * @return the index of the sequence
	 */
	public static final <T> int indexOf(final T[] buffer, final T[] sequence) {
		return indexOf(buffer, 0, sequence);
	}

	/**
	 * Returns the array of indexes of the specified sequence in the specified array.
	 * 
	 * @param <T>
	 * @param buffer
	 * @param sequence
	 * @return the array of indexes of the sequence in the specified array
	 */
	public static final <T> int[] indexesOf(final T[] buffer, final T[] sequence) {
		int[] result = new int[] {};
		if (buffer != null && buffer.length > 0 && sequence != null && sequence.length > 0) {
			for (int i = 0; i < buffer.length - sequence.length + 1; ++i) {
				boolean found = true;
				for (int j = 0; j < sequence.length; ++j) {
					if ((buffer[i + j] == null && sequence[j] != null)
						|| (!buffer[i + j].equals(sequence[j]))) {
						found = false;
						break;
					}
				}
				if (found) {
					final int l = result.length;
					final int[] aux = new int[l + 1];
					System.arraycopy(result, 0, aux, 0, l);
					aux[l] = i;
					result = aux;
				}
			}
		}
		return result;
	}

	/**
	 * Returns the index of the first assigned (not null) element in the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @return the index of the first assigned (not null) element
	 */
	public static final <T> int indexOfFirstNull(final T[] array) {
		return indexOfFirstNullInRange(array, 0, array.length);
	}

	/**
	 * Returns the index of the first assigned (not null) element inside the specified index range in the specified array.
	 * 
	 * @param <T>
	 * @param array
	 * @param index
	 * @param max
	 * @return the index of the first assigned (not null) element inside the specified index range
	 */
	public static final <T> int indexOfFirstNullInRange(final T[] array, final int index, final int max) {
		if (max > index && index > -1 && array.length >= max) {
			for (int i = index; i < max - 1; i++) {
				if (array[i] == null) {
					return i;
				}
			}
		}
		return -1;
	}

}
