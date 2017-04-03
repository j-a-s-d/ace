/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.Ace;
import java.util.Collection;
import java.util.Stack;

/**
 * Utility class for working with stacks.
 */
public class Stacks extends Ace {

	public static boolean hasContent(final Stack stack) {
		return stack != null && stack.size() > 0;
	}

	public static <T> Stack<T> make(final T... values) {
		return new Stack() {
			{
				for (final T value : values) {
					push(value);
				}
			}
		};
	}

	public static <T> Stack<T> combine(final Stack<T>... stacks) {
		return new Stack() {
			{
				for (final Stack<T> stack : stacks) {
					for (final T value : stack) {
						push(value);
					}
				}
			}
		};
	}

	public static String[] getAsStringArray(final Stack<String> stack) {
		return hasContent(stack) ? stack.toArray(new String[] {}) : new String[] {};
	}

	public static <T> boolean has(final Stack<T> stack, final T... items) {
		for (final T k : items) {
			if (!stack.contains(k)) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean has(final Stack<T> set, final Collection<T> items) {
		for (final T k : items) {
			if (!set.contains(k)) {
				return false;
			}
		}
		return true;
	}

}
