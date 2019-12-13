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
	
	/**
	 * Makes a string of the specified length.
	 * 
	 * @param length
	 * @return a string of the specified length
	 */
	public static final String make(final int length) {
		return new String(new char[length]);
	}

	/**
	 * Makes a string of the specified length with the char value repeated all over it.
	 * 
	 * @param length
	 * @param value
	 * @return a string of the specified length with the char value repeated all over it
	 */
	public static final String make(final int length, final char value) {
		return new String(new char[length]).replace('\0', value);
	}

	/**
	 * Makes a string of the specified length with the string value repeated all over it.
	 * 
	 * @param length
	 * @param value
	 * @return a string of the specified length with the string value repeated all over it
	 */
	public static final String make(final int length, final String value) {
		return new String(new char[length]).replace("\0", value);
	}

	/**
	 * Makes a string with the specified byte array as content.
	 * 
	 * @param buffer
	 * @return a string with the specified byte array as content
	 */
	public static String fromByteArray(final byte[] buffer) {
		return buffer != null ? new String(buffer) : null;
	}

	/**
	 * Makes a string with the specified byte array as content and using the specified character set.
	 * 
	 * @param buffer
	 * @param charset
	 * @return a string with the specified byte array as content and using the specified character set
	 */
	public static String fromByteArray(final byte[] buffer, final String charset) {
		try {
			return buffer != null ? new String(buffer, charset) : null;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Makes a string with the specified segment of the specified byte array as content.
	 * 
	 * @param buffer
	 * @param offset
	 * @param length
	 * @return a string with the specified segment of the specified byte array as content
	 */
	public static String fromByteArray(final byte[] buffer, final int offset, final int length) {
		return buffer != null && offset > -1 && length > -1 && buffer.length >= offset + length ? new String(buffer, offset, length) : null;
	}

	/**
	 * Makes a string with the specified segment of the specified byte array as content and using the specified character set.
	 * 
	 * @param buffer
	 * @param offset
	 * @param length
	 * @param charset
	 * @return a string with the specified segment of the specified byte array as content and using the specified character set
	 */
	public static String fromByteArray(final byte[] buffer, final int offset, final int length, final String charset) {
		try {
			return buffer != null && offset > -1 && length > -1 && buffer.length >= offset + length ? new String(buffer, offset, length, charset) : null;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Makes a string with the specified range of the specified byte array as content.
	 * 
	 * @param buffer
	 * @param from
	 * @param to
	 * @return a string with the specified range of the specified byte array as content
	 */
	public static String fromByteArrayRange(final byte[] buffer, final int from, final int to) {
		final int size = to - from + 1;
		if (buffer == null || from < 0 || size < 0 || to >= buffer.length) {
			return null;
		}
		final byte[] copy = new byte[size];
		System.arraycopy(buffer, from, copy, 0, size);
		return new String(copy);
	}

	/**
	 * Makes a string with the specified range of the specified byte array as content and using the specified character set.
	 * 
	 * @param buffer
	 * @param from
	 * @param to
	 * @param charset
	 * @return a string with the specified range of the specified byte array as content and using the specified character set
	 */
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

	/**
	 * Gets the specified string content as a byte array.
	 * 
	 * @param string
	 * @return the specified string content as a byte array
	 */
	public static byte[] toByteArray(final String string) {
		return string != null ? string.getBytes() : null;
	}

	/**
	 * Gets the specified string content as a byte array using the specified character set.
	 * 
	 * @param string
	 * @param charset
	 * @return the specified string content as a byte array using the specified character set
	 */
	public static byte[] toByteArray(final String string, final String charset) {
		try {
			return string != null ? string.getBytes(charset) : null;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return null;
		}
	}

	/**
	 * Makes a string with the specified input stream as content.
	 * 
	 * @param is
	 * @return a string with the specified input stream as content
	 */
	public static String fromInputStream(final InputStream is) {
		if (is != null) {
			final Scanner s = new Scanner(is).useDelimiter("\\A");
			return s.hasNext() ? s.next() : STRINGS.EMPTY;
		}
		return null;
	}

	/**
	 * Gets the specified string content as an input stream.
	 * 
	 * @param string
	 * @return the specified string content as an input stream
	 */
	public static InputStream toInputStream(final String string) {
		return string != null ? new ByteArrayInputStream(string.getBytes()) : null;
	}

	/**
	 * Ensures a string.
	 * 
	 * @param string
	 * @return the specified string if it is assigned (not null) or an empty string otherwise
	 */
	public static final String ensure(final String string) {
		return ensure(string, STRINGS.EMPTY);
	}

	/**
	 * Concatenates the specified string values.
	 * 
	 * @param values
	 * @return the resulting string
	 */
	public static final String concat(final String... values) {
		final StringBuilder sb = new StringBuilder();
		for (final String value : values) {
			if (value != null) {
				sb.append(value);
			}
		}
		return sb.toString();
	}

	/**
	 * Encloses the specified string between the specified char.
	 * 
	 * @param string
	 * @param chr
	 * @return the resulting string
	 */
	public static String enclose(final String string, final char chr) {
		return concat(STRINGS.EMPTY + chr, string, STRINGS.EMPTY + chr);
	}

	/**
	 * Encloses the specified string between the specified borders string.
	 * 
	 * @param string
	 * @param borders
	 * @return the resulting string
	 */
	public static String enclose(final String string, final String borders) {
		return concat(borders, string, borders);
	}

	/**
	 * Encloses the specified string between apostrophes.
	 * 
	 * @param string
	 * @return the resulting string
	 */
	public static String apostrophe(final String string) {
		return enclose(string, STRINGS.APOSTROPHE);
	}

	/**
	 * Encloses the specified string between quotes.
	 * 
	 * @param string
	 * @return the resulting string
	 */
	public static String quote(final String string) {
		return enclose(string, STRINGS.QUOTE);
	}

	/**
	 * Encloses the specified string between braces.
	 * 
	 * @param string
	 * @return the resulting string
	 */
	public static String brace(final String string) {
		return concat(STRINGS.BRACES_OPEN, string, STRINGS.BRACES_CLOSE);
	}

	/**
	 * Encloses the specified string between brackets.
	 * 
	 * @param string
	 * @return the resulting string
	 */
	public static String bracketize(final String string) {
		return concat(STRINGS.BRACKETS_OPEN, string, STRINGS.BRACKETS_CLOSE);
	}

	/**
	 * Encloses the specified string between parenthesis.
	 * 
	 * @param string
	 * @return the resulting string
	 */
	public static String parenthesize(final String string) {
		return concat(STRINGS.PARENTHESES_OPEN, string, STRINGS.PARENTHESES_CLOSE);
	}

	/**
	 * Encloses the specified string between chevrons.
	 * 
	 * @param string
	 * @return the resulting string
	 */
	public static String chevronize(final String string) {
		return concat(STRINGS.MINOR, string, STRINGS.MAJOR);
	}

	// CONTENT

	/**
	 * Determines if the specified string is not empty.
	 * 
	 * @param string
	 * @return <tt>true</tt> if the specified string is not empty, <tt>false</tt> otherwise
	 */
	public static final boolean hasContent(final String string) {
		return string != null && string.length() > 0;
	}

	/**
	 * Determines if the specified strings are not empty.
	 * 
	 * @param strings
	 * @return <tt>true</tt> if the specified strings are not empty, <tt>false</tt> otherwise
	 */
	public static final boolean haveContent(final String... strings) {
		boolean result = strings.length > 0;
		for (final String string : strings) {
			result &= string != null && string.length() > 0;
		}
		return result;
	}

	/**
	 * Determines if the specified strings have the same content.
	 * 
	 * Note: both string must be assigned (not null).
	 * 
	 * @param value1
	 * @param value2
	 * @return <tt>true</tt> if the specified strings have the same content, <tt>false</tt> otherwise
	 */
	public static final boolean sameContent(final String value1, final String value2) {
		return hasContent(value1) && value1.equals(value2);
	}

	/**
	 * Determines if the specified strings are equal.
	 * 
	 * Note: if both strings are null it returns <tt>true</tt>.
	 * 
	 * @param value1
	 * @param value2
	 * @return <tt>true</tt> if the specified strings are equal, <tt>false</tt> otherwise
	 */
	public static final boolean areEqual(final String value1, final String value2) {
		return value1 == null ? value2 == null : value1.equals(value2);
	}

	/**
	 * Determines if the specified string has text.
	 * 
	 * Note: white space is ignored.
	 * 
	 * @param string
	 * @return <tt>true</tt> if the specified string has text, <tt>false</tt> otherwise
	 */
	public static final boolean hasText(final String string) {
		return string != null && !string.trim().equals(STRINGS.EMPTY);
	}

	/**
	 * Determines if the specified strings have text.
	 * 
	 * Note: white space is ignored.
	 * 
	 * @param strings
	 * @return <tt>true</tt> if the specified strings have text, <tt>false</tt> otherwise
	 */
	public static final boolean haveText(final String... strings) {
		boolean result = strings.length > 0;
		for (final String string : strings) {
			result &= string != null && !string.trim().equals(STRINGS.EMPTY);
		}
		return result;
	}

	/**
	 * Determines if the specified string is present among the specified strings.
	 * 
	 * @param string
	 * @param values
	 * @return <tt>true</tt> if the specified string is present among the specified strings, <tt>false</tt> otherwise
	 */
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

	/**
	 * Retrieves the amount of occurrences of the specified character in the specified string.
	 * 
	 * @param string
	 * @param character
	 * @return the amount of occurrences of the specified character in the specified string
	 */
	public static final int occurs(final String string, final char character) {
		int count = 0;
		int lastIndex = -1;
		while ((lastIndex = string.indexOf(character, ++lastIndex)) != -1) {
			count++;
		}
		return count;
	}

	/**
	 * Retrieves the amount of occurrences of the second specified string in the first specified string.
	 * 
	 * @param string
	 * @param value
	 * @return the amount of occurrences of the second specified string in the first specified string
	 */
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

	/**
	 * Retrieves the amount of occurrences of the specified character in the left side (beginning) of the specified string.
	 * 
	 * @param s
	 * @param c
	 * @return the amount of occurrences of the specified character in the left side (beginning) of the specified string
	 */
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

	/**
	 * Retrieves the amount of occurrences of the specified character in the right side (ending) of the specified string.
	 * 
	 * @param s
	 * @param c
	 * @return the amount of occurrences of the specified character in the right side (ending) of the specified string
	 */
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

	/**
	 * Determines if the specified string starts with the specified prefix ignoring the case.
	 * 
	 * @param string
	 * @param prefix
	 * @return <tt>true</tt> if the specified string starts with the specified prefix ignoring the case, <tt>false</tt> otherwise
	 */
	public static final boolean startsWithIgnoreCase(final String string, final String prefix) {
		return string == null || prefix == null || string.length() < prefix.length() ? false : string.toLowerCase().startsWith(prefix.toLowerCase());
	}

	/**
	 * Determines if the specified string ends with the specified suffix ignoring the case.
	 * 
	 * @param string
	 * @param suffix
	 * @return <tt>true</tt> if the specified string ends with the specified suffix ignoring the case, <tt>false</tt> otherwise
	 */
	public static final boolean endsWithIgnoreCase(final String string, final String suffix) {
		return string == null || suffix == null || string.length() < suffix.length() ? false : string.toLowerCase().endsWith(suffix.toLowerCase());
	}

	/**
	 * Pads the left side (beginning) of the specified string with the specified char value up to the specified length.
	 * 
	 * @param length
	 * @param value
	 * @param string
	 * @return the resulting string
	 */
	public static final String padLeft(final int length, final char value, final String string) {
		final int delta = length - string.length();
		return delta > 0 ? concat(make(delta, value), string) : string;
	}

	/**
	 * Pads the right side (ending) of the specified string with the specified char value up to the specified length.
	 * 
	 * @param length
	 * @param value
	 * @param string
	 * @return the resulting string
	 */
	public static final String padRight(final int length, final char value, final String string) {
		final int delta = length - string.length();
		return delta > 0 ? concat(string, make(delta, value)) : string;
	}

	/**
	 * Drops the specified amount of characters from the left side (beginning) of the specified string.
	 * 
	 * @param string
	 * @param amount
	 * @return the resulting string
	 */
	public static final String dropLeft(final String string, final int amount) {
		return amount > -1 && string.length() > amount ? string.substring(amount) : null;
	}

	/**
	 * Drops the specified amount of characters from the right side (ending) of the specified string.
	 * 
	 * @param string
	 * @param amount
	 * @return the resulting string
	 */
	public static final String dropRight(final String string, final int amount) {
		return amount > -1 && string.length() > amount ? string.substring(0, string.length() - amount) : null;
	}

	/**
	 * Drops the specified amount of characters from the left side (beginning) and the right side (ending) of the specified string.
	 * 
	 * @param string
	 * @param amount
	 * @return the resulting string
	 */
	public static final String dropBoth(final String string, final int amount) {
		return amount > -1 && string.length() > amount * 2 ? string.substring(amount, string.length() - amount) : null;
	}

	/**
	 * Removes the specified character from the left side (beginning) of the specified string.
	 * 
	 * @param string
	 * @param chr
	 * @return the resulting string
	 */
	public static final String stripLeft(final String string, final char chr) {
		String result = string;
		if (result != null) {
			while (result.length() > 0 && result.charAt(0) == chr) {
				result = result.substring(1);
			}
		}
		return result;
	}

	/**
	 * Removes the specified character from the right side (ending) of the specified string.
	 * 
	 * @param string
	 * @param chr
	 * @return the resulting string
	 */
	public static final String stripRight(final String string, final char chr) {
		String result = string;
		if (result != null) {
			while (result.length() > 0 && result.charAt(result.length() - 1) == chr) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	/**
	 * Removes the specified character from the left side (beginning) and the right side (ending) of the specified string.
	 * 
	 * @param string
	 * @param chr
	 * @return the resulting string
	 */
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

	/**
	 * Removes the matches of specified regular expressions from the specified string.
	 * 
	 * @param string
	 * @param regexes
	 * @return the resulting string
	 */
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

	/**
	 * Splits the text lines in the specified string.
	 * 
	 * @param string
	 * @return a list of strings
	 */
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

	/**
	 * Splits the specified string into chunks of the specified size.
	 * 
	 * @param string
	 * @param size
	 * @return a list of strings
	 */
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

	/**
	 * Joins the specified strings using the specified separator.
	 * 
	 * @param separator
	 * @param values
	 * @return the resulting string
	 */
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

	/**
	 * Joins the specified collection of strings using the specified separator.
	 * 
	 * @param separator
	 * @param collection
	 * @return the resulting string
	 */
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

	/**
	 * Replace the first occurrence of the second specified string with the third specified string in the first specified string.
	 * 
	 * @param string
	 * @param find
	 * @param replacement
	 * @return the resulting string
	 */
	public static String replaceOnce(final String string, final String find, final String replacement) {
		if (string == null || find == null || replacement == null) {
			return null;
		}
		final char[] a = string.toCharArray();
		int i = string.indexOf(find, 0);
		final StringBuilder buf = new StringBuilder(a.length).append(a, 0, i).append(replacement.toCharArray());
		i += find.length();
		return buf.append(a, i, a.length - i).toString();
	}

	/**
	 * Replace all the occurrences of the second specified string with the third specified string in the first specified string.
	 * 
	 * @param string
	 * @param find
	 * @param replacement
	 * @return the resulting string
	 */
	public static String replaceAll(final String string, final String find, final String replacement) {
		if (string == null || find == null || replacement == null) {
			return null;
		}
		int i = string.indexOf(find, 0);
		if (i < 0) {
			return string;
		}
		final int l = find.length();
		final char[] a = string.toCharArray();
		final char[] r = replacement.toCharArray();
		final StringBuilder b = new StringBuilder(a.length).append(a, 0, i).append(r);
		int j = (i += l);
		while ((i = string.indexOf(find, i)) > 0) {
			b.append(a, j, i - j).append(r);
			j = (i += l);
		}
		return b.append(a, j, a.length - j).toString();
	}

	/**
	 * Determines if the specified string is alphanumeric.
	 * 
	 * @param string
	 * @return <tt>true</tt> if the specified string is alphanumeric, <tt>false</tt> otherwise
	 */
	public static boolean isAlphanumeric(final String string) {
		return string != null && string.matches("[A-Za-z0-9]+");
	}

	/**
	 * Determines if the specified string is numeric.
	 * 
	 * @param string
	 * @return <tt>true</tt> if the specified string is numeric, <tt>false</tt> otherwise
	 */
	public static boolean isNumeric(final String string) {
		return string != null && string.matches("-?\\d+(\\.\\d+)?");
	}

}
