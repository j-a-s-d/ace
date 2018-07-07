/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.LocalExceptionHandler;
import ace.interfaces.Initializable;
import java.lang.reflect.Method;

public class ReflectionPropertyHelper<T> extends LocalExceptionHandler implements Initializable {

	private final Class<?> _clazz;
	private final Class<?> _type;
	private final String _getter;
	private final String _setter;
	private boolean _initialized;
	private Method _get;
	private Method _set;

	public ReflectionPropertyHelper(final Class<?> clazz, final String getter, final String setter, final Class<T> type) {
		_clazz = clazz;
		_getter = getter;
		_setter = setter;
		_type = type;
		_initialized = false;
	}

	/*@Override*/ public final boolean initialize() {
		return _initialized = assigned(
			_get = Reflection.getMethodAsAccessible(_clazz, _getter),
			_set = Reflection.getMethodAsAccessible(_clazz, _setter, _type)
		);
	}

	public final T get(final Object instance) {
		if (_initialized && _clazz.isInstance(instance)) {
			try {
				return (T) _get.invoke(instance);
			} catch (final Exception e) {
				setLastException(e);
			}
		}
		return null;
	}

	public final void set(final Object instance, final T object) {
		if (_initialized && _clazz.isInstance(instance)) {
			try {
				_set.invoke(instance, object);
			} catch (final Exception e) {
				setLastException(e);
			}
		}
	}

}
