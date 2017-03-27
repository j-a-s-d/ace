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
	public static final <T> T[] make(final T... items) {
		return items;
	}

	public static final <T> T[] fromClass(final Class<?> clazz, final int length) {
		return clazz != null && length > -1 ? (T[]) Array.newInstance(clazz, length) : null;
	}

	public static final <T> T[] fromCollection(final Collection<T> collection) {
		return collection != null && collection.size() > 0 ? collection.toArray((T[]) fromClass(collection.iterator().next().getClass(), collection.size())) : null;
	}

	// CONTENT
	public static final <T> boolean hasContent(final T[] array) {
		return array != null && array.length > 0;
	}

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

	public static final <T> T[] filterNull(final T[] array) {
		return fromCollection(new ArrayList<T>() {{
			for (final T element : array) {
				if (element != null) {
					add(element);
				}
			}
		}});
	}

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

	public static final <T> T get(final T[] array, final int index) {
		return hasContent(array) && array.length > index && index >= 0 ? array[index] : null;
	}

	public static final <T> T first(final T[] array) {
		return hasContent(array) ? array[0] : null;
	}

	public static final <T> T last(final T[] array) {
		return hasContent(array) ? array[array.length - 1] : null;
	}

	public static final <T> T[] firstAndLast(final T[] array) {
		return hasContent(array) ? make(array[0], array[array.length - 1]) : null;
	}

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

	public static final <T> T[] append(final T[] array, final T... elements) {
		return concat(array, elements);
	}

	public static final <T> T[] concat(final T[]... arrays) {
		return fromCollection(new ArrayList<T>() {{
			for (final T[] array : arrays) {
				for (final T element : array) {
					add(element);
				}
			}
		}});
	}

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

	public static final <T> int indexOf(final T[] buffer, final T[] sequence) {
		return indexOf(buffer, 0, sequence);
	}

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

	public static final <T> int indexOfFirstNull(final T[] array) {
		return indexOfFirstNullInRange(array, 0, array.length);
	}

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
