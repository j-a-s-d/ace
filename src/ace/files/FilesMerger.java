/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.arrays.GenericArrays;
import ace.binary.Streams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Useful file merger class.
 */
public class FilesMerger extends Ace {

	public static final int DEFAULT_BUFFER_SIZE = 4096;

	private File _file;
	private int _bufferSize;

	/**
	 * Constructor accepting the output file name.
	 * 
	 * @param filename 
	 */
	public FilesMerger(final String filename) {
		this(new File(filename), DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Constructor accepting the output file.
	 * 
	 * @param file 
	 */
	public FilesMerger(final File file) {
		this(file, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Constructor accepting the output file name and the buffer size.
	 * 
	 * @param filename 
	 * @param bufferSize 
	 */
	public FilesMerger(final String filename, final int bufferSize) {
		this(new File(filename), bufferSize);
	}

	/**
	 * Constructor accepting the output file and the buffer size.
	 * 
	 * @param file 
	 * @param bufferSize 
	 */
	public FilesMerger(final File file, final int bufferSize) {
		_file = file;
		_bufferSize = bufferSize;
	}

	/**
	 * Gets the buffer size.
	 * 
	 * @return the buffer size
	 */
	public int getBufferSize() {
		return _bufferSize;
	}

	/**
	 * Sets the buffer size.
	 * 
	 * @param value
	 * @return itself
	 */
	public FilesMerger setBufferSize(final int value) {
		_bufferSize = value;
		return this;
	}

	/**
	 * Gets the output file.
	 * 
	 * @return the output file
	 */
	public File getFile() {
		return _file;
	}

	/**
	 * Sets the output file.
	 * 
	 * @param file
	 * @return itself
	 */
	public FilesMerger setFile(final File file) {
		_file = file;
		return this;
	}

	/**
	 * Sequentially concatenates the specified files into the output file using the buffer size for the operation.
	 * 
	 * @param files
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
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
