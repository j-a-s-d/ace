/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.html;

import ace.Ace;
import ace.constants.STRINGS;
import ace.text.Strings;

/**
 * Utility class to parse HTML shorthand notation and make HTML elements with it.
 */
public class Shorthand extends Ace {

	/**
	 * This class holds the resulting information of the shorthand notation parsing
	 * containing the tag, the id and the classes included in it.
	 */
	public static class ShorthandInfo {

		public String tag = "div";
		public String id = STRINGS.EMPTY;
		public String[] classes = new String[] {};

	}

	/**
	 * Parses the supplied html shorthand notation string
	 * storing the resulting information in a ShorthandInfo instance.
	 * 
	 * @param shorthandNotation
	 * @return a ShorthandInfo instance
	 */
	@SuppressWarnings("PMD.AvoidArrayLoops")
	public static ShorthandInfo parse(final String shorthandNotation) {
		if (!Strings.hasContent(shorthandNotation)) {
			return null;
		}
		final ShorthandInfo result = new ShorthandInfo();
		final String[] parts = shorthandNotation.split("\\.");
		if (parts.length > 0) {
			result.classes = new String[parts.length - 1];
			for (int i = 1; i < parts.length; i++) {
				result.classes[i - 1] = parts[i];
			}
		}
		final String a = parts[0];
		if (Strings.hasContent(a)) {
			if (!a.contains(STRINGS.NUMERAL)) {
				result.tag = a;
			} else {
				final int n = a.indexOf(STRINGS.NUMERAL);
				if (n > 0) {
					result.tag = a.substring(0, n);
				}
				result.id = a.substring(n + 1);
			}
		}
		return result;
	}

	/**
	 * Compiles the provided shorthand notation string to an html element.
	 * 
	 * @param shorthandNotation
	 * @return the resulting html element string
	 */
	public static String make(final String shorthandNotation) {
		return make(shorthandNotation, null);
	}

	/**
	 * Compiles the provided shorthand notation string to an html element
	 * containing the supplied content.
	 * 
	 * @param shorthandNotation
	 * @param content
	 * @return the resulting html element string
	 */
	public static String make(final String shorthandNotation, final String content) {
		final ShorthandInfo parsed = parse(shorthandNotation);
		final StringBuilder sb = new StringBuilder(STRINGS.MINOR).append(parsed.tag);
		if (Strings.hasText(parsed.id)) {
			sb.append(" id='").append(parsed.id).append(STRINGS.APOSTROPHE);
		}
		if (parsed.classes.length > 0) {
			sb.append(" class='");
			for (int i = 0; i < parsed.classes.length; i++) {
				if (i > 0) {
					sb.append(STRINGS.SPACE);
				}
				sb.append(parsed.classes[i]);
			}
			sb.append(STRINGS.APOSTROPHE);
		}
		if (assigned(content)) {
			sb.append(STRINGS.MAJOR);
			if (Strings.hasContent(content)) {
				sb.append(content);
			}
			sb.append(STRINGS.MINOR).append(STRINGS.SLASH).append(parsed.tag);
		} else {
			sb.append(STRINGS.SLASH);
		}
		return sb.append(STRINGS.MAJOR).toString();
	}

}
