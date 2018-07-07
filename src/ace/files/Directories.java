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

	public static boolean exists(final String path) {
		return exists(new File(path));
	}

	public static boolean exists(final File directory) {
		return assigned(directory) && directory.isDirectory() && directory.exists();
	}

	public abstract static class DirectoryWalkerHandler {

		public abstract void processFile(File file);

	}

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

	public static final List<File> listFiles(final String directoryPath) {
		return listFiles(directoryPath, false, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFiles(final String directoryPath, final boolean recursive) {
		return listFiles(directoryPath, recursive, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFiles(final String directoryPath, final boolean recursive, final FilenameValidator validator) {
		return listFiles(new File(directoryPath), recursive, validator);
	}

	public static final List<File> listFiles(final File directory) {
		return listFiles(directory, false, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFiles(final File directory, final boolean recursive) {
		return listFiles(directory, recursive, FilenameValidator.makeAllFilesValidator());
	}

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

	public static final List<File> listDirectories(final String directoryPath, final boolean recursive) {
		return listDirectories(new File(directoryPath), recursive);
	}

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

	public static final boolean ensure(final String path) {
		return ensure(new File(path));
	}

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

	public static final boolean delete(final String path) {
		return delete(new File(path));
	}

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

	public static long calculateSize(final String directoryPath) {
		return calculateSize(new File(directoryPath));
	}

	public static long calculateSize(final File directory) {
		long result = 0;
		for (final File file : listFiles(directory)) {
			result += file.isFile() ? file.length() : calculateSize(file);
		}
		return result;
	}

	public static boolean copy(final String sourcePath, final String destinationPath) {
		return copy(new File(sourcePath), new File(destinationPath));
	}

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
