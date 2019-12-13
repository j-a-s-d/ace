/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.Ace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility class for working with lists.
 */
public class Lists extends Ace {

	/**
	 * Indicates if the specified list is not empty.
	 * 
	 * @param list
	 * @return <tt>true</tt> if the specified list is assigned (not null) and has one or more elements, <tt>false</tt> otherwise
	 */
	public static boolean hasContent(final List list) {
		return list != null && list.size() > 0;
	}

	/**
	 * Creates a list with the specified values as content.
	 * 
	 * @param values
	 * @return the new list
	 */
	public static <T> ArrayList<T> make(final T... values) {
		return new ArrayList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	/**
	 * Creates a synchronized list with the specified values as content.
	 * 
	 * @param values
	 * @return the new list
	 */
	public static <T> List<T> makeSynchronized(final T... values) {
		return Collections.synchronizedList(new ArrayList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	/**
	 * Creates a linked list with the specified values as content.
	 * 
	 * @param values
	 * @return the new list
	 */
	public static <T> LinkedList<T> makeLinked(final T... values) {
		return new LinkedList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	/**
	 * Creates a synchronized linked list with the specified values as content.
	 * 
	 * @param values
	 * @return the new list
	 */
	public static <T> List<T> makeSynchronizedLinked(final T... values) {
		return Collections.synchronizedList(new LinkedList() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	/**
	 * Creates a list with the combined values of the specified lists.
	 * 
	 * @param <T>
	 * @param lists
	 * @return the new list
	 */
	public static <T> ArrayList<T> combine(final List<T>... lists) {
		return new ArrayList() {
			{
				for (final List<T> list : lists) {
					addAll(list);
				}
			}
		};
	}

	/**
	 * Creates a linked list with the combined values of the specified lists.
	 * 
	 * @param <T>
	 * @param lists
	 * @return the new list
	 */
	public static <T> LinkedList<T> combineLinked(final List<T>... lists) {
		return new LinkedList() {
			{
				for (final List<T> list : lists) {
					addAll(list);
				}
			}
		};
	}

	/**
	 * Indicates if the list has the same value repeated in all elements.
	 * 
	 * @param <T>
	 * @param list
	 * @return <tt>true</tt> if the list is filled with the same repeated value, <tt>false</tt> otherwise
	 */
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

	/**
	 * Returns the element in the specified index of the specified list.
	 * 
	 * @param <T>
	 * @param list
	 * @param index
	 * @return the element in the specified index of the specified list or <tt>null</tt> if the index does not exist
	 */
	public static <T> T get(final List<T> list, final int index) {
		return hasContent(list) && list.size() > index && index >= 0 ? list.get(index) : null;
	}

	/**
	 * Returns the last element in the specified list.
	 * 
	 * @param <T>
	 * @param list
	 * @return the last element in the specified list or <tt>null</tt> if the specified array has no content
	 */
	public static <T> T last(final List<T> list) {
		return hasContent(list) ? list.get(list.size() - 1) : null;
	}

	/**
	 * Returns a list containing the first and the last elements in the specified array.
	 * 
	 * @param <T>
	 * @param list
	 * @return a list containing the first and the last elements in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static <T> ArrayList<T> firstAndLast(final List<T> list) {
		return hasContent(list) ? make(list.get(0), list.get(list.size() - 1)) : null;
	}

	/**
	 * Returns a synchronized list containing the first and the last elements in the specified array.
	 * 
	 * @param <T>
	 * @param list
	 * @return a synchronized list containing the first and the last elements in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static <T> List<T> firstAndLastSynchronized(final List<T> list) {
		return hasContent(list) ? makeSynchronized(list.get(0), list.get(list.size() - 1)) : null;
	}

	/**
	 * Returns a linked list containing the first and the last elements in the specified array.
	 * 
	 * @param <T>
	 * @param list
	 * @return a linked list containing the first and the last elements in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static <T> LinkedList<T> firstAndLastLinked(final List<T> list) {
		return hasContent(list) ? makeLinked(list.get(0), list.get(list.size() - 1)) : null;
	}

	/**
	 * Returns a synchronized linked list containing the first and the last elements in the specified array.
	 * 
	 * @param <T>
	 * @param list
	 * @return a synchronized linked list containing the first and the last elements in the specified array or <tt>null</tt> if the specified array has no content
	 */
	public static <T> List<T> firstAndLastSynchronizedLinked(final List<T> list) {
		return hasContent(list) ? makeSynchronizedLinked(list.get(0), list.get(list.size() - 1)) : null;
	}

	/**
	 * Returns a list containing the elements of the specified list between the specified indexes.
	 * 
	 * @param <T>
	 * @param list
	 * @param from
	 * @param to
	 * @return a list containing the elements of the specified list between the specified indexes
	 */
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

	/**
	 * Indicates if the specified list contains the specified values.
	 * 
	 * @param <T>
	 * @param list
	 * @param items
	 * @return <tt>true</tt> if the specified list contains the specified values, <tt>false</tt> otherwise
	 */
	public static <T> boolean has(final List<T> list, final T... items) {
		for (final T k : items) {
			if (!list.contains(k)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Indicates if the specified list contains the values of the specified collection.
	 * 
	 * @param <T>
	 * @param list
	 * @param items
	 * @return <tt>true</tt> if the specified list contains the values of the specified collection, <tt>false</tt> otherwise
	 */
	public static <T> boolean has(final List<T> list, final Collection<T> items) {
		for (final T k : items) {
			if (!list.contains(k)) {
				return false;
			}
		}
		return true;
	}

}
