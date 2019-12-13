/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import ace.Ace;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class for working with streams.
 */
public class Streams extends Ace {

	/**
	 * Indicates the buffer size used inside the copy methods.
	 */
	public static final int DEFAULT_COPY_BUFFER_SIZE = 4096;

	/**
	 * Indicates the buffer size used inside the deepCopy methods.
	 */
	public static final int DEFAULT_DEEP_COPY_BUFFER_SIZE = 1;

	/**
	 * Copies the supplied input stream to the supplied output stream
	 * using the default copy buffer size.
	 * 
	 * Note: this method uses the read and write methods of the supplied streams
	 * to perform the operation.
	 * 
	 * @param input
	 * @param output
	 * @return <tt>true</tt> if the operation was successful or
	 * <tt>false</tt> if it failed
	 */
	public static boolean copy(final InputStream input, final OutputStream output) {
		return copy(input, output, DEFAULT_COPY_BUFFER_SIZE);
	}

	/**
	 * Copies the supplied input stream to the supplied output stream
	 * using the specified buffer size.
	 * 
	 * Note: this method uses the read and write methods of the supplied streams
	 * to perform the operation.
	 * 
	 * @param input
	 * @param output
	 * @param bufferSize
	 * @return <tt>true</tt> if the operation was successful or
	 * <tt>false</tt> if it failed
	 */
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

	/**
	 * Copies the supplied input stream to a newly created byte array
	 * using the default deep copy buffer size.
	 * 
	 * Note: this method uses the read method of the input stream an a
	 * temporal byte buffer to perform the operation.
	 * 
	 * @param input
	 * @return the resulting byte array if the operation was successful
	 * or null if it failed
	 */
	public static byte[] deepCopyToByteArray(final InputStream input) {
		return deepCopyToByteArray(input, DEFAULT_DEEP_COPY_BUFFER_SIZE);
	}

	/**
	 * Copies the supplied input stream to a newly created byte array
	 * using the specified deep copy buffer size.
	 * 
	 * Note: this method uses the read method of the input stream an a
	 * temporal byte buffer to perform the operation.
	 * 
	 * @param input
	 * @param bufferSize
	 * @return the resulting byte array if the operation was successful
	 * or null if it failed
	 */
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

	/**
	 * Copies the supplied input stream to a newly created byte array.
	 * 
	 * Note: this method uses the copy method to perform the operation.
	 * 
	 * @param input
	 * @return the resulting byte array if the operation was successful
	 * or null if it failed
	 */
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

	/**
	 * Compares the supplied streams looking for the same content.
	 * 
	 * @param stream1
	 * @param stream2
	 * @return <tt>true</tt> if the contents of both streams are equal
	 * or <tt>false</tt> if it is different
	 */
	public static boolean hasSameBytes(final BufferedInputStream stream1, final BufferedInputStream stream2) {
		try {
			int x = stream1.read();
			while (x != -1) {
				if (x != stream2.read()) {
					return false;
				}
				x = stream1.read();
			}
			return stream2.read() == -1;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Quite method that closes the supplied closable catching any possible exception.
	 * 
	 * @param closeable 
	 */
	public static void close(final Closeable closeable) {
		try {
			if (assigned(closeable)) {
				closeable.close();
			}
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
	}

	/**
	 * Copies the specified segment of the supplied input stream to the supplied output stream
	 * using the default copy buffer size.
	 * 
	 * @param input
	 * @param skip
	 * @param length
	 * @param output
	 * @return <tt>true</tt> if the operation was successful or
	 * <tt>false</tt> if it failed
	 */
	public static boolean copySegment(final InputStream input, final long skip, final long length, final OutputStream output) {
		return copySegment(input, skip, length, output, DEFAULT_COPY_BUFFER_SIZE);
	}

	/**
	 * Copies the specified segment of the supplied input stream to the supplied output stream
	 * using the specified copy buffer size.
	 * 
	 * @param input
	 * @param skip
	 * @param length
	 * @param output
	 * @param bufferSize
	 * @return <tt>true</tt> if the operation was successful or
	 * <tt>false</tt> if it failed
	 */
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
