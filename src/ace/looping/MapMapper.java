/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import java.util.Map;
import java.util.Map.Entry;

public abstract class MapMapper<K, V> extends BaseMapper<Map<K, V>, V> {

	public MapMapper(final Map<K, V> items) {
		super(items);
		for (final Entry<K, V> entry : items.entrySet()) {
			_result.put(entry.getKey(), treat(entry.getValue()));
		}
	}

}
