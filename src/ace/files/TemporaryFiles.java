/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.Sandboxed;
import java.io.File;
import java.io.FileNotFoundException;

public class TemporaryFiles extends Ace {

	public static final String PREFIX = "ace";
	public static final String EXTENSION = ".tmp";

	private static int _counter;

	public static int getCreatedCount() {
		return _counter;
	}

	public static File create() {
		return create(PREFIX, (File) null);
	}

	public static File create(final String prefix) {
		return create(prefix, (File) null);
	}

	public static File create(final File directory) {
		return create(PREFIX, directory);
	}

	public static File create(final String prefix, final String directoryName) {
		return create(prefix, new File(directoryName));
	}

	public static File create(final String prefix, final File directory) {
		return new Sandboxed<File>() {
			/*@Override*/ public File run() throws Exception {
				final File temp = File.createTempFile(prefix, EXTENSION, directory);
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
