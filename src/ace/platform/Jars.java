/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.Sandboxed;
import ace.arrays.GenericArrays;
import ace.constants.STRINGS;
import ace.text.Strings;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

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

	public static synchronized boolean load(final File jarFile, final URLClassLoader classLoader) {
		if (assigned(jarFile, classLoader)) {
			try {
				final URL url = jarFile.toURI().toURL();
				for (final URL it : classLoader.getURLs()) {
					if (it.equals(url)) {
						return true;
					}
				}
				final Object[] oo = GenericArrays.make(url);
				Reflection.getMethodAsAccessible(URLClassLoader.class, "addURL", GenericArrays.make(URL.class)).invoke(classLoader, oo);
				return true;
			} catch (final Exception e) {
				ace.Ace.GEH.setLastException(e);
			}
		}
		return false;
	}

	public static boolean load(final java.io.File jar) {
		return load(jar, (java.net.URLClassLoader) ClassLoader.getSystemClassLoader());
	}

}
