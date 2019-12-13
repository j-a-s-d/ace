/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Processor;
import java.util.Collection;

/**
 * Useful items mapper class.
 * 
 * @param <T> Value type to be mapped.
 */
public abstract class ItemsLooper<T> extends Ace implements Processor<T> {

	/**
	 * Constructor accepting items.
	 * 
	 * @param items 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ItemsLooper(final T... items) {
		for (final T item : items) {
			process(item);
		}
	}

	/**
	 * Constructor accepting a collection.
	 * 
	 * @param items 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ItemsLooper(final Collection<T> items) {
		for (final T item : items) {
			process(item);
		}
	}

}
