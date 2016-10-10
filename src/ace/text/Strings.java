/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;

/**
 * Utility class for working with strings.
 */
public class Strings extends Ace {

	// COMPOSITION
	public static final String make(final int length) {
		return new String(new char[length]);
	}

	public static final String make(final int length, final char value) {
		return new String(new char[length]).replace('\0', value);
	}

	public static final String make(final int length, final String value) {
		return new String(new char[length]).replace("\0", value);
	}

	public static String fromByteArray(final byte[] buffer) {
		return buffer != null ? new String(buffer) : null;
	}

	public static String fromByteArray(final byte[] buffer, final String charset) {
		try {
			return buffer != null ? new String(buffer, charset) : null;
		} catch (final Exception e) {
			return null;
		}
	}

	public static String fromByteArray(final byte[] buffer, final int offset, final int length) {
		return buffer != null && offset > -1 && length > -1 && buffer.length > offset + length ? new String(buffer, offset, length) : null;
	}

	public static String fromByteArray(final byte[] buffer, final int offset, final int length, final String charset) {
		try {
			return buffer != null && offset > -1 && length > -1 && buffer.length > offset + length ? new String(buffer, offset, length, charset) : null;
		} catch (final Exception e) {
			return null;
		}
	}

	public static byte[] toByteArray(final String string) {
		return string != null ? string.getBytes() : null;
	}

	public static byte[] toByteArray(final String string, final String charset) {
		try {
			return string != null ? string.getBytes(charset) : null;
		} catch (final Exception e) {
			return null;
		}
	}

	public static final String ensure(final String string) {
		return ensure(string, STRINGS.EMPTY);
	}

	public static final String concat(final String... values) {
		final StringBuilder sb = new StringBuilder();
		for (final String value : values) {
			if (value != null) {
				sb.append(value);
			}
		}
		return sb.toString();
	}

	// CONTENT
	public static final boolean hasContent(final String string) {
		return string != null && string.length() > 0;
	}

	public static final boolean haveContent(final String... strings) {
		boolean result = strings.length > 0;
		for (final String string : strings) {
			result &= string != null && string.length() > 0;
		}
		return result;
	}

	public static final boolean sameContent(final String value1, final String value2) {
		return hasContent(value1) && value1.equals(value2);
	}

	public static final boolean areEqual(final String value1, final String value2) {
		return value1 == null ? value2 == null : value1.equals(value2);
	}

	@SuppressWarnings("PMD.InefficientEmptyStringCheck")
	public static final boolean hasText(final String string) {
		return string != null && string.trim().length() > 0;
	}

	public static final boolean haveText(final String... strings) {
		boolean result = strings.length > 0;
		for (final String string : strings) {
			result &= string != null && string.trim().length() > 0;
		}
		return result;
	}

	public static final boolean in(final String string, final String... values) {
		if (assigned(string)) {
			for (final String value : values) {
				if (string.equals(value)) {
					return true;
				}
			}
		} else {
			for (final String value : values) {
				if (!assigned(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public static final int occurs(final String string, final char character) {
		int count = 0;
		int lastIndex = -1;
		while ((lastIndex = string.indexOf(character, ++lastIndex)) != -1) {
			count++;
		}
		return count;
	}

	public static final int occurs(final String string, final String value) {
		int count = 0;
		if (hasContent(value)) {
			int lastIndex = -1;
			while ((lastIndex = string.indexOf(value, ++lastIndex)) != -1) {
				count++;
			}
		}
		return count;
	}

	public static final int leftOccurrences(final String s, final char c) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public static final int rightOccurrences(final String s, final char c) {
		int count = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == c) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public static final boolean startsWithIgnoreCase(final String string, final String prefix) {
		return string == null || prefix == null || string.length() < prefix.length() ? false : string.toLowerCase().startsWith(prefix.toLowerCase());
	}

	public static final boolean endsWithIgnoreCase(final String string, final String suffix) {
		return string == null || suffix == null || string.length() < suffix.length() ? false : string.toLowerCase().endsWith(suffix.toLowerCase());
	}

	public static final String padLeft(final int length, final char value, final String string) {
		final int delta = length - string.length();
		return delta > 0 ? concat(make(delta, value), string) : string;
	}

	public static final String padRight(final int length, final char value, final String string) {
		final int delta = length - string.length();
		return delta > 0 ? concat(string, make(delta, value)) : string;
	}

	public static final String dropLeft(final String string, final int amount) {
		return amount > -1 && string.length() > amount ? string.substring(amount) : null;
	}

	public static final String dropRight(final String string, final int amount) {
		return amount > -1 && string.length() > amount ? string.substring(0, string.length() - amount) : null;
	}

	public static final String dropBoth(final String string, final int amount) {
		return amount > -1 && string.length() > amount * 2 ? string.substring(amount, string.length() - amount) : null;
	}

	public static final String stripLeft(final String string, final char chr) {
		String result = string;
		final String c = STRINGS.EMPTY + chr;
		while (result.startsWith(c)) {
			result = result.substring(1);
		}
		return result;
	}

	public static final String stripRight(final String string, final char chr) {
		String result = string;
		final String c = STRINGS.EMPTY + chr;
		while (result.endsWith(c)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public static final String stripBoth(final String string, final char chr) {
		if (!hasContent(string)) {
			return string;
		}
		int beginIndex = 0;
		while (beginIndex < string.length() && string.charAt(beginIndex) == chr) {
			beginIndex++;
		}
		if (beginIndex == string.length()) {
			return STRINGS.EMPTY;
		}
		int endIndex = string.length() - 1;
		while (endIndex > beginIndex && string.charAt(endIndex) == chr) {
			endIndex--;
		}
		return string.substring(beginIndex, endIndex + 1);
	}

}
