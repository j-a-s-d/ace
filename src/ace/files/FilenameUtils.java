/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import ace.text.Strings;
import java.io.File;

/**
 * Utility class for working with file names.
 */
public class FilenameUtils extends Ace {

	private static final char DOT = '.';

	public static final String sanitizeFilename(final String name) {
		return assigned(name) ? name.replaceAll("[ \n\r\t\0\f`:\\\\/*\"?|<>]", "_") : null;
	}

	public static final String stripExtension(final String filename) {
		if (!Strings.hasText(filename)) {
			return filename;
		}
		final int x = filename.lastIndexOf(DOT);
		return x == -1 ? filename : filename.substring(0, x);
	}

	public static final String replaceExtension(final String filename, final String newExtension) {
		if (!Strings.hasText(filename) || !Strings.hasText(newExtension)) {
			return filename;
		}
		return stripExtension(filename) + (newExtension.charAt(0) == DOT ? newExtension : DOT + newExtension);
	}

	public static final String extractExtension(final String filename) {
		if (!Strings.hasText(filename)) {
			return filename;
		}
		final int x = filename.lastIndexOf(DOT);
		return x > 0 ? filename.substring(x) : STRINGS.EMPTY;
	}

	public static final String extractFilename(final String filename) {
		return extractFilename(filename, File.separator);
	}

	public static final String extractFilename(final String filename, final String fileSeparator) {
		if (!Strings.hasText(filename) || filename.lastIndexOf(fileSeparator) == -1) {
			return filename;
		}
		return filename.substring(filename.lastIndexOf(fileSeparator) + 1);
	}

	public static final String extractFilenameWithoutExtension(final String filename) {
		return stripExtension(extractFilename(filename, File.separator));
	}

	public static final String ensureLastDirectorySeparator(final String filename) {
		return ensureLastDirectorySeparator(filename, File.separator);
	}

	public static final String ensureLastDirectorySeparator(final String filename, final String fileSeparator) {
		if (!Strings.hasText(filename)) {
			return filename;
		}
		return filename.endsWith(fileSeparator) ? filename : filename + fileSeparator;
	}

}
