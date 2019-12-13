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

	/**
	 * Determines if the class with the specified name is loaded.
	 * 
	 * @param className
	 * @return <tt>true</tt> if the class with the specified name is loaded, <tt>false</tt> otherwise
	 */
	public static boolean isClassLoaded(final String className) {
		try {
			Class.forName(className);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Creates an instance of the specified class type by invoking the default constructor.
	 * 
	 * @param <T>
	 * @param type
	 * @return an instance of the specified class type if the operation was successful, <tt>null</tt> otherwise
	 */
	public static <T> T instantiate(final Class<?> type) {
		try {
			return (T) type.newInstance();
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Creates an instance of the specified class type by invoking the constructor that matches the specified argument types with the specified argument values.
	 * 
	 * @param <T>
	 * @param type
	 * @param argumentTypes
	 * @param arguments
	 * @return an instance of the specified class type if the operation was successful, <tt>null</tt> otherwise
	 */
	public static <T> T instantiate(final Class<?> type, final Class[] argumentTypes, final Object[] arguments) {
		try {
			return (T) type.getDeclaredConstructor(argumentTypes).newInstance(arguments);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Creates an instance of the class that matches the specified name in the local class loader by invoking the default constructor.
	 * 
	 * @param <T>
	 * @param className
	 * @return an instance of the specified class type if the operation was successful, <tt>null</tt> otherwise
	 */
	public static <T> T instantiate(final String className) {
		try {
			return (T) Classes.class.getClassLoader().loadClass(className).newInstance();
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Creates an instance of the class that matches the specified name in the local class loader by invoking the constructor that matches the specified argument types with the specified argument values.
	 * 
	 * @param <T>
	 * @param className
	 * @param argumentTypes
	 * @param arguments
	 * @return an instance of the specified class type if the operation was successful, <tt>null</tt> otherwise
	 */
	public static <T> T instantiate(final String className, final Class[] argumentTypes, final Object[] arguments) {
		try {
			return (T) Classes.class.getClassLoader().loadClass(className).getDeclaredConstructor(argumentTypes).newInstance(arguments);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Lists the class hierarchy for the specified class type.
	 * 
	 * @param clazz
	 * @return the resulting list of classes
	 */
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

	/**
	 * Gets the short name for the specified class canonical name.
	 * 
	 * @param canonicalName
	 * @return the resulting short name
	 */
	public static String getShortName(final String canonicalName) {
		if (Strings.hasText(canonicalName)) {
			final int x = canonicalName.lastIndexOf(CHARS.PERIOD);
			return x == -1 ? canonicalName : Strings.dropLeft(canonicalName.substring(x), 1);
		}
		return null;
	}

	/**
	 * Loads the class by the specified canonical name from the specified class loader.
	 * 
	 * @param classesLoader
	 * @param canonicalName
	 * @return the class if the operation was successful, <tt>null</tt> otherwise
	 */
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

	/**
	 * Creates an url class loader for the specified directory path.
	 * 
	 * @param classesDirectoryPath
	 * @return an url class loader instance
	 */
	public static URLClassLoader makeURLClassLoaderFromDirectory(final String classesDirectoryPath) {
		return makeURLClassLoaderFromDirectory(new File(classesDirectoryPath));
	}

	/**
	 * Creates an url class loader for the specified directory.
	 * 
	 * @param classesDirectory
	 * @return an url class loader instance
	 */
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
