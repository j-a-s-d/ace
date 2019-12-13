/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.Sandboxed;
import ace.arrays.GenericArrays;
import ace.constants.STRINGS;
import ace.text.Strings;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Utility class for working with jar files.
 */
public class Jars extends Ace {

	public static final String FILE_EXTENSION = ".jar";

	private static Jar _current;

	static {
		_current = getJarForClass(Jar.class);
	}

	/**
	 * Determines if the current java process has an associated (is originated from a) jar file.
	 * 
	 * @return <tt>true</tt> if is the case, <tt>false</tt> otherwise
	 */
	public static boolean hasCurrent() {
		return assigned(_current);
	}

	/**
	 * Gets the current java process associated jar file.
	 * 
	 * @return the current java process associated jar file
	 */
	public static Jar getCurrent() {
		return _current;
	}

	/**
	 * Sets the current java process associated jar file.
	 * 
	 * @param jar
	 */
	public static void setCurrent(final Jar jar) {
		_current = jar;
	}

	/**
	 * Converts the specified package name to its corresponding package path.
	 * 
	 * @param packageName
	 * @return the resulting string
	 */
	public static String packageNameToPackagePath(final String packageName) {
		return STRINGS.SLASH + packageName.replace(STRINGS.PERIOD, STRINGS.SLASH) + STRINGS.SLASH;
	}

	/**
	 * Converts the specified package path to its corresponding package name.
	 * 
	 * @param packagePath
	 * @return the resulting string
	 */
	public static String packagePathToPackageName(final String packagePath) {
		return Strings.stripBoth(packagePath.replace(STRINGS.SLASH, STRINGS.PERIOD), '.');
	}

	/**
	 * Converts the specified class name to its corresponding class path.
	 * 
	 * @param className
	 * @return the resulting string
	 */
	public static String classNameToClassPath(final String className) {
		return STRINGS.SLASH + className.replace(STRINGS.PERIOD, STRINGS.SLASH);
	}

	/**
	 * Converts the specified class path to its corresponding class name.
	 * 
	 * @param classPath
	 * @return the resulting string
	 */
	public static String classPathToClassName(final String classPath) {
		return classPath.replace(STRINGS.SLASH, STRINGS.PERIOD);
	}

	/**
	 * Gets a class loader for the specified jar file path.
	 * 
	 * @param jarFilename
	 * @param requester
	 * @return the class loader if the operation was successful, <tt>null</tt> otherwise
	 */
	public static ClassLoader getClassLoaderForJarFile(final String jarFilename, final Class<?> requester) {
		return getClassLoaderForJarFile(new File(jarFilename), requester);
	}

	/**
	 * Gets a class loader for the specified jar file.
	 * 
	 * @param jarFile
	 * @param requester
	 * @return the class loader if the operation was successful, <tt>null</tt> otherwise
	 */
	public static ClassLoader getClassLoaderForJarFile(final File jarFile, final Class<?> requester) {
		return new Sandboxed<ClassLoader>() {
			/*@Override*/ public ClassLoader run() throws Exception {
				return URLClassLoader.newInstance(new URL[] { jarFile.toURI().toURL() }, requester.getClassLoader());
			}
		}.go();
	}

	/**
	 * Gets a class from the specified jar file path.
	 * 
	 * @param jarFilename
	 * @param className
	 * @param requester
	 * @return the class if the operation was successful, <tt>null</tt> otherwise
	 */
	public static Class<?> getClassFromJarFile(final String jarFilename, final String className, final Class<?> requester) {
		return getClassFromJarFile(new File(jarFilename), className, requester);
	}

	/**
	 * Gets a class from the specified jar file.
	 * 
	 * @param jarFile
	 * @param className
	 * @param requester
	 * @return the class if the operation was successful, <tt>null</tt> otherwise
	 */
	public static Class<?> getClassFromJarFile(final File jarFile, final String className, final Class<?> requester) {
		return new Sandboxed<Class<?>>() {
			@Override public Class<?> run() throws Exception {
				return getClassLoaderForJarFile(jarFile, requester).loadClass(className);
			}
		}.go();
	}

	/**
	 * Gets the jar of the specified class if accessible.
	 * 
	 * @param clazz
	 * @return the jar of the specified class if the operation was successful, <tt>null</tt> otherwise
	 */
	public static Jar getJarForClass(final Class<?> clazz) {
		try {
			return new Jar(clazz.getProtectionDomain().getCodeSource().getLocation().getPath(), clazz);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	private static final Method _addURL = Reflection.getMethodAsAccessible(URLClassLoader.class, "addURL", GenericArrays.make(URL.class));

	/**
	 * Loads the specified jar url to the specified url class loader.
	 * 
	 * @param urlToJarFile
	 * @param classLoader
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static synchronized boolean load(final URL urlToJarFile, final URLClassLoader classLoader) {
		if (assigned(urlToJarFile, classLoader)) {
			try {
				for (final URL it : classLoader.getURLs()) {
					if (it.equals(urlToJarFile)) {
						return true;
					}
				}
				_addURL.invoke(classLoader, urlToJarFile);
				return true;
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return false;
	}

	/**
	 * Loads the specified jar url to the system class loader.
	 * 
	 * @param urlToJarFile
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean load(final URL urlToJarFile) {
		return load(urlToJarFile, (URLClassLoader) ClassLoader.getSystemClassLoader());
	}

	/**
	 * Loads the specified jar file to the specified url class loader.
	 * 
	 * @param jarFile
	 * @param classLoader
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean load(final File jarFile, final URLClassLoader classLoader) {
		try {
			return load(jarFile.toURI().toURL(), classLoader);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Loads the specified jar file to the system class loader.
	 * 
	 * @param jarFile
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean load(final File jarFile) {
		return load(jarFile, (URLClassLoader) ClassLoader.getSystemClassLoader());
	}

}
