/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Useful binary file writer class.
 */
public class BinaryFileWriter extends Ace {

	private FileOutputStream _fos;
	private BufferedOutputStream _bos;
	private OutputStream _output;

	/**
	 * Default constructor.
	 */
	public BinaryFileWriter() {
		reset();
	}

	private void reset() {
		_fos = null;
		_bos = null;
		_output = null;
	}

	/**
	 * Initializes the binary file writer with the specified output file name and the specified writing mode (<tt>true</tt> for append mode).
	 * 
	 * @param filename
	 * @param append
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean init(final String filename, final boolean append) {
		return init(new File(filename), append);
	}

	/**
	 * Initializes the binary file writer with the specified output file and the specified writing mode (<tt>true</tt> for append mode).
	 * 
	 * @param file
	 * @param append
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean init(final File file, final boolean append) {
		try {
			_fos = new FileOutputStream(file, append);
			_bos = new BufferedOutputStream(_fos);
			_output = _bos;
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			reset();
			return false;
		}
	}

	/**
	 * Writes the specified byte array to the output file.
	 * 
	 * @param input
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean write(final byte[] input) {
		try {
			_output.write(input);
			_output.flush();
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Writes the specified input stream to the output file.
	 * 
	 * @param input
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean write(final InputStream input) {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int reads = input.read();
			while (reads != -1) {
				baos.write(reads);
				reads = input.read();
			}
			return write(baos.toByteArray());
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Writes the specified string to the output file.
	 * 
	 * @param input
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean write(final String input) {
		return write(input.getBytes());
	}

	/**
	 * Closes the output file.
	 * 
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean close() {
		try {
			_output.close();
			reset();
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

}
