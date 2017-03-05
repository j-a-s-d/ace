/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Utility class for working with reflection.
 */
public class Reflection extends Ace {
	
	private static AccessibleObject setObjectAsAccessible(final AccessibleObject object) {
		if (assigned(object)) {
			object.setAccessible(true);
			return object;
		}
		return null;
	}
	
	public static final Constructor getConstructorAsAccessible(final Class<?> clazz, final Class<?>... parameterTypes) {
		try {
			return (Constructor) setObjectAsAccessible(clazz.getDeclaredConstructor(parameterTypes));
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}
	
	public static final Method getMethodAsAccessible(final Class<?> clazz, final String name, final Class<?>... parameterTypes) {
		try {
			return (Method) setObjectAsAccessible(clazz.getDeclaredMethod(name, parameterTypes));
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}
	
	public static final Field getFieldAsAccessible(final Class<?> clazz, final String fieldName) {
		try {
			return (Field) setObjectAsAccessible(clazz.getDeclaredField(fieldName));
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}
	
}
