/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Treater;
import java.util.List;

public abstract class ListMapper<T> extends Ace implements Treater<T> {

	private final List<T> _result;

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ListMapper(final List<T> items) {
		_result = items;
		for (int x = items.size() - 1; x > -1; x--) {
			_result.set(x, treat(items.get(x)));
		}
	}

	public List<T> map() {
		return _result;
	}

}
