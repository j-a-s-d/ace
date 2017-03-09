/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BinaryFileWriter extends Ace {

	private FileOutputStream _fos;
	private BufferedOutputStream _bos;
	private OutputStream _output;

	public BinaryFileWriter() {
		reset();
	}

	private void reset() {
		_fos = null;
		_bos = null;
		_output = null;
	}

	public boolean init(final String filename, final boolean append) {
		return init(new File(filename), append);
	}

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

	public boolean write(final String input) {
		return write(input.getBytes());
	}

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
