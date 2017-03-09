/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.arrays.GenericArrays;
import ace.binary.Streams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FilesMerger extends Ace {

	public static final int DEFAULT_BUFFER_SIZE = 4096;

	private File _file;
	private int _bufferSize;

	public FilesMerger(final String filename) {
		this(new File(filename), DEFAULT_BUFFER_SIZE);
	}

	public FilesMerger(final File file) {
		this(file, DEFAULT_BUFFER_SIZE);
	}

	public FilesMerger(final String filename, final int bufferSize) {
		this(new File(filename), bufferSize);
	}

	public FilesMerger(final File file, final int bufferSize) {
		_file = file;
		_bufferSize = bufferSize;
	}

	public int getBufferSize() {
		return _bufferSize;
	}

	public FilesMerger setBufferSize(final int value) {
		_bufferSize = value;
		return this;
	}

	public File getFile() {
		return _file;
	}

	public FilesMerger setFile(final File file) {
		_file = file;
		return this;
	}

	public boolean merge(final File... files) {
		if (GenericArrays.hasContent(files)) {
			try {
				final FileOutputStream fos = new FileOutputStream(_file);
				for (final File file : files) {
					final FileInputStream fis = new FileInputStream(file);
					final byte[] buffer = new byte[_bufferSize];
					int read;
					while ((read = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, read);
					}
					Streams.close(fis);
				}
				fos.flush();
				Streams.close(fos);
				return true;
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return false;
	}

}
