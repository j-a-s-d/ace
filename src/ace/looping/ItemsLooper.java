/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Processor;
import java.util.Collection;

public abstract class ItemsLooper<T> extends Ace implements Processor<T> {

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ItemsLooper(final T... items) {
		for (final T item : items) {
			process(item);
		}
	}

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ItemsLooper(final Collection<T> items) {
		for (final T item : items) {
			process(item);
		}
	}

}
