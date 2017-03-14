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

	public static final String read(final String filename) {
		return read(new File(filename), CHARSET);
	}

	public static final String read(final File file) {
		return read(file, CHARSET);
	}

	public static final String read(final String filename, final String charset) {
		return read(new File(filename), charset);
	}

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

	public static final boolean write(final String filename, final String text, final String charset) {
		return write (new File(filename), text, charset);
	}

	public static final boolean write(final File file, final String text, final String charset) {
		try {
			performWriting(new OutputStreamWriter(new FileOutputStream(file, false), charset), text);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	public static final boolean write(final String filename, final String text) {
		return write(new File(filename), text);
	}

	public static final boolean write(final File file, final String text) {
		return write(file, text, CHARSET);
	}

	public static final boolean append(final String filename, final String text) {
		return append(new File(filename), text, CHARSET);
	}

	public static final boolean append(final File file, final String text) {
		return append(file, text, CHARSET);
	}

	public static final boolean append(final String filename, final String text, final String charset) {
		return append(new File(filename), text, charset);
	}

	public static final boolean append(final File file, final String text, final String charset) {
		try {
			performWriting(new OutputStreamWriter(new FileOutputStream(file, true), charset), text);
			return true;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return false;
		}
	}

	public static final boolean transcode(final String inputFilename, final String inputEncoding, final String outputFilename, final String outputEncoding) {
		return transcode(new File(inputFilename), inputEncoding, new File(outputFilename), outputEncoding);
	}

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

	public static final boolean treat(final File file, final Treater<String> treater) {
		return write(file, treater.treat(read(file)));
	}

}
