/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.binary.Streams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Useful file splitter class.
 */
public class FilesSplitter extends Ace {

	public static final int DEFAULT_BUFFER_SIZE = 4096;
	public static String SPLITTED_PART_EXTENSION_PREFIX = ".part.";

	private File _file;
	private int _bufferSize;

	public FilesSplitter(final String filename) {
		this(new File(filename), DEFAULT_BUFFER_SIZE);
	}

	public FilesSplitter(final File file) {
		this(file, DEFAULT_BUFFER_SIZE);
	}

	public FilesSplitter(final String filename, final int bufferSize) {
		this(new File(filename), bufferSize);
	}

	public FilesSplitter(final File file, final int bufferSize) {
		_file = file;
		_bufferSize = bufferSize;
	}

	public int getBufferSize() {
		return _bufferSize;
	}

	public FilesSplitter setBufferSize(final int value) {
		_bufferSize = value;
		return this;
	}

	public File getFile() {
		return _file;
	}

	public FilesSplitter setFile(final File file) {
		_file = file;
		return this;
	}

	public File[] splitIntoParts(final int partsAmount) {
		long fileSize;
		if (assigned(_file) && _file.exists() && (fileSize = _file.length()) > 0) {
			final long partsSize = fileSize / partsAmount;
			return splitBySize(partsSize + Math.max(fileSize % partsSize, 0));
		}
		return null;
	}

	public File[] splitBySize(final long partsSize) {
		long fileSize;
		if (assigned(_file) && _file.exists() && (fileSize = _file.length()) > 0 && partsSize > 0) {
			try {
				int parts = ((int) (fileSize / partsSize));
				if (fileSize % partsSize > 0) {
					parts++;
				}
				final int bufferSize = Math.min((int) partsSize, _bufferSize);
				final File[] files = new File[parts];
				final FileInputStream fis = new FileInputStream(_file);
				for (int i = 0; i < files.length; i++) {
					files[i] = new File(_file.getPath() + SPLITTED_PART_EXTENSION_PREFIX + i);
					final FileOutputStream fos = new FileOutputStream(files[i]);
					long total = 0;
					final byte[] buffer = new byte[bufferSize];
					int read;
					while (total < partsSize) {
						read = fis.read(buffer);
						if (read != -1) {
							total += read;
							fos.write(buffer, 0, read);
						}
						if (i == files.length - 1 && read < bufferSize) {
							break;
						}
					}
					fos.flush();
					Streams.close(fos);
				}
				Streams.close(fis);
				return files;
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return null;
	}

}
