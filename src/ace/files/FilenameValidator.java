/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import java.util.regex.Pattern;

/**
 * Useful file name validation class.
 */
public class FilenameValidator extends Ace {

	/**
	 * Creates a file name validator that matches all files (*.*)
	 * 
	 * @return the resulting file name validator
	 */
	public static final FilenameValidator makeAllFilesValidator() {
		return new FilenameValidator("^.*\\.*$");
	}

	private final Pattern _pattern;

	/**
	 * Constructor accepting a file name regex pattern.
	 * 
	 * @param filePattern 
	 */
	public FilenameValidator(final String filePattern) {
		_pattern = Pattern.compile(filePattern);
	}

	/**
	 * Determines if the specified file name matches the validation pattern.
	 * 
	 * @param fileName
	 * @return <tt>true</tt> if the specified file name matches the validation pattern, <tt>false</tt> otherwise
	 */
	public boolean validate(final String fileName) {
		return _pattern.matcher(fileName).matches();
	}

	/**
	 * Creates a file name validator that matches the specified file name regex pattern.
	 * 
	 * @param extensions
	 * @return the resulting file name validator
	 */
	@SuppressWarnings("PMD.SimplifyStartsWith")
	public static FilenameValidator makeExtensionsValidator(final String... extensions) {
		if (extensions == null || extensions.length == 0) {
			return makeAllFilesValidator();
		}
		final StringBuilder sb = new StringBuilder("^.*\\.(");
		boolean first = true;
		for (String extension : extensions) {
			if (first) {
				first = false;
			} else {
				sb.append(STRINGS.PIPE);
			}
			if (extension.startsWith(STRINGS.PERIOD)) {
				extension = extension.substring(1);
			}
			sb.append(extension.toLowerCase());
			sb.append(STRINGS.PIPE);
			sb.append(extension.toUpperCase());
		}
		sb.append(")$");
		return new FilenameValidator(sb.toString());
	}

}
