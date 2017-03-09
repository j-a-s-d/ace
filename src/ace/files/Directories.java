/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.containers.Lists;
import java.io.File;
import java.util.List;

public class Directories extends Ace {

	public static boolean exists(final File directory) {
		return assigned(directory) && directory.isDirectory() && directory.exists();
	}

	public abstract static class DirectoryWalkerHandler {

		public abstract void processFile(File file);

	}

	public static final void walkDirectory(final File directory, final boolean recursive, final DirectoryWalkerHandler handler) {
		if (directory != null && directory.exists()) {
			for (final File file : directory.listFiles()) {
				handler.processFile(file);
				if (recursive && file.isDirectory()) {
					walkDirectory(file, recursive, handler);
				}
			}
		}
	}

	public static final List<File> listFilesFromDirectory(final String directoryPath) {
		return listFilesFromDirectory(directoryPath, false, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFilesFromDirectory(final String directoryPath, final boolean recursive) {
		return listFilesFromDirectory(directoryPath, recursive, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFilesFromDirectory(final String directoryPath, final boolean recursive, final FilenameValidator validator) {
		return listFilesFromDirectory(new File(directoryPath), recursive, validator);
	}

	public static final List<File> listFilesFromDirectory(final File directory) {
		return listFilesFromDirectory(directory, false, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFilesFromDirectory(final File directory, final boolean recursive) {
		return listFilesFromDirectory(directory, recursive, FilenameValidator.makeAllFilesValidator());
	}

	public static final List<File> listFilesFromDirectory(final File directory, final boolean recursive, final FilenameValidator validator) {
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

	public static final List<File> listDirectoriesFromDirectory(final String directoryPath, final boolean recursive) {
		return listDirectoriesFromDirectory(new File(directoryPath), recursive);
	}

	public static final List<File> listDirectoriesFromDirectory(final File directory, final boolean recursive) {
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

	public static long getDirectorySize(final String directoryPath) {
		return getDirectorySize(new File(directoryPath));
	}

	public static long getDirectorySize(final File directory) {
		long result = 0;
		for (final File file : listFilesFromDirectory(directory)) {
			result += file.isFile() ? file.length() : getDirectorySize(file);
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
