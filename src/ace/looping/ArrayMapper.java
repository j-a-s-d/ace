/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

/**
 * Useful array mapper class.
 * 
 * @param <T> Value type to be mapped.
 */
public abstract class ArrayMapper<T> extends BaseMapper<T[], T> {

	/**
	 * Constructor accepting an array.
	 * 
	 * @param items 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ArrayMapper(final T... items) {
		super(items);
		for (int x = items.length - 1; x > -1; x--) {
			_result[x] = treat(items[x]);
		}
	}

}
