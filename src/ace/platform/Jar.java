/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.LocalExceptionHandler;
import ace.binary.Streams;
import ace.constants.STRINGS;
import ace.containers.Lists;
import ace.containers.Maps;
import ace.files.BinaryFiles;
import ace.files.Directories;
import ace.files.FilenameUtils;
import ace.text.Strings;
import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Useful class for working with a jar file.
 */
public class Jar extends LocalExceptionHandler {

	private static final String META_INF = "META-INF";

	private final List<String> _metaInfo;
	private final List<String> _packages;
	private final List<String> _classes;
	private final Map<String, byte[]> _contents;
	private final File _file;
	private final ClassLoader _classLoader;
	private final Class _requester;

	/**
	 * Constructor accepting the jar filename.
	 * 
	 * @param jarFilename 
	 */
	public Jar(final String jarFilename) {
		this(jarFilename, Jar.class);
	}

	/**
	 * Constructor accepting the jar file.
	 * 
	 * @param jarFile 
	 */
	public Jar(final File jarFile) {
		this(jarFile, Jar.class);
	}

	/**
	 * Constructor accepting the jar filename and a class requester.
	 * 
	 * @param jarFilename 
	 * @param requester 
	 */
	public Jar(final String jarFilename, final Class requester) {
		this(new File(jarFilename), requester);
	}

	/**
	 * Constructor accepting the jar file and a class requester.
	 * 
	 * @param jarFile 
	 * @param requester 
	 */
	public Jar(final File jarFile, final Class requester) {
		_file = jarFile;
		_requester = requester;
		_classLoader = Jars.getClassLoaderForJarFile(_file.getAbsolutePath(), requester);
		_metaInfo = Lists.make();
		_packages = Lists.make();
		_classes = Lists.make();
		_contents = Maps.make();
		reload();
	}

	/**
	 * Reloads this jar object instance.
	 */
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

	/**
	 * Gets the class requester.
	 * 
	 * @return the class requester
	 */
	public Class getRequester() {
		return _requester;
	}

	/**
	 * Gets the class loader.
	 * 
	 * @return the class loader
	 */
	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	/**
	 * Gets the jar file.
	 * 
	 * @return the jar file
	 */
	public File getFile() {
		return _file;
	}

	/**
	 * Gets the jar meta info as a list of strings.
	 * 
	 * @return the jar meta info as a list of strings
	 */
	public List<String> getMetaInfo() {
		return _metaInfo;
	}

	/**
	 * Gets the jar contents as a map of strings.
	 * 
	 * @return the jar contents as a map of strings
	 */
	public Map<String, byte[]> getContents() {
		return _contents;
	}

	/**
	 * Gets the jar packages as a list of strings.
	 * 
	 * @return the jar packages as a list of strings
	 */
	public List<String> getPackages() {
		return _packages;
	}

	/**
	 * Determines if the current jar has the package with the specified name.
	 * 
	 * @param packageName
	 * @return <tt>true</tt> if the current jar has the package with the specified name, <tt>false</tt> otherwise
	 */
	public boolean hasPackage(final String packageName) {
		return _packages.indexOf(packageName) > -1;
	}

	/**
	 * Gets the contents of the jar package with the specified name as a list of strings.
	 * 
	 * @param packageName
	 * @return the contents of the jar package with the specified name as a list of strings
	 */
	public List<String> getPackageContents(final String packageName) {
		final List<String> result = Lists.make();
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

	/**
	 * Gets the classes of the jar package with the specified name as a list of strings.
	 * 
	 * @param packageName
	 * @return the classes of the jar package with the specified name as a list of strings
	 */
	public List<String> getPackageClasses(final String packageName) {
		final List<String> result = Lists.make();
		if (hasPackage(packageName)) {
			for (final String s : _classes) {
				if (s.startsWith(packageName)) {
					result.add(s);
				}
			}
		}
		return result;
	}

	/**
	 * Gets as string the jar resource with the specified name.
	 * 
	 * @param resourceName
	 * @return the jar resource as string if the operation is successful, <tt>null</tt> otherwise
	 */
	public String getResourceAsString(final String resourceName) {
		return Strings.fromInputStream(getResourceAsStream(resourceName));
	}

	/**
	 * Gets as an input stream the jar resource with the specified name.
	 * 
	 * @param resourceName
	 * @return the jar resource as an input stream if the operation is successful, <tt>null</tt> otherwise
	 */
	public InputStream getResourceAsStream(final String resourceName) {
		return assigned(resourceName) ? Jar.class.getResourceAsStream(resourceName) : null;
	}

	/**
	 * Extracts the jar resource with the specified name to the file with the specified name.
	 * 
	 * @param resourceName
	 * @param fileName
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractResourceToFile(final String resourceName, final String fileName) {
		return extractResourceToFile(resourceName, new File(fileName));
	}

	/**
	 * Extracts the jar resource with the specified name to the specified file.
	 * 
	 * @param resourceName
	 * @param file
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractResourceToFile(final String resourceName, final File file) {
		return BinaryFiles.write(file, getResourceAsStream(resourceName));
	}

	/**
	 * Extracts the resources of the jar package with the specified name to the specified directory path.
	 * 
	 * @param packageName
	 * @param directoryPath
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractPackageResourcesToFolder(final String packageName, final String directoryPath) {
		return extractPackageResourcesToFolder(packageName, new File(directoryPath));
	}

	/**
	 * Extracts the resources of the jar package with the specified name to the specified directory.
	 * 
	 * @param packageName
	 * @param directory
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractPackageResourcesToFolder(final String packageName, final File directory) {
		boolean result = Directories.ensure(directory) && hasPackage(packageName);
		if (result) {
			for (final String s : getPackageContents(packageName)) {
				result &= extractResourceToFile(s, directory.getAbsolutePath() + STRINGS.SLASH + FilenameUtils.extractFilename(s));
			}
		}
		return result;
	}

	/**
	 * Gets as string the jar contents item with the specified name.
	 * 
	 * @param contentName
	 * @return the jar content item as string if the operation is successful, <tt>null</tt> otherwise
	 */
	public String getContentAsString(final String contentName) {
		return Strings.fromByteArray(getContentAsByteArray(contentName));
	}

	/**
	 * Gets as byte array the jar contents item with the specified name.
	 * 
	 * @param contentName
	 * @return the jar content item as byte array if the operation is successful, <tt>null</tt> otherwise
	 */
	public byte[] getContentAsByteArray(final String contentName) {
		return _contents.get(contentName);
	}

	/**
	 * Extracts the jar content item with the specified name to the file with the specified name.
	 * 
	 * @param contentName
	 * @param fileName
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractContentToFile(final String contentName, final String fileName) {
		return extractContentToFile(contentName, new File(fileName));
	}

	/**
	 * Extracts the jar content item with the specified name to the specified file.
	 * 
	 * @param contentName
	 * @param file
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractContentToFile(final String contentName, final File file) {
		return BinaryFiles.write(file, getContentAsByteArray(contentName));
	}

	/**
	 * Extracts the content items of the jar package with the specified name to the specified directory path.
	 * 
	 * @param packageName
	 * @param directoryPath
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractPackageContentsToFolder(final String packageName, final String directoryPath) {
		return extractPackageContentsToFolder(packageName, new File(directoryPath));
	}

	/**
	 * Extracts the content items of the jar package with the specified name to the specified directory.
	 * 
	 * @param packageName
	 * @param directory
	 * @return <tt>true</tt> if the operation is successful, <tt>null</tt> otherwise
	 */
	public boolean extractPackageContentsToFolder(final String packageName, final File directory) {
		boolean result = Directories.ensure(directory) && hasPackage(packageName);
		if (result) {
			for (final String s : getPackageContents(packageName)) {
				result &= extractContentToFile(s, directory.getAbsolutePath() + STRINGS.SLASH + FilenameUtils.extractFilename(s));
			}
		}
		return result;
	}

	/**
	 * Gets the class with the specified name from the current jar using the specified requester.
	 * 
	 * @param className
	 * @param requester
	 * @return the class if the operation was successful, <tt>null</tt> otherwise
	 */
	public Class<?> getClass(final String className, final Class<?> requester) {
		try {
			return Jars.getClassFromJarFile(_file.getAbsolutePath(), className, requester);
		} catch (final Exception e) {
			setLastException(e);
			return null;
		}
	}

	/**
	 * Gets the class with the specified name from the current jar.
	 * 
	 * @param className
	 * @return the class if the operation was successful, <tt>null</tt> otherwise
	 */
	public Class<?> getClass(final String className) {
		try {
			return _classLoader.loadClass(className);
		} catch (final Exception e) {
			setLastException(e);
			return null;
		}
	}

}
