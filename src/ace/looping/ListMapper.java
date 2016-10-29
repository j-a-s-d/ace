/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import java.util.List;

public abstract class ListMapper<T> extends BaseMapper<List<T>, T> {

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ListMapper(final List<T> items) {
		super(items);
		for (int x = items.size() - 1; x > -1; x--) {
			_result.set(x, treat(items.get(x)));
		}
	}

}
