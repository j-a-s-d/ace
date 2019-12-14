/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import java.util.List;

/**
 * Useful list mapper class.
 * 
 * @param <T> Value type to be mapped.
 */
public abstract class ListMapper<T> extends BaseMapper<List<T>, T> {

	/**
	 * Constructor accepting a list.
	 * 
	 * @param items 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ListMapper(final List<T> items) {
		super(items);
		for (int x = items.size() - 1; x > -1; x--) {
			_result.set(x, treat(items.get(x)));
		}
	}

}
