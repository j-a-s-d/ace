/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.Ace;
import ace.constants.STRINGS;
import java.util.regex.Pattern;

/**
 * Useful file name validation class.
 */
public class FilenameValidator extends Ace {

	public static final FilenameValidator makeAllFilesValidator() {
		return new FilenameValidator("^.*\\.*$");
	}

	private final Pattern _pattern;

	public FilenameValidator(final String filePattern) {
		_pattern = Pattern.compile(filePattern);
	}

	public boolean validate(final String fileName) {
		return _pattern.matcher(fileName).matches();
	}

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
