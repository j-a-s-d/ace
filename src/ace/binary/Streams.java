/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import ace.Ace;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public class Streams extends Ace {

	public static final int DEFAULT_COPY_BUFFER_SIZE = 4096;
	public static final int DEFAULT_DEEP_COPY_BUFFER_SIZE = 1;

	public static boolean copy(final InputStream input, final OutputStream output) {
		return copy(input, output, DEFAULT_COPY_BUFFER_SIZE);
	}

	public static boolean copy(final InputStream input, final OutputStream output, final int bufferSize) {
		try {
			int bytesRead;
			final byte[] buffer = new byte[bufferSize];
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	public static byte[] deepCopyToByteArray(final InputStream input) {
		return deepCopyToByteArray(input, DEFAULT_DEEP_COPY_BUFFER_SIZE);
	}

	public static byte[] deepCopyToByteArray(final InputStream input, final int bufferSize) {
		try {
			final byte[] result = new byte[input.available()];
			final byte[] tmp = new byte[bufferSize];
			int x = 0;
			int read;
			while ((read = input.read(tmp)) >= 0) {
				for (int c = 0; c < read; c++) {
					result[x++] = tmp[c];
				}
			}
			return result;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static byte[] toByteArray(final InputStream input) {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Streams.copy(input, baos);
			baos.flush();
			return baos.toByteArray();
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static boolean hasSameBytes(final BufferedInputStream value1, final BufferedInputStream value2) {
		try {
			int x = value1.read();
			while (x != -1) {
				if (x != value2.read()) {
					return false;
				}
				x = value1.read();
			}
			return value2.read() == -1;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	public static void close(final Closeable closeable) {
		try {
			if (assigned(closeable)) {
				closeable.close();
			}
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
	}

	public static boolean copySegment(final InputStream input, final long skip, final long length, final OutputStream output) {
		return copySegment(input, skip, length, output, DEFAULT_COPY_BUFFER_SIZE);
	}

	public static boolean copySegment(final InputStream input, final long skip, final long length, final OutputStream output, final int bufferSize) {
		try {
			input.skip(skip);
			final int x = length > Integer.MAX_VALUE ? bufferSize : Math.min((int) length, bufferSize);
			final byte[] buffer = new byte[x];
			int bytesRead;
			long bytesToRead = length;
			while ((bytesRead = input.read(buffer)) != -1 && bytesToRead > 0) {
				output.write(buffer, 0, bytesRead);
				bytesToRead -= bytesRead;
				if (bytesToRead < x) {
					final byte[] lastBuffer = new byte[(int) bytesToRead];
					if ((bytesRead = input.read(lastBuffer)) != -1) {
						output.write(lastBuffer, 0, bytesRead);
						bytesToRead -= bytesRead;
						if (bytesToRead > 0) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

}
