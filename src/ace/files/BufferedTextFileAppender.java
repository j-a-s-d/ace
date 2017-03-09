/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.text.StringComposer;
import java.io.File;

public class BufferedTextFileAppender extends FileAppender {

	public static final int DEFAULT_BUFFER_SIZE = 4096;

	private String _appendix;
	private StringComposer _buffer;
	private int _bufferSize;

	public BufferedTextFileAppender(final String filename) {
		this(filename, DEFAULT_BUFFER_SIZE);
	}

	public BufferedTextFileAppender(final File file) {
		this(file, DEFAULT_BUFFER_SIZE);
	}

	public BufferedTextFileAppender(final String filename, final int bufferSize) {
		super(filename);
		initialize(bufferSize);
	}

	public BufferedTextFileAppender(final File file, final int bufferSize) {
		super(file);
		initialize(bufferSize);
	}

	private void initialize(final int bufferSize) {
		_buffer = new StringComposer();
		_bufferSize = bufferSize;
		_appendix = null;
	}

	public int getBufferSize() {
		return _bufferSize;
	}

	public BufferedTextFileAppender setBufferSize(final int value) {
		_bufferSize = value;
		return this;
	}

	public String getAppendix() {
		return _appendix;
	}

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

	public BufferedTextFileAppender write(final boolean condition, final String... strings) {
		return condition ? write(strings) : this;
	}

	public BufferedTextFileAppender write(final String... strings) {
		_buffer.append(strings);
		return completeWrite();
	}

	public BufferedTextFileAppender write(final boolean condition, final String string) {
		return condition ? write(string) : this;
	}

	public BufferedTextFileAppender write(final String string) {
		_buffer.append(string);
		return completeWrite();
	}

	public void flush() {
		append(_buffer.toString());
		_buffer.reset();
	}

	/*@Override*/ public void close() {
		flush();
		closeFile();
	}

}
