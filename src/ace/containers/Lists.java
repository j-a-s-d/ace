/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.Ace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility class for working with lists.
 */
public class Lists extends Ace {

	public static boolean hasContent(final List list) {
		return list != null && list.size() > 0;
	}

	public static <T> List<T> make(final T... values) {
		return new ArrayList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	public static <T> List<T> makeSynchronized(final T... values) {
		return Collections.synchronizedList(new ArrayList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	public static <T> LinkedList<T> makeLinked(final T... values) {
		return new LinkedList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	public static <T> List<T> makeSynchronizedLinked(final T... values) {
		return Collections.synchronizedList(new LinkedList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	public static <T> List<T> combine(final List<T>... lists) {
		return new ArrayList() {
			{
				for (final List<T> list : lists) {
					addAll(list);
				}
			}
		};
	}

	public static <T> List<T> combineLinked(final List<T>... lists) {
		return new LinkedList() {
			{
				for (final List<T> list : lists) {
					addAll(list);
				}
			}
		};
	}

	public static <T> boolean hasSameValueItems(final List<T> list) {
		if (hasContent(list)) {
			boolean result = true;
			final T first = list.get(0);
			for (final T value : list) {
				result &= first.equals(value);
			}
			return result;
		}
		return false;
	}

	public static <T> T get(final List<T> list, final int index) {
		return hasContent(list) && list.size() > index && index >= 0 ? list.get(index) : null;
	}

	public static <T> T last(final List<T> list) {
		return hasContent(list) ? list.get(list.size() - 1) : null;
	}

	public static <T> List<T> firstAndLast(final List<T> list) {
		return hasContent(list) ? make(list.get(0), list.get(list.size() - 1)) : null;
	}

	public static <T> List<T> firstAndLastSynchronized(final List<T> list) {
		return hasContent(list) ? makeSynchronized(list.get(0), list.get(list.size() - 1)) : null;
	}

	public static <T> List<T> firstAndLastLinked(final List<T> list) {
		return hasContent(list) ? makeLinked(list.get(0), list.get(list.size() - 1)) : null;
	}

	public static <T> List<T> firstAndLastSynchronizedLinked(final List<T> list) {
		return hasContent(list) ? makeSynchronizedLinked(list.get(0), list.get(list.size() - 1)) : null;
	}

	public static <T> List<T> extractSublist(final List<T> list, final int from, final int to) {
		if (hasContent(list)) {
			final int size = list.size();
			final int lowerBound = from < 0 ? 0 : from + 1 > size ? size : from;
			final int upperBound = to < 0 ? 0 : to + 1 > size ? size : to;
			//TODO: instantiate the list by reflection
			//final List<T> newList = list.getClass().getDeclaredConstructor(list.getClass()).newInstance(list);
			final List<T> newList = list instanceof LinkedList ? new LinkedList<T>() : new ArrayList<T>();
			for (int i = lowerBound; i < upperBound; i++) {
				newList.add(list.get(i));
			}
			return newList;
		}
		return list;
	}

}
