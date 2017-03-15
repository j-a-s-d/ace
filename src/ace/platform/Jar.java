/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.LocalExceptionHandler;
import ace.binary.Streams;
import ace.constants.STRINGS;
import ace.files.BinaryFiles;
import ace.files.Directories;
import ace.files.FilenameUtils;
import ace.text.Strings;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Jar extends LocalExceptionHandler {

	private static final String META_INF = "META-INF";

	private final ArrayList<String> _metaInfo;
	private final ArrayList<String> _packages;
	private final ArrayList<String> _classes;
	private final HashMap<String, byte[]> _contents;
	private final File _file;
	private final ClassLoader _classLoader;
	private final Class _requester;

	public Jar(final String jarFilename) {
		this(jarFilename, Jar.class);
	}

	public Jar(final File jarFile) {
		this(jarFile, Jar.class);
	}

	public Jar(final String jarFilename, final Class requester) {
		this(new File(jarFilename), requester);
	}

	public Jar(final File jarFile, final Class requester) {
		_file = jarFile;
		_requester = requester;
		_classLoader = Jars.getClassLoaderForJarFile(_file.getAbsolutePath(), requester);
		_metaInfo = new ArrayList();
		_packages = new ArrayList();
		_classes = new ArrayList();
		_contents = new HashMap();
		reload();
	}

	public void reload() {
		_metaInfo.clear();
		_packages.clear();
		_contents.clear();
		_classes.clear();
		try {
			final JarFile jar = new JarFile(_file.getAbsolutePath());
			final Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				final JarEntry jarEntry = entries.nextElement();
				final String name = jarEntry.getName();
				if (name.startsWith(META_INF)) {
					_metaInfo.add(STRINGS.SLASH + name);
				} else if (name.endsWith(STRINGS.SLASH)) {
					_packages.add(Jars.packagePathToPackageName(name));
				} else {
					_contents.put(STRINGS.SLASH + name, Streams.toByteArray(jar.getInputStream(jarEntry)));
					if (name.endsWith(Classes.FILE_EXTENSION)) {
						_classes.add(Jars.classPathToClassName(name));
					}
				}
			}
			jar.close();
		} catch (final Exception e) {
			setLastException(e);
		}
	}

	public Class getRequester() {
		return _requester;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	public File getFile() {
		return _file;
	}

	public ArrayList<String> getMetaInfo() {
		return _metaInfo;
	}

	public HashMap<String, byte[]> getContents() {
		return _contents;
	}

	public ArrayList<String> getPackages() {
		return _packages;
	}

	public boolean hasPackage(final String packageName) {
		return _packages.indexOf(packageName) > -1;
	}

	public ArrayList<String> getPackageContents(final String packageName) {
		final ArrayList<String> result = new ArrayList();
		if (hasPackage(packageName)) {
			final String pkgPath = Jars.packageNameToPackagePath(packageName);
			for (final String s : _contents.keySet()) {
				if (s.startsWith(pkgPath)) {
					result.add(s);
				}
			}
		}
		return result;
	}

	public ArrayList<String> getPackageClasses(final String packageName) {
		final ArrayList<String> result = new ArrayList();
		if (hasPackage(packageName)) {
			for (final String s : _classes) {
				if (s.startsWith(packageName)) {
					result.add(s);
				}
			}
		}
		return result;
	}

	public String getResourceAsString(final String resourceName) {
		return Strings.fromInputStream(getResourceAsStream(resourceName));
	}

	public InputStream getResourceAsStream(final String resourceName) {
		return assigned(resourceName) ? Jar.class.getResourceAsStream(resourceName) : null;
	}

	public boolean extractResourceToFile(final String resourceName, final String fileName) {
		return extractResourceToFile(resourceName, new File(fileName));
	}

	public boolean extractResourceToFile(final String resourceName, final File file) {
		return BinaryFiles.write(file, getResourceAsStream(resourceName));
	}

	public boolean extractPackageResourcesToFolder(final String packageName, final String directory) {
		return extractPackageResourcesToFolder(packageName, new File(directory));
	}

	public boolean extractPackageResourcesToFolder(final String packageName, final File directory) {
		boolean result = Directories.ensure(directory) && hasPackage(packageName);
		if (result) {
			for (final String s : getPackageContents(packageName)) {
				result &= extractResourceToFile(s, directory.getAbsolutePath() + STRINGS.SLASH + FilenameUtils.extractFilename(s));
			}
		}
		return result;
	}

	public String getContentAsString(final String resourceName) {
		return Strings.fromByteArray(getContentAsByteArray(resourceName));
	}

	public byte[] getContentAsByteArray(final String resourceName) {
		return _contents.get(resourceName);
	}

	public boolean extractContentToFile(final String resourceName, final String fileName) {
		return extractContentToFile(resourceName, new File(fileName));
	}

	public boolean extractContentToFile(final String resourceName, final File file) {
		return BinaryFiles.write(file, getContentAsByteArray(resourceName));
	}

	public boolean extractPackageContentsToFolder(final String packageName, final String directory) {
		return extractPackageContentsToFolder(packageName, new File(directory));
	}

	public boolean extractPackageContentsToFolder(final String packageName, final File directory) {
		boolean result = Directories.ensure(directory) && hasPackage(packageName);
		if (result) {
			for (final String s : getPackageContents(packageName)) {
				result &= extractContentToFile(s, directory.getAbsolutePath() + STRINGS.SLASH + FilenameUtils.extractFilename(s));
			}
		}
		return result;
	}

	public Class<?> getClass(final String className, final Class<?> requester) {
		try {
			return Jars.getClassFromJarFile(_file.getAbsolutePath(), className, requester);
		} catch (final Exception e) {
			setLastException(e);
			return null;
		}
	}

	public Class<?> getClass(final String className) {
		try {
			return _classLoader.loadClass(className);
		} catch (final Exception e) {
			setLastException(e);
			return null;
		}
	}

}
