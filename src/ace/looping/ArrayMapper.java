/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Treater;

public abstract class ArrayMapper<T> extends Ace implements Treater<T> {

	private final T[] _result;

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ArrayMapper(final T... items) {
		_result = items;
		for (int x = items.length - 1; x > -1; x--) {
			_result[x] = treat(items[x]);
		}
	}

	public T[] map() {
		return _result;
	}

}
