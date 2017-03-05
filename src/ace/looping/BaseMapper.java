/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.looping;

import ace.Ace;
import ace.interfaces.Treater;

/**
 * Useful base mapper class.
 * @param <R> Result map type.
 * @param <T> Value type to be mapped.
 */
public abstract class BaseMapper<R, T> extends Ace implements Treater<T> {

	protected final R _result;

	public BaseMapper(final R value) {
		_result = value;
	}

	public R map() {
		return _result;
	}

}
