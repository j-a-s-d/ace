/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import ace.interfaces.Treater;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Utility class for working with text files.
 */
public class TextFiles extends Ace {

	public static final String FILE_EXTENSION = ".txt";
	public static String CHARSET = "utf-8";

	/**
	 * Reads the contents of the file with the specified name.
	 * 
	 * @param filename
	 * @return the contents of the file with the specified name or <tt>null</tt> if it fails
	 */
	public static final String read(final String filename) {
		return read(new File(filename), CHARSET);
	}

	/**
	 * Reads the contents of the specified file.
	 * 
	 * @param file
	 * @return the contents of the specified file or <tt>null</tt> if it fails
	 */
	public static final String read(final File file) {
		return read(file, CHARSET);
	}

	/**
	 * Reads the contents of the file with the specified name in the specified character set.
	 * 
	 * @param filename
	 * @param charset
	 * @return the contents of the file with the specified name in the specified character set or <tt>null</tt> if it fails
	 */
	public static final String read(final String filename, final String charset) {
		return read(new File(filename), charset);
	}

	/**
	 * Reads the contents of the specified file in the specified character set.
	 * 
	 * @param file
	 * @param charset
	 * @return the contents of the specified file in the specified character set or <tt>null</tt> if it fails
	 */
	public static final String read(final File file, final String charset) {
		String result = null;
		try {
			boolean firstLine = true;
			final StringBuilder contents = new StringBuilder();
			final BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			String line;
			try {
				while ((line = input.readLine()) != null) {
					if (!firstLine) {
						contents.append(STRINGS.EOL);
					} else {
						firstLine = false;
					}
					contents.append(line);
				}
			} finally {
				input.close();
			}
			result = contents.toString();
		} catch (final Exception e) {
			GEH.setLastException(e);
		}
		return result;
	}

	private static void performWriting(final OutputStreamWriter out, final String text) {
		final PrintWriter pw = new PrintWriter(out);
		try {
			pw.print(text);
		} finally {
			pw.flush();
			pw.close();
		}
	}

	/**
	 * Writes the specified text in the file with the specified name in the specified character set.
	 * 
	 * @param filename
	 * @param text
	 * @param charset
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean write(final String filename, final String text, final String charset) {
		return write(new File(filename), text, charset);
	}

	/**
	 * Writes the specified text in the specified file in the specified character set.
	 * 
	 * @param file
	 * @param text
	 * @param charset
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean write(final File file, final String text, final String charset) {
		try {
			performWriting(new OutputStreamWriter(new FileOutputStream(file, false), charset), text);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Writes the specified text in the file with the specified name.
	 * 
	 * @param filename
	 * @param text
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean write(final String filename, final String text) {
		return write(new File(filename), text);
	}

	/**
	 * Writes the specified text in the specified file.
	 * 
	 * @param file
	 * @param text
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean write(final File file, final String text) {
		return write(file, text, CHARSET);
	}

	/**
	 * Appends the specified text in the file with the specified name.
	 * 
	 * @param filename
	 * @param text
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean append(final String filename, final String text) {
		return append(new File(filename), text, CHARSET);
	}

	/**
	 * Appends the specified text in the specified file.
	 * 
	 * @param file
	 * @param text
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean append(final File file, final String text) {
		return append(file, text, CHARSET);
	}

	/**
	 * Appends the specified text in the file with the specified name in the specified character set.
	 * 
	 * @param filename
	 * @param text
	 * @param charset
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean append(final String filename, final String text, final String charset) {
		return append(new File(filename), text, charset);
	}

	/**
	 * Appends the specified text in the specified file in the specified character set.
	 * 
	 * @param file
	 * @param text
	 * @param charset
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean append(final File file, final String text, final String charset) {
		try {
			performWriting(new OutputStreamWriter(new FileOutputStream(file, true), charset), text);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Reads the content of the input file with the specified name in the specified character set and writes it in the specified character set to the output file with the name specified.
	 * 
	 * @param inputFilename
	 * @param inputEncoding
	 * @param outputFilename
	 * @param outputEncoding
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean transcode(final String inputFilename, final String inputEncoding, final String outputFilename, final String outputEncoding) {
		return transcode(new File(inputFilename), inputEncoding, new File(outputFilename), outputEncoding);
	}

	/**
	 * Reads the content of the specified input file in the specified character set and writes it in the specified character set to the specified output file.
	 * 
	 * @param inputFile
	 * @param inputEncoding
	 * @param outputFile
	 * @param outputEncoding
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean transcode(final File inputFile, final String inputEncoding, final File outputFile, final String outputEncoding) {
		try {
			final FileInputStream is = new FileInputStream(inputFile);
			final byte[] content = new byte[is.available()];
			is.read(content, 0, content.length);
			final FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(new String(content, inputEncoding).getBytes(outputEncoding));
			fos.close();
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	/**
	 * Treats the specified file with the specified string treater.
	 * 
	 * @param file
	 * @param treater
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public static final boolean treat(final File file, final Treater<String> treater) {
		return write(file, treater.treat(read(file)));
	}

}
