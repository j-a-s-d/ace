/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.interfaces.Reseteable;
import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;

public class FileAppender extends Ace implements Reseteable, Closeable {

	private final File _file;
	private RandomAccessFile _raf;

	public FileAppender(final String filename) {
		this(new File(filename));
	}

	public FileAppender(final File file) {
		_file = file;
		reset();
	}

	/*@Override*/ public final void reset() {
		_raf = initializeAppender(_file);
	}

	private RandomAccessFile initializeAppender(final File file) {
		try {
			final RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(file.length());
			return raf;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public boolean append(final String string) {
		if (_raf != null) {
			try {
				_raf.writeBytes(string);
				return true;
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return false;
	}

	public boolean append(final byte[] bytes) {
		if (_raf != null) {
			try {
				_raf.write(bytes);
				return true;
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return false;
	}

	protected void closeFile() {
		if (_raf != null) {
			try {
				_raf.close();
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
	}

	/*@Override*/ public void close() {
		closeFile();
	}

}
