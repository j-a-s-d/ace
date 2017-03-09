/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import ace.containers.Lists;
import ace.interfaces.Treater;
import ace.text.StringList;
import java.io.File;
import java.util.List;

public abstract class FilesDirectory extends Ace {

	protected final String _path;
	protected final String _extension;
	protected final FilenameValidator _validator;

	public FilesDirectory(final Class<?> clazz, final String path, final String extension) {
		_path = FilenameUtils.ensureLastDirectorySeparator(path);
		_extension = assigned(extension) ? extension : STRINGS.EMPTY;
		_validator = _extension.length() == 0 ? FilenameValidator.makeAllFilesValidator() : FilenameValidator.makeExtensionsValidator(extension);
	}

	public String getPath() {
		return _path;
	}

	public String composeFilename(final String name) {
		return _path + name + _extension;
	}

	public File composeFile(final String name) {
		return new File(composeFilename(name));
	}

	public final boolean has(final String name) {
		return composeFile(name).exists();
	}

	public final boolean delete(final String name) {
		return composeFile(name).delete();
	}

	public final List<File> list() {
		return Directories.listFilesFromDirectory(_path, false, _validator);
	}

	public final List<String> listNames() {
		final List<String> result = Lists.make();
		for (final File file : list()) {
			result.add(FilenameUtils.stripExtension(file.getName()));
		}
		return result;
	}

	public List<String> findNames(final String name) {
		return new StringList(listNames()).filter(name).toList();
	}

	public final long length() {
		return Directories.getDirectorySize(_path);
	}

	public final boolean treat(final Treater<byte[]> treater) {
		boolean result = true;
		for (final File file : list()) {
			result &= BinaryFiles.treat(file, treater);
		}
		return result;
	}

}
