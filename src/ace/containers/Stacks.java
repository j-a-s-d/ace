/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.Ace;
import java.util.Collection;
import java.util.Stack;

/**
 * Utility class for working with stacks.
 */
public class Stacks extends Ace {

	/**
	 * Indicates if the specified stack is not empty.
	 * 
	 * @param stack
	 * @return <tt>true</tt> if the specified stack is assigned (not null) and has one or more elements, <tt>false</tt> otherwise
	 */
	public static boolean hasContent(final Stack stack) {
		return stack != null && stack.size() > 0;
	}

	/**
	 * Creates a stack with the specified values as content.
	 * 
	 * @param <T>
	 * @param values
	 * @return the new stack
	 */
	public static <T> Stack<T> make(final T... values) {
		return new Stack() {
			{
				for (final T value : values) {
					push(value);
				}
			}
		};
	}

	/**
	 * Creates a stack with the combined values of the specified stacks.
	 * 
	 * @param <T>
	 * @param stacks
	 * @return the new stack
	 */
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

	/**
	 * Gets the specified string stack as a string array.
	 * 
	 * @param stack
	 * @return the specified string stack as a string array
	 */
	public static String[] getAsStringArray(final Stack<String> stack) {
		return hasContent(stack) ? stack.toArray(new String[] {}) : new String[] {};
	}

	/**
	 * Indicates if the specified stack contains the specified values.
	 * 
	 * @param <T>
	 * @param stack
	 * @param items
	 * @return <tt>true</tt> if the specified stack contains the specified values, <tt>false</tt> otherwise
	 */
	public static <T> boolean has(final Stack<T> stack, final T... items) {
		for (final T k : items) {
			if (!stack.contains(k)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Indicates if the specified stack contains the values of the specified collection.
	 * 
	 * @param <T>
	 * @param stack
	 * @param items
	 * @return <tt>true</tt> if the specified stack contains the values of the specified collection, <tt>false</tt> otherwise
	 */
	public static <T> boolean has(final Stack<T> stack, final Collection<T> items) {
		for (final T k : items) {
			if (!stack.contains(k)) {
				return false;
			}
		}
		return true;
	}

}
