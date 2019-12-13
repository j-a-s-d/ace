/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.LocalExceptionHandler;
import ace.interfaces.Initializable;
import java.lang.reflect.Method;

/**
 * Useful property helper class allowing access to a getter/setter pair in a silent way (handling exceptions).
 */
public class ReflectionPropertyHelper<T> extends LocalExceptionHandler implements Initializable {

	private final Class<?> _clazz;
	private final Class<?> _type;
	private final String _getter;
	private final String _setter;
	private boolean _initialized;
	private Method _get;
	private Method _set;

	/**
	 * Default constructor accepting the class of objects to help, the getter method name, the setter method name and the class type of them.
	 * 
	 * @param clazz
	 * @param getter
	 * @param setter
	 * @param type 
	 */
	public ReflectionPropertyHelper(final Class<?> clazz, final String getter, final String setter, final Class<T> type) {
		_clazz = clazz;
		_getter = getter;
		_setter = setter;
		_type = type;
		_initialized = false;
	}

	/**
	 * Initializes this property helper.
	 * 
	 * @return <tt>true</tt> if the initialization was successful, <tt>false</tt> otherwise
	 */
	/*@Override*/ public final boolean initialize() {
		return _initialized = assigned(
			_get = Reflection.getMethodAsAccessible(_clazz, _getter),
			_set = Reflection.getMethodAsAccessible(_clazz, _setter, _type)
		);
	}

	/**
	 * Calls the helped class instance getter method in a silent mode (handling exceptions).
	 * 
	 * @param instance
	 * @return the getter returned value if the call was successful, <tt>null</tt> otherwise
	 */
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

	/**
	 * Calls the helped class instance setter method in a silent mode (handling exceptions).
	 * 
	 * @param instance
	 * @param object 
	 */
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
