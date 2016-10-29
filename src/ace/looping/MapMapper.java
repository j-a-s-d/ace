/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Treater;
import java.util.Map;
import java.util.Map.Entry;

public abstract class MapMapper<K, V> extends Ace implements Treater<V> {

	private final Map<K, V> _result;

	public MapMapper(final Map<K, V> items) {
		_result = items;
		for (final Entry<K, V> entry : items.entrySet()) {
			_result.put(entry.getKey(), treat(entry.getValue()));
		}
	}

	public Map<K, V> map() {
		return _result;
	}

}
