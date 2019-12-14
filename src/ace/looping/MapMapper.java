/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Useful map mapper class.
 * 
 * @param <K> Key type to be mapped.
 * @param <V> Value type to be mapped.
 */
public abstract class MapMapper<K, V> extends BaseMapper<Map<K, V>, V> {

	/**
	 * Constructor accepting a map.
	 * 
	 * @param items 
	 */
	public MapMapper(final Map<K, V> items) {
		super(items);
		for (final Entry<K, V> entry : items.entrySet()) {
			_result.put(entry.getKey(), treat(entry.getValue()));
		}
	}

}
