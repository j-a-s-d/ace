/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.arrays.GenericArrays;
import ace.binary.Streams;
import ace.constants.STRINGS;
import ace.containers.Lists;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Utility class for working with files.
 */
public class Files extends Ace {

	/**
	 * Determines if the specified file has content.
	 * 
	 * @param file
	 * @return <tt>true</tt> if the specified file has content, <tt>false</tt> otherwise
	 */
	public static boolean hasContent(final File file) {
		return exists(file) && file.length() > 0;
	}

	/**
	 * Makes a file with the specified name in the specified directory.
	 * 
	 * @param directory
	 * @param fileName
	 * @return the resulting file
	 */
	public static File make(final File directory, final String fileName) {
		return make(directory.getAbsolutePath(), fileName);
	}

	/**
	 * Makes a file with the specified name in the specified directory path.
	 * 
	 * @param directoryPath
	 * @param fileName
	 * @return the resulting file
	 */
	public static File make(final String directoryPath, final String fileName) {
		try {
			final File file = new File(FilenameUtils.ensureLastDirectorySeparator(directoryPath) + fileName);
			if (file.createNewFile()) {
				return file;
			}
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		return null;
	}

	/**
	 * Determines if the specified file exists.
	 * 
	 * @param file
	 * @return <tt>true</tt> if the specified file exists, <tt>false</tt> otherwise
	 */
	public static boolean exists(final File file) {
		return assigned(file) && file.isFile() && file.exists();
	}

	/**
	 * Determines if the specified files exist.
	 * 
	 * @param files
	 * @return <tt>true</tt> if the specified files exist, <tt>false</tt> otherwise
	 */
	public static boolean exist(final List<File> files) {
		boolean result = Lists.hasContent(files);
		for (final File file : files) {
			result &= assigned(file) && file.exists();
		}
		return result;
	}

	/**
	 * Determines if the specified files exist.
	 * 
	 * @param files
	 * @return <tt>true</tt> if the specified files exist, <tt>false</tt> otherwise
	 */
	public static boolean exist(final File... files) {
		boolean result = GenericArrays.hasContent(files);
		for (final File file : files) {
			result &= assigned(file) && file.exists();
		}
		return result;
	}

	/**
	 * Ensures the existence of the specified file (if it does not exist, it is created).
	 * 
	 * @param file
	 * @return <tt>true</tt> if the file (created or not in the process) exists, <tt>false</tt> otherwise
	 */
	public static boolean ensure(final File file) {
		if (assigned(file)) {
			if (file.exists()) {
				return true;
			}
			final String filename = file.getName();
			final String parent = file.getParent();
			if (!assigned(parent)) {
				return make(new File(STRINGS.EMPTY), filename).exists();
			}
			if (Directories.ensure(parent)) {
				return make(parent, filename).exists();
			}
		}
		return false;
	}

	/**
	 * Ensures the existence of the file with the specified name (if it does not exist, it is created).
	 * 
	 * @param filePath
	 * @return <tt>true</tt> if the file (created or not in the process) exists, <tt>false</tt> otherwise
	 */
	public static boolean ensure(final String filePath) {
		return ensure(new File(filePath));
	}

	/**
	 * Compares the two specified files.
	 * 
	 * @param file1
	 * @param file2
	 * @return <tt>true</tt> if the content is equal, <tt>false</tt> otherwise
	 */
	public static boolean hasSameBytes(final File file1, final File file2) {
		InputStream input1 = null;
		InputStream input2 = null;
		try {
			if (!assigned(file1) || !assigned(file2)) {
				return false;
			}
			if (file1.exists() != file2.exists()) {
				return false;
			}
			if (file1.isDirectory() || file2.isDirectory()) {
				return false;
			}
			if (file1.length() != file2.length()) {
				return false;
			}
			if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
				return true;
			}
			input1 = new FileInputStream(file1);
			input2 = new FileInputStream(file2);
			return Streams.hasSameBytes(new BufferedInputStream(input1), new BufferedInputStream(input2));
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		Streams.close(input1);
		Streams.close(input2);
		return false;
	}

	/**
	 * Copies the content of the specified source file to the specified destination file.
	 * 
	 * @param source
	 * @param destination
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean copy(final File source, final File destination) {
		return copy(source, destination, false);
	}

	/**
	 * Copies the content of the specified source file to the specified destination file preserving the modification date on it.
	 * 
	 * @param source
	 * @param destination
	 * @param preserveModificationDate
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean copy(final File source, final File destination, final boolean preserveModificationDate) {
		try {
			if (destination.exists() && destination.isDirectory()) {
				return false;
			}
			final FileInputStream input = new FileInputStream(source);
			try {
				final FileOutputStream output = new FileOutputStream(destination);
				try {
					Streams.copy(input, output);
				} finally {
					Streams.close(output);
				}
			} finally {
				Streams.close(input);
			}
			if (source.length() != destination.length()) {
				destination.delete();
				return false;
			}
			if (preserveModificationDate) {
				destination.setLastModified(source.lastModified());
			}
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		return false;
	}

	/**
	 * Gets the corresponding url to the specified file in a silent mode (catching exceptions).
	 * 
	 * @param file
	 * @return the corresponding url if the operation was successful, <tt>null</tt> otherwise
	 */
	public static URL getURL(final File file) {
		if (assigned(file)) {
			try {
				return file.toURL();
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return null;
	}

}
