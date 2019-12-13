/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.CHARS;
import ace.constants.STRINGS;
import ace.text.Strings;
import java.io.File;

/**
 * Utility class for working with file names.
 */
public class FilenameUtils extends Ace {

	/**
	 * Replaces non valid characters in file systems from the specified string with underscore characters (_).
	 * 
	 * @param name
	 * @return the resulting string
	 */
	public static final String sanitizeFilename(final String name) {
		return assigned(name) ? name.replaceAll("[ \n\r\t\0\f`:\\\\/*\"?|<>]", STRINGS.UNDERSCORE) : null;
	}

	/**
	 * Removes the extension from the specified file name.
	 * 
	 * @param filename
	 * @return the resulting string
	 */
	public static final String stripExtension(final String filename) {
		if (!Strings.hasText(filename)) {
			return filename;
		}
		final int x = filename.lastIndexOf(CHARS.PERIOD);
		return x == -1 ? filename : filename.substring(0, x);
	}

	/**
	 * Replaces the extension from the specified file name with the specified extension.
	 * 
	 * @param filename
	 * @param newExtension
	 * @return the resulting string
	 */
	public static final String replaceExtension(final String filename, final String newExtension) {
		if (!Strings.hasText(filename) || !Strings.hasText(newExtension)) {
			return filename;
		}
		return stripExtension(filename) + (newExtension.charAt(0) == CHARS.PERIOD ? newExtension : STRINGS.PERIOD + newExtension);
	}

	/**
	 * Extracts the extension (including the period) from the specified file name.
	 * 
	 * @param filename
	 * @return the resulting string containing the extension
	 */
	public static final String extractExtension(final String filename) {
		if (!Strings.hasText(filename)) {
			return filename;
		}
		final int x = filename.lastIndexOf(CHARS.PERIOD);
		return x > 0 ? filename.substring(x) : STRINGS.EMPTY;
	}

	/**
	 * Extracts the name from the specified file name.
	 * 
	 * @param filename
	 * @return the resulting string containing the name
	 */
	public static final String extractFilename(final String filename) {
		return extractFilename(filename, File.separator);
	}

	/**
	 * Extracts the name from the specified file name using the specified file separator.
	 * 
	 * @param filename
	 * @param fileSeparator
	 * @return the resulting string containing the name
	 */
	public static final String extractFilename(final String filename, final String fileSeparator) {
		if (!Strings.hasText(filename) || filename.lastIndexOf(fileSeparator) == -1) {
			return filename;
		}
		return filename.substring(filename.lastIndexOf(fileSeparator) + 1);
	}

	/**
	 * Extracts the name (without extension) from the specified file name.
	 * 
	 * @param filename
	 * @return the resulting string containing the name
	 */
	public static final String extractFilenameWithoutExtension(final String filename) {
		return stripExtension(extractFilename(filename, File.separator));
	}

	/**
	 * Ensures the directory separator in the last position.
	 * 
	 * @param filename
	 * @return the resulting string
	 */
	public static final String ensureLastDirectorySeparator(final String filename) {
		return ensureLastDirectorySeparator(filename, File.separator);
	}

	/**
	 * Ensures the directory separator in the last position using the specified file separator.
	 * 
	 * @param filename
	 * @param fileSeparator
	 * @return the resulting string
	 */
	public static final String ensureLastDirectorySeparator(final String filename, final String fileSeparator) {
		if (!Strings.hasText(filename)) {
			return filename;
		}
		return filename.endsWith(fileSeparator) ? filename : filename + fileSeparator;
	}

}
