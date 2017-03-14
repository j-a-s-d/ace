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

	public static int getCreatedCount() {
		return _counter;
	}

	public static File getDirectory() {
		return new File(getDirectoryPath());
	}

	public static String getDirectoryPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static File create() {
		return create(FILE_PREFIX, (File) null);
	}

	public static File create(final String prefix) {
		return create(prefix, (File) null);
	}

	public static File create(final File directory) {
		return create(FILE_PREFIX, directory);
	}

	public static File create(final String prefix, final String directoryName) {
		return create(prefix, new File(directoryName));
	}

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
