/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.text.StringComposer;
import java.io.File;

/**
 * Useful buffered text file writer class.
 */
public class BufferedTextFileAppender extends FileAppender {

	public static final int DEFAULT_BUFFER_SIZE = 4096;

	private String _appendix;
	private StringComposer _buffer;
	private int _bufferSize;

	/**
	 * Constructor accepting the output file name.
	 * 
	 * @param filename 
	 */
	public BufferedTextFileAppender(final String filename) {
		this(filename, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Constructor accepting the output file.
	 * 
	 * @param file 
	 */
	public BufferedTextFileAppender(final File file) {
		this(file, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Constructor accepting the output file name and the buffer size.
	 * 
	 * @param filename 
	 * @param bufferSize 
	 */
	public BufferedTextFileAppender(final String filename, final int bufferSize) {
		super(filename);
		initialize(bufferSize);
	}

	/**
	 * Constructor accepting the output file and the buffer size.
	 * 
	 * @param file 
	 * @param bufferSize 
	 */
	public BufferedTextFileAppender(final File file, final int bufferSize) {
		super(file);
		initialize(bufferSize);
	}

	private void initialize(final int bufferSize) {
		_buffer = new StringComposer();
		_bufferSize = bufferSize;
		_appendix = null;
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
	public BufferedTextFileAppender setBufferSize(final int value) {
		_bufferSize = value;
		return this;
	}

	/**
	 * Gets the appendix for each single append operation.
	 * 
	 * Specially useful for line feeds and alike line suffixes.
	 * 
	 * If it is <tt>null</tt> it won't be used.
	 * 
	 * @return the appendix
	 */
	public String getAppendix() {
		return _appendix;
	}

	/**
	 * Sets the appendix for each single append operation.
	 * 
	 * Specially useful for line feeds and alike line suffixes.
	 * 
	 * If it is <tt>null</tt> it won't be used.
	 * 
	 * @param value
	 * @return itself
	 */
	public BufferedTextFileAppender setAppendix(final String value) {
		_appendix = value;
		return this;
	}

	private BufferedTextFileAppender completeWrite() {
		_buffer.append(assigned(_appendix), _appendix);
		if (_buffer.length() >= _bufferSize) {
			flush();
		}
		return this;
	}

	/**
	 * Writes the specified strings to the buffered output text file if the specified condition value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param strings
	 * @return itself
	 */
	public BufferedTextFileAppender write(final boolean condition, final String... strings) {
		return condition ? write(strings) : this;
	}

	/**
	 * Writes the specified strings to the buffered output text file.
	 * 
	 * @param strings
	 * @return itself
	 */
	public BufferedTextFileAppender write(final String... strings) {
		_buffer.append(strings);
		return completeWrite();
	}

	/**
	 * Writes the specified string to the buffered output text file if the specified condition value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param string
	 * @return itself
	 */
	public BufferedTextFileAppender write(final boolean condition, final String string) {
		return condition ? write(string) : this;
	}

	/**
	 * Writes the specified string to the buffered output text file.
	 * 
	 * @param string
	 * @return itself
	 */
	public BufferedTextFileAppender write(final String string) {
		_buffer.append(string);
		return completeWrite();
	}

	/**
	 * Flushes the currently buffer content to the output file.
	 */
	public void flush() {
		append(_buffer.toString());
		_buffer.reset();
	}

	/**
	 * Flushes and closes the output file.
	 */
	/*@Override*/ public void close() {
		flush();
		closeFile();
	}

}
