/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import ace.containers.Lists;
import ace.interfaces.Treater;
import ace.text.StringList;
import java.io.File;
import java.util.List;

/**
 * Useful files (of specific extension) directory class.
 */
public abstract class FilesDirectory extends Ace {

	protected final String _path;
	protected final String _extension;
	protected final FilenameValidator _validator;

	/**
	 * Constructor accepting a directory and a file extension.
	 * 
	 * @param directory
	 * @param extension 
	 */
	public FilesDirectory(final File directory, final String extension) {
		this(directory.getAbsolutePath(), extension);
	}

	/**
	 * Constructor accepting a directory path and a file extension.
	 * 
	 * @param path
	 * @param extension 
	 */
	public FilesDirectory(final String path, final String extension) {
		_path = FilenameUtils.ensureLastDirectorySeparator(path);
		_extension = assigned(extension) ? extension : STRINGS.EMPTY;
		_validator = _extension.length() == 0 ? FilenameValidator.makeAllFilesValidator() : FilenameValidator.makeExtensionsValidator(extension);
	}

	/**
	 * Gets the directory path.
	 * 
	 * @return the directory path
	 */
	public String getPath() {
		return _path;
	}

	/**
	 * Composes a full file name (including the directory path and the file extension) from the specified name.
	 * 
	 * @param name
	 * @return the resulting full file name
	 */
	public String composeFilename(final String name) {
		return _path + name + _extension;
	}

	/**
	 * Composes a file (including the directory path and the file extension) from the specified name.
	 * 
	 * @param name
	 * @return the resulting file
	 */
	public File composeFile(final String name) {
		return new File(composeFilename(name));
	}

	/**
	 * Determines if the directory has an existing file with that name.
	 * 
	 * @param name
	 * @return <tt>true</tt> if the file exists in the directory, <tt>false</tt> otherwise
	 */
	public final boolean has(final String name) {
		return composeFile(name).exists();
	}

	/**
	 * Deletes the file with the specified name from the directory.
	 * 
	 * @param name
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public final boolean delete(final String name) {
		return composeFile(name).delete();
	}

	/**
	 * Lists the files in the directory (in a non-recursive fashion).
	 * 
	 * @return the list of files
	 */
	public final List<File> list() {
		return Directories.listFiles(_path, false, _validator);
	}

	/**
	 * Lists the file names (without directory path and without extension) in the directory (in a non-recursive fashion).
	 * 
	 * @return the list of file names
	 */
	public final List<String> listNames() {
		final List<String> result = Lists.make();
		for (final File file : list()) {
			result.add(FilenameUtils.stripExtension(file.getName()));
		}
		return result;
	}

	/**
	 * Find names that match the specified name filter. It accepts '*' wildcard for the beginning and/or the ending of the filter.
	 * 
	 * @param nameFilter
	 * @return the resulting list of file names
	 */
	public List<String> findNames(final String nameFilter) {
		return new StringList(listNames()).filter(nameFilter).toList();
	}

	/**
	 * Summarizes the size of all the directory files.
	 * 
	 * @return the resulting sum
	 */
	public final long length() {
		return Directories.calculateSize(_path);
	}

	/**
	 * Treats all directory files with the specified treater.
	 * 
	 * @param treater
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public final boolean treat(final Treater<byte[]> treater) {
		boolean result = true;
		for (final File file : list()) {
			result &= BinaryFiles.treat(file, treater);
		}
		return result;
	}

}
