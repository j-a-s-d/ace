/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.binary.Streams;
import ace.interfaces.Treater;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class BinaryFiles extends Ace {

	public static final byte[] read(final String filename) {
		return read(new File(filename));
	}

	public static final byte[] read(final File file) {
		if (file == null || !file.exists()) {
			GEH.setLastException(new FileNotFoundException()); // NOTE: same as in java.io.RandomAccessFile.setLength()
			return null;
		}
		if (file.length() > Integer.MAX_VALUE) {
			GEH.setLastException(new IOException("File too large")); // NOTE: same as in java.io.RandomAccessFile.setLength()
			return null;
		}
		return read(file, (long) 0, (int) file.length());
	}

	public static final byte[] read(final String filename, final long fromPosition, final int length) {
		return read(new File(filename), fromPosition, length);
	}

	public static final byte[] read(final File file, final long fromPosition, final int length) {
		try {
			final byte[] result = new byte[length];
			final RandomAccessFile raf = new RandomAccessFile(file, "r");
			raf.seek(fromPosition);
			raf.read(result);
			raf.close();
			return result;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static final InputStream read(final String filename, final int length, final long fromPosition) {
		return read(new File(filename), length, fromPosition);
	}
	
	public static final InputStream read(final File file, final int length, final long fromPosition) {
		return new ByteArrayInputStream(read(file, fromPosition, length));
	}

	private static boolean performByteArrayFileWriting(final File file, final byte[] content, final boolean append) {
		boolean result = false;
		final BinaryFileWriter bfw = new BinaryFileWriter();
		if (bfw.init(file, append)) {
			try {
				result = bfw.write(content);
			} finally {
				bfw.close();
			}
		}
		return result;
	}

	public static final boolean write(final String filename, final byte[] content) {
		return performByteArrayFileWriting(new File(filename), content, false);
	}

	public static final boolean write(final File file, final byte[] content) {
		return performByteArrayFileWriting(file, content, false);
	}

	public static final boolean append(final String filename, final byte[] content) {
		return performByteArrayFileWriting(new File(filename), content, true);
	}

	public static final boolean append(final File file, final byte[] content) {
		return performByteArrayFileWriting(file, content, true);
	}

	private static boolean performInputStreamFileWriting(final File file, final InputStream content, final boolean append) {
		boolean result = false;
		final BinaryFileWriter bfw = new BinaryFileWriter();
		if (bfw.init(file, append)) {
			try {
				result = bfw.write(content);
			} finally {
				bfw.close();
			}
		}
		return result;
	}

	public static final boolean write(final String filename, final InputStream content) {
		return performInputStreamFileWriting(new File(filename), content, false);
	}

	public static final boolean write(final File file, final InputStream content) {
		return performInputStreamFileWriting(file, content, false);
	}

	public static final boolean append(final String filename, final InputStream content) {
		return performInputStreamFileWriting(new File(filename), content, true);
	}

	public static final boolean append(final File file, final InputStream content) {
		return performInputStreamFileWriting(file, content, true);
	}

	public static final boolean treat(final File file, final Treater<byte[]> treater) {
		return write(file, treater.treat(read(file)));
	}

	public static boolean copySegmentToStream(final File inputFile, final long skip, final long length, final OutputStream output) {
		try {
			final FileInputStream input = new FileInputStream(inputFile);
			try {
				return Streams.copySegment(input, skip, length, output);
			} finally {
				Streams.close(input);
			}
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		return false;
	}

	public static byte[] readSegment(final File file, final long skip, final long length) {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (copySegmentToStream(file, skip, length, baos)) {
				return baos.toByteArray();
			}
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		return null;
	}

}
