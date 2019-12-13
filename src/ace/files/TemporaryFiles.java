/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.Sandboxed;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Utility class for working with temporary files.
 */
public class TemporaryFiles extends Ace {

	public static final String FILE_PREFIX = "ace";
	public static final String FILE_EXTENSION = ".tmp";

	private static int _counter;

	/**
	 * Gets the count of the already created temporary files.
	 * 
	 * @return the count of the already created temporary files
	 */
	public static int getCreatedCount() {
		return _counter;
	}

	/**
	 * Gets the directory where the temporary files are created.
	 * 
	 * @return the directory where the temporary files are created
	 */
	public static File getDirectory() {
		return new File(getDirectoryPath());
	}

	/**
	 * Gets the directory path where the temporary files are created (system property: java.io.tmpdir).
	 * 
	 * @return the directory path where the temporary files are created
	 */
	public static String getDirectoryPath() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * Creates a temporary file.
	 * 
	 * @return the created temporary file
	 */
	public static File create() {
		return create(FILE_PREFIX, (File) null);
	}

	/**
	 * Creates a temporary file with a specified prefix.
	 * 
	 * @param prefix
	 * @return the created temporary file
	 */
	public static File create(final String prefix) {
		return create(prefix, (File) null);
	}

	/**
	 * Creates a temporary file in a specified directory.
	 * 
	 * @param directory
	 * @return the created temporary file
	 */
	public static File create(final File directory) {
		return create(FILE_PREFIX, directory);
	}

	/**
	 * Creates a temporary file with a specified prefix and in a specified directory path.
	 * 
	 * @param prefix
	 * @param directoryPath
	 * @return the created temporary file
	 */
	public static File create(final String prefix, final String directoryPath) {
		return create(prefix, new File(directoryPath));
	}

	/**
	 * Creates a temporary file with a specified prefix and in a specified directory.
	 * 
	 * @param prefix
	 * @param directory
	 * @return the created temporary file
	 */
	public static File create(final String prefix, final File directory) {
		return new Sandboxed<File>() {
			/*@Override*/ public File run() throws Exception {
				final File temp = File.createTempFile(prefix, FILE_EXTENSION, directory);
				temp.deleteOnExit();
				if (!temp.exists()) {
					throw new FileNotFoundException(temp.getAbsolutePath());
				}
				_counter++;
				return temp;
			}
		}.go();
	}

}
