/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.constants.CHARS;
import ace.text.Strings;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for working with classes.
 */
public class Classes extends Ace {

	public static final String FILE_EXTENSION = ".class";

	public static boolean isClassLoaded(final String className) {
		try {
			Class.forName(className);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	public static <T> T instantiate(final Class<?> type) {
		try {
			return (T) type.newInstance();
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static <T> T instantiate(final Class<?> type, final Class[] argumentTypes, final Object[] arguments) {
		try {
			return (T) type.getDeclaredConstructor(argumentTypes).newInstance(arguments);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static <T> T instantiate(final String className) {
		try {
			return (T) Classes.class.getClassLoader().loadClass(className).newInstance();
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static <T> T instantiate(final String className, final Class[] argumentTypes, final Object[] arguments) {
		try {
			return (T) Classes.class.getClassLoader().loadClass(className).getDeclaredConstructor(argumentTypes).newInstance(arguments);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static List<Class<?>> listHierarchy(final Class<?> clazz) {
		if (assigned(clazz)) {
			final List<Class<?>> result = new ArrayList();
			Class<?> c = clazz;
			do {
				result.add(c);
			} while (assigned(c = c.getSuperclass()));
			return result;
		}
		return null;
	}

	public static String getShortName(final String canonicalName) {
		if (Strings.hasText(canonicalName)) {
			final int x = canonicalName.lastIndexOf(CHARS.PERIOD);
			return x == -1 ? canonicalName : Strings.dropLeft(canonicalName.substring(x), 1);
		}
		return null;
	}

	public static Class<?> loadFromFile(final URLClassLoader classesLoader, final String canonicalName) {
		if (assigned(classesLoader)) {
			try {
				return classesLoader.loadClass(canonicalName);
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return null;
	}

	public static URLClassLoader makeURLClassLoaderFromDirectory(final String classesDirectoryPath) {
		return makeURLClassLoaderFromDirectory(new File(classesDirectoryPath));
	}

	public static URLClassLoader makeURLClassLoaderFromDirectory(final File classesDirectory) {
		if (assigned(classesDirectory) && classesDirectory.exists() && classesDirectory.isDirectory()) {
			try {
				return new URLClassLoader(new URL[] { classesDirectory.toURL() });
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return null;
	}

}
