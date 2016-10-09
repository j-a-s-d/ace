/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericArrays extends Ace {

	// COMPOSITION
	public static final <T> T[] make(final T... items) {
		return items;
	}

	public static final <T> T[] fromList(final List<T> list) {
		return list != null && list.size() > 0 ? list.toArray((T[]) Array.newInstance(list.get(0).getClass(), list.size())) : null;
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
		if (hasContent(array)) {
			final ArrayList<T> list = new ArrayList(Arrays.asList(array));
			int x = 0;
			while (count > x++ && !list.isEmpty()) {
				list.remove(0);
			}
			result = fromList(list);
		}
		return result;
	}

	public static <T> T[] chopEnding(final T[] array, final int count) {
		T[] result = null;
		if (hasContent(array)) {
			final ArrayList<T> list = new ArrayList(Arrays.asList(array));
			int x = 0;
			while (count > x++ && !list.isEmpty()) {
				list.remove(list.size() - 1);
			}
			result = fromList(list);
		}
		return result;
	}

	public static <T> T[] chopBoth(final T[] array, final int count) {
		T[] result = null;
		if (hasContent(array)) {
			final ArrayList<T> list = new ArrayList(Arrays.asList(array));
			int x = 0;
			while (count > x++ && !list.isEmpty()) {
				list.remove(0);
				list.remove(list.size() - 1);
			}
			result = fromList(list);
		}
		return result;
	}

	public static final <T> T[] concat(final T[]... arrays) {
		final ArrayList<T> list = new ArrayList();
		for (final T[] array : arrays) {
			list.addAll(Arrays.asList(array));
		}
		return fromList(list);
	}

}
