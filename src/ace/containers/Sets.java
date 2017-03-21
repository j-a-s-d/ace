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

	public static boolean hasContent(final Set set) {
		return set != null && set.size() > 0;
	}

	public static <T> HashSet<T> make(final T... values) {
		return new HashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	public static <T> Set<T> makeSynchronized(final T... values) {
		return Collections.synchronizedSet(new HashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	public static <T> LinkedHashSet<T> makeLinked(final T... values) {
		return new LinkedHashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		};
	}

	public static <T> Set<T> makeSynchronizedLinked(final T... values) {
		return Collections.synchronizedSet(new LinkedHashSet() {
			{
				for (final T value : values) {
					add(value);
				}
			}
		});
	}

	public static <T> HashSet<T> combine(final Set<T>... sets) {
		return new HashSet() {
			{
				for (final Set<T> set : sets) {
					addAll(set);
				}
			}
		};
	}

	public static <T> HashSet<T> combineLinked(final Set<T>... sets) {
		return new HashSet() {
			{
				for (final Set<T> set : sets) {
					addAll(set);
				}
			}
		};
	}

	public static TreeSet makeTree() {
		return new TreeSet();
	}

	public static TreeSet makeTree(final Set set) {
		return new TreeSet(set);
	}

	public static Set makeSynchronizedTree() {
		return Collections.synchronizedSet(new TreeSet());
	}

	public static Set makeSynchronizedTree(final Set set) {
		return Collections.synchronizedSet(new TreeSet(set));
	}

	public static String[] getAsStringArray(final Set<String> set) {
		return hasContent(set) ? set.toArray(new String[] {}) : new String[] {};
	}

	public static <T> boolean has(final Set<T> set, final T... items) {
		for (final T k : items) {
			if (!set.contains(k)) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean has(final Set<T> set, final Collection<T> items) {
		for (final T k : items) {
			if (!set.contains(k)) {
				return false;
			}
		}
		return true;
	}

}
