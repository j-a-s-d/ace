/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;
import ace.constants.STRINGS;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
			GEH.setLastException(e);
			return null;
		}
	}

	public static String fromByteArray(final byte[] buffer, final int offset, final int length) {
		return buffer != null && offset > -1 && length > -1 && buffer.length >= offset + length ? new String(buffer, offset, length) : null;
	}

	public static String fromByteArray(final byte[] buffer, final int offset, final int length, final String charset) {
		try {
			return buffer != null && offset > -1 && length > -1 && buffer.length >= offset + length ? new String(buffer, offset, length, charset) : null;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	public static String fromByteArrayRange(final byte[] buffer, final int from, final int to) {
		final int size = to - from + 1;
		if (buffer == null || from < 0 || size < 0 || to >= buffer.length) {
			return null;
		}
		final byte[] copy = new byte[size];
		System.arraycopy(buffer, from, copy, 0, size);
		return new String(copy);
	}

	public static String fromByteArrayRange(final byte[] buffer, final int from, final int to, final String charset) {
		try {
			final int size = to - from + 1;
			if (buffer == null || from < 0 || size < 0 || to >= buffer.length) {
				return null;
			}
			final byte[] copy = new byte[size];
			System.arraycopy(buffer, from, copy, 0, size);
			return new String(copy, charset);
		} catch (final Exception e) {
			GEH.setLastException(e);
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
			GEH.setLastException(e);
			return null;
		}
	}

	public static String fromInputStream(final InputStream is) {
		if (is != null) {
			final Scanner s = new Scanner(is).useDelimiter("\\A");
			return s.hasNext() ? s.next() : STRINGS.EMPTY;
		}
		return null;
	}

	public static InputStream toInputStream(final String string) {
		return string != null ? new ByteArrayInputStream(string.getBytes()) : null;
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

	public static String enclose(final String string, final char chr) {
		return concat(STRINGS.EMPTY + chr, string, STRINGS.EMPTY + chr);
	}

	public static String enclose(final String string, final String borders) {
		return concat(borders, string, borders);
	}

	public static String apostrophe(final String string) {
		return enclose(string, STRINGS.APOSTROPHE);
	}

	public static String quote(final String string) {
		return enclose(string, STRINGS.QUOTE);
	}

	public static String brace(final String string) {
		return concat("{", string, "}");
	}

	public static String bracketize(final String string) {
		return concat("[", string, "]");
	}

	public static String parenthesize(final String string) {
		return concat("(", string, ")");
	}

	public static String chevronize(final String string) {
		return concat("<", string, ">");
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

	@SuppressWarnings("PMD.InefficientEmptyStringCheck")
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

	public static String stripAll(final String string, final String... regexes) {
		String result = string;
		if (assigned(string, regexes)) {
			for (final String regex : regexes) {
				if (assigned(regex)) {
					result = result.replaceAll(regex, STRINGS.EMPTY);
				}
			}
		}
		return result;
	}

	public static List<String> splitLines(final String string) {
		List<String> result = null;
		if (assigned(string)) {
			result = new ArrayList();
			final Scanner scanner = new Scanner(string);
			while (scanner.hasNextLine()) {
				result.add(scanner.nextLine());
			}
			scanner.close();
		}
		return result;
	}

	public static List<String> splitEqually(final String string, final int size) {
		List<String> result = null;
		if (assigned(string) && size > 0) {
			result = new ArrayList<String>((string.length() + size - 1) / size);
			for (int start = 0; start < string.length(); start += size) {
				result.add(string.substring(start, Math.min(string.length(), start + size)));
			}
		}
		return result;
	}

	public static String join(final String separator, final String... values) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(values[i]);
		}
		return sb.toString();
	}

	public static String join(final String separator, final Collection<?> collection) {
		final StringBuilder sb = new StringBuilder();
		if (assigned(collection) && collection.size() > 0) {
			final Iterator<?> it = collection.iterator();
			while (it.hasNext()) {
				sb.append(it.next());
				if (!it.hasNext()) {
					break;
				}
				sb.append(separator);
			}
		}
		return sb.toString();
	}

}
