/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.Ace;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Utility class for working with sets.
 */
public class Sets extends Ace {

	/**
	 * Indicates if the specified set is not empty.
	 * 
	 * @param set
	 * @return <tt>true</tt> if the specified set is assigned (not null) and has one or more elements, <tt>false</tt> otherwise
	 */
	public static boolean hasContent(final Set set) {
		return set != null && set.size() > 0;
	}

	/**
	 * Creates a set with the specified values as content.
	 * 
	 * @param <T>
	 * @param values
	 * @return the new set
	 */
	public static <T> HashSet<T> make(final T... values) {
		return new HashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	/**
	 * Creates a synchronized set with the specified values as content.
	 * 
	 * @param <T>
	 * @param values
	 * @return the new set
	 */
	public static <T> Set<T> makeSynchronized(final T... values) {
		return Collections.synchronizedSet(new HashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	/**
	 * Creates a linked set with the specified values as content.
	 * 
	 * @param <T>
	 * @param values
	 * @return the new set
	 */
	public static <T> LinkedHashSet<T> makeLinked(final T... values) {
		return new LinkedHashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	/**
	 * Creates a synchronized linked set with the specified values as content.
	 * 
	 * @param <T>
	 * @param values
	 * @return the new set
	 */
	public static <T> Set<T> makeSynchronizedLinked(final T... values) {
		return Collections.synchronizedSet(new LinkedHashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	/**
	 * Creates a set with the combined values of the specified sets.
	 * 
	 * @param <T>
	 * @param sets
	 * @return the new set
	 */
	public static <T> HashSet<T> combine(final Set<T>... sets) {
		return new HashSet() {
			{
				for (final Set<T> set : sets) {
					addAll(set);
				}
			}
		};
	}

	/**
	 * Creates a linked set with the combined values of the specified sets.
	 * 
	 * @param <T>
	 * @param sets
	 * @return the new set
	 */
	public static <T> HashSet<T> combineLinked(final Set<T>... sets) {
		return new HashSet() {
			{
				for (final Set<T> set : sets) {
					addAll(set);
				}
			}
		};
	}

	/**
	 * Creates a new empty tree set.
	 * 
	 * @return the new set
	 */
	public static TreeSet makeTree() {
		return new TreeSet();
	}

	/**
	 * Creates a new tree set with the elements of the specified set as content.
	 * 
	 * @param set
	 * @return the new set
	 */
	public static TreeSet makeTree(final Set set) {
		return new TreeSet(set);
	}

	/**
	 * Creates a new empty synchronized tree set.
	 * 
	 * @return the new set
	 */
	public static Set makeSynchronizedTree() {
		return Collections.synchronizedSet(new TreeSet());
	}

	/**
	 * Creates a new synchronized tree set with the elements of the specified set as content.
	 * 
	 * @param set
	 * @return the new set
	 */
	public static Set makeSynchronizedTree(final Set set) {
		return Collections.synchronizedSet(new TreeSet(set));
	}

	/**
	 * Gets the specified string set as a string array.
	 * 
	 * @param set
	 * @return the specified string set as a string array
	 */
	public static String[] getAsStringArray(final Set<String> set) {
		return hasContent(set) ? set.toArray(new String[] {}) : new String[] {};
	}

	/**
	 * Indicates if the specified set contains the specified values.
	 * 
	 * @param <T>
	 * @param set
	 * @param items
	 * @return <tt>true</tt> if the specified set contains the specified values, <tt>false</tt> otherwise
	 */
	public static <T> boolean has(final Set<T> set, final T... items) {
		for (final T k : items) {
			if (!set.contains(k)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Indicates if the specified set contains the values of the specified collection.
	 * 
	 * @param <T>
	 * @param set
	 * @param items
	 * @return <tt>true</tt> if the specified set contains the values of the specified collection, <tt>false</tt> otherwise
	 */
	public static <T> boolean has(final Set<T> set, final Collection<T> items) {
		for (final T k : items) {
			if (!set.contains(k)) {
				return false;
			}
		}
		return true;
	}

}
