/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import ace.containers.Lists;
import java.io.File;
import java.util.List;

/**
 * Utility class for working with directories.
 */
public class Directories extends Ace {

	public static final String CURRENT_PATH = STRINGS.PERIOD + File.separator;
	public static final File CURRENT = new File(CURRENT_PATH);

	/**
	 * Determines if the specified directory path exists.
	 * 
	 * @param path
	 * @return <tt>true</tt> if the specified directory path exists, <tt>false</tt> otherwise
	 */
	public static boolean exists(final String path) {
		return exists(new File(path));
	}

	/**
	 * Determines if the specified directory exists.
	 * 
	 * @param directory
	 * @return <tt>true</tt> if the specified directory exists, <tt>false</tt> otherwise
	 */
	public static boolean exists(final File directory) {
		return assigned(directory) && directory.isDirectory() && directory.exists();
	}

	/**
	 * Abstract directory walker class.
	 */
	public abstract static class DirectoryWalkerHandler {

		public abstract void processFile(File file);

	}

	/**
	 * Walks the specified directory in the specified recursive mode using the specified directory walking handler.
	 * 
	 * @param directory
	 * @param recursive
	 * @param handler 
	 */
	public static final void walkDirectory(final File directory, final boolean recursive, final DirectoryWalkerHandler handler) {
		if (directory != null && directory.exists()) {
			final File[] files = directory.listFiles();
			if (files != null) {
				for (final File file : files) {
					handler.processFile(file);
					if (recursive && file.isDirectory()) {
						walkDirectory(file, recursive, handler);
					}
				}
			}
		}
	}

	/**
	 * Lists the files of the directory of the specified path.
	 * 
	 * @param directoryPath
	 * @return the resulting list of files
	 */
	public static final List<File> listFiles(final String directoryPath) {
		return listFiles(directoryPath, false, FilenameValidator.makeAllFilesValidator());
	}

	/**
	 * Lists the files of the directory of the specified path recursing as specified.
	 * 
	 * @param directoryPath
	 * @param recursive
	 * @return the resulting list of files
	 */
	public static final List<File> listFiles(final String directoryPath, final boolean recursive) {
		return listFiles(directoryPath, recursive, FilenameValidator.makeAllFilesValidator());
	}

	/**
	 * Lists the files of the directory of the specified path recursing as specified and matching the specified validator.
	 * 
	 * @param directoryPath
	 * @param recursive
	 * @param validator
	 * @return the resulting list of files
	 */
	public static final List<File> listFiles(final String directoryPath, final boolean recursive, final FilenameValidator validator) {
		return listFiles(new File(directoryPath), recursive, validator);
	}

	/**
	 * Lists the files of the specified directory.
	 * 
	 * @param directory
	 * @return the resulting list of files
	 */
	public static final List<File> listFiles(final File directory) {
		return listFiles(directory, false, FilenameValidator.makeAllFilesValidator());
	}

	/**
	 * Lists the files of the specified directory recursing as specified.
	 * 
	 * @param directory
	 * @param recursive
	 * @return the resulting list of files
	 */
	public static final List<File> listFiles(final File directory, final boolean recursive) {
		return listFiles(directory, recursive, FilenameValidator.makeAllFilesValidator());
	}

	/**
	 * Lists the files of the specified directory recursing as specified and matching the specified validator.
	 * 
	 * @param directory
	 * @param recursive
	 * @param validator
	 * @return the resulting list of files
	 */
	public static final List<File> listFiles(final File directory, final boolean recursive, final FilenameValidator validator) {
		final List<File> list = Lists.make();
		walkDirectory(directory, recursive, new DirectoryWalkerHandler() {
			@Override public void processFile(final File file) {
				if (file.isFile() && validator.validate(file.getName())) {
					list.add(file);
				}
			}
		});
		return list;
	}

	/**
	 * Lists the directories of the directory in the specified path recursing as specified.
	 * 
	 * @param directoryPath
	 * @param recursive
	 * @return the resulting list of files
	 */
	public static final List<File> listDirectories(final String directoryPath, final boolean recursive) {
		return listDirectories(new File(directoryPath), recursive);
	}

	/**
	 * Lists the directories of the specified directory recursing as specified.
	 * 
	 * @param directory
	 * @param recursive
	 * @return the resulting list of files
	 */
	public static final List<File> listDirectories(final File directory, final boolean recursive) {
		final List<File> list = Lists.make();
		walkDirectory(directory, recursive, new DirectoryWalkerHandler() {
			@Override public void processFile(final File file) {
				if (file.isDirectory()) {
					list.add(file);
				}
			}
		});
		return list;
	}

	/**
	 * Ensures the existence of the directory in the specified path (if it does not exist, it is created).
	 * 
	 * @param path
	 * @return <tt>true</tt> if the directory (created or not in the process) exists, <tt>false</tt> otherwise
	 */
	public static final boolean ensure(final String path) {
		return ensure(new File(path));
	}

	/**
	 * Ensures the existence of the specified directory (if it does not exist, it is created).
	 * 
	 * @param directory
	 * @return <tt>true</tt> if the directory (created or not in the process) exists, <tt>false</tt> otherwise
	 */
	public static final boolean ensure(final File directory) {
		boolean result = directory.exists();
		if (!result) {
			if (directory.mkdirs()) {
				result = directory.exists();
			}
		} else if (!directory.isDirectory()) {
			result = false;
		}
		return result;
	}

	/**
	 * Deletes the directory in the specified path.
	 * 
	 * @param path
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean delete(final String path) {
		return delete(new File(path));
	}

	/**
	 * Deletes the specified directory.
	 * 
	 * @param folder
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean delete(final File folder) {
		boolean result = true;
		final File[] files = folder.listFiles();
		if (files != null) {
			for (final File f : files) {
				if (f.isDirectory()) {
					result &= delete(f);
				} else {
					f.delete();
				}
			}
		}
		result &= folder.delete();
		return result;
	}

	/**
	 * Summarizes the sizes of the files of the directory in the specified path.
	 * 
	 * @param directoryPath
	 * @return the summarization of sizes of the files of the directory
	 */
	public static long calculateSize(final String directoryPath) {
		return calculateSize(new File(directoryPath));
	}

	/**
	 * Summarizes the sizes of the files of the specified directory.
	 * 
	 * @param directory
	 * @return the summarization of sizes of the files of the directory
	 */
	public static long calculateSize(final File directory) {
		long result = 0;
		for (final File file : listFiles(directory)) {
			result += file.isFile() ? file.length() : calculateSize(file);
		}
		return result;
	}

	/**
	 * Copies the contents of the directory in the specified source path to the destination path.
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean copy(final String sourcePath, final String destinationPath) {
		return copy(new File(sourcePath), new File(destinationPath));
	}

	/**
	 * Copies the contents of the specified source directory to the specified destination directory.
	 * 
	 * @param source
	 * @param destination
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static boolean copy(final File source, final File destination) {
		if (source.isDirectory()) {
			if (destination.exists() || ensure(destination)) {
				boolean result = true;
				for (final String file : source.list()) {
					result &= copy(new File(source, file), new File(destination, file));
				}
				return result;
			}
			return false;
		} else {
			return Files.copy(source, destination);
		}
	}

}
