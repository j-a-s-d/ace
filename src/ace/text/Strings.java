/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;

/**
 * Utility class for working with strings.
 */
public class Strings extends Ace {
    
    public static final boolean hasContent(final String string) {
        return string != null && string.length() > 0;
    }
    
    @SuppressWarnings("PMD.InefficientEmptyStringCheck")
    public static final boolean hasText(final String string) {
        return string != null && string.trim().length() > 0;
    }
    
	public static final String ensure(final String string) {
        return ensure(string, "");
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
    
    public static int startOccurrences(final String s, final char c) {
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
    
}
