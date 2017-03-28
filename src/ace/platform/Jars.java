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

	public static boolean hasCurrent() {
		return assigned(_current);
	}

	public static Jar getCurrent() {
		return _current;
	}

	public static void setCurrent(final Jar jar) {
		_current = jar;
	}

	public static String packageNameToPackagePath(final String packageName) {
		return STRINGS.SLASH + packageName.replace(STRINGS.PERIOD, STRINGS.SLASH) + STRINGS.SLASH;
	}

	public static String packagePathToPackageName(final String packagePath) {
		return Strings.stripBoth(packagePath.replace(STRINGS.SLASH, STRINGS.PERIOD), '.');
	}

	public static String classNameToClassPath(final String className) {
		return STRINGS.SLASH + className.replace(STRINGS.PERIOD, STRINGS.SLASH);
	}

	public static String classPathToClassName(final String classPath) {
		return classPath.replace(STRINGS.SLASH, STRINGS.PERIOD);
	}

	public static ClassLoader getClassLoaderForJarFile(final String jarFilename, final Class<?> requester) {
		return getClassLoaderForJarFile(new File(jarFilename), requester);
	}

	public static ClassLoader getClassLoaderForJarFile(final File jarFile, final Class<?> requester) {
		return new Sandboxed<ClassLoader>() {
			/*@Override*/ public ClassLoader run() throws Exception {
				return URLClassLoader.newInstance(new URL[] { jarFile.toURI().toURL() }, requester.getClassLoader());
			}
		}.go();
	}

	public static Class<?> getClassFromJarFile(final String jarFilename, final String className, final Class<?> requester) {
		return getClassFromJarFile(new File(jarFilename), className, requester);
	}

	public static Class<?> getClassFromJarFile(final File jarFile, final String className, final Class<?> requester) {
		return new Sandboxed<Class<?>>() {
			@Override public Class<?> run() throws Exception {
				return getClassLoaderForJarFile(jarFile, requester).loadClass(className);
			}
		}.go();
	}

	public static Jar getJarForClass(final Class<?> clazz) {
		try {
			return new Jar(clazz.getProtectionDomain().getCodeSource().getLocation().getPath(), clazz);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	private static final Method _addURL = Reflection.getMethodAsAccessible(URLClassLoader.class, "addURL", GenericArrays.make(URL.class));

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

	public static boolean load(final URL urlToJarFile) {
		return load(urlToJarFile, (URLClassLoader) ClassLoader.getSystemClassLoader());
	}

	public static boolean load(final File jarFile, final URLClassLoader classLoader) {
		try {
			return load(jarFile.toURI().toURL(), classLoader);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	public static boolean load(final File jar) {
		return load(jar, (URLClassLoader) ClassLoader.getSystemClassLoader());
	}

}
