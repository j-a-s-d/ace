/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.interfaces.Reseteable;
import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;

/**
 * Useful file appender class.
 */
public class FileAppender extends Ace implements Reseteable, Closeable {

	private final File _file;
	private RandomAccessFile _raf;

	/**
	 * Constructor accepting the output file name.
	 * 
	 * @param filename 
	 */
	public FileAppender(final String filename) {
		this(new File(filename));
	}

	/**
	 * Constructor accepting the output file.
	 * 
	 * @param file 
	 */
	public FileAppender(final File file) {
		_file = file;
		reset();
	}

	/**
	 * Resets the file appender.
	 */
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

	/**
	 * Appends the output file content with the specified string.
	 * 
	 * @param string
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
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

	/**
	 * Appends the output file content with the specified byte array.
	 * 
	 * @param bytes
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
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

	/**
	 * Closes the output file.
	 */
	protected void closeFile() {
		if (_raf != null) {
			try {
				_raf.close();
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
	}

	/**
	 * Closes the output file.
	 */
	/*@Override*/ public void close() {
		closeFile();
	}

}
