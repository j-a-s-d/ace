/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import ace.Ace;
import ace.arrays.ByteArrays;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Useful binary data flexible composing class.
 */
public class BinaryComposer extends Ace {

	public enum Mode {
		ARRAY, STREAM
	};
	private Mode _mode = Mode.STREAM;
	private ByteArrayOutputStream _bos = null;
	private DataOutputStream _out = null;
	private byte[] _buffer = null;

	public BinaryComposer() {
		reset();
	}

	public BinaryComposer(final Mode mode) {
		_mode = mode;
		reset();
	}

	public BinaryComposer(final File file) {
		set(file);
	}

	public BinaryComposer(final InputStream content) {
		set(content);
	}

	public BinaryComposer(final String content) {
		set(content);
	}

	public BinaryComposer(final char[] content) {
		set(content);
	}

	public BinaryComposer(final byte[] content) {
		set(content);
	}

	public BinaryComposer(final int length, final byte filling) {
		set(length, filling);
	}

	public final BinaryComposer reset() {
		if (_mode.equals(Mode.STREAM)) {
			_bos = new ByteArrayOutputStream();
			_out = new DataOutputStream(_bos);
		} else {
			_buffer = new byte[] {};
		}
		return this;
	}

	public final BinaryComposer set(final byte[] data) {
		if (_mode.equals(Mode.STREAM)) {
			reset();
			append(data);
		} else {
			_buffer = data;
		}
		return this;
	}

	public final BinaryComposer set(final char[] data) {
		return set(data == null ? null : new String(data).getBytes());
	}

	public final BinaryComposer set(final String string) {
		return set(string == null ? null : string.getBytes());
	}

	public final BinaryComposer set(final InputStream inputStream, final int size) {
		try {
			final byte[] arr = new byte[size];
			inputStream.read(arr, 0, arr.length);
			return set(arr);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public final BinaryComposer set(final InputStream inputStream) {
		try {
			return set(inputStream, inputStream.available());
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public final BinaryComposer set(final File file) {
		try {
			return set(new FileInputStream(file), (int) file.length());
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public final BinaryComposer set(final int length, final byte filling) {
		final byte[] content = new byte[length];
		Arrays.fill(content, filling);
		return set(content);
	}

	public final byte[] get() {
		if (_mode.equals(Mode.STREAM)) {
			try {
				_out.close();
				final byte[] result = _bos.toByteArray();
				_bos.close();
				return result;
			} catch (final Exception e) {
				GEH.setLastException(e);
				return null;
			}
		} else {
			return _buffer;
		}
	}

	public final char[] getAsCharArray() {
		final byte[] bytes = get();
		final char[] result = new char[bytes.length];
		int index = 0;
		for (final byte b : bytes) {
			result[index++] = (char) b;
		}
		return result;
	}

	public final String getAsString() {
		return new String(getAsCharArray());
	}

	public final String getAsHexString() {
		return Hex.fromByteArray(get());
	}

	public final BinaryComposer append(final byte[] data) {
		if (_mode.equals(Mode.STREAM)) {
			try {
				_out.write(data);
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		} else {
			_buffer = ByteArrays.concat(_buffer, data);
		}
		return this;
	}

	public final BinaryComposer append(final int length, final byte filling) {
		final byte[] content = new byte[length];
		Arrays.fill(content, filling);
		return append(content);
	}

	public final BinaryComposer append(final char[] data) {
		return append(data == null ? null : new String(data).getBytes());
	}

	public final BinaryComposer append(final String string) {
		return append(string == null ? null : string.getBytes());
	}

	public final BinaryComposer append(final InputStream inputStream, final int size) {
		try {
			final byte[] arr = new byte[size];
			inputStream.read(arr, 0, arr.length);
			append(arr);
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		return this;
	}

	public final int size() {
		return _mode.equals(Mode.STREAM) ? _out.size() : _buffer.length;
	}

}
