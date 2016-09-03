/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Constants class for strings.
 */
public class STRINGS {
    
    public static final String EMPTY = "";
    public static final String EOL = new StringWriter(4) {{
        final PrintWriter pw = new PrintWriter(this);
        try {
            // NOTE: emulates Java 1.7+ System.lineSeparator
            pw.print(System.getProperty("line.separator"));
        } catch (final Exception e) {
            // NOTE: avoiding security issues
            pw.println();
        }
        pw.flush();
    }}.toString();
    public static final String TAB = "\t";
    public static final String LF = "\n";
    public static final String CR = "\r";
    public static final String SPACE = " ";
    public static final String EQUAL = "=";
    public static final String NUMERAL = "#";
    public static final String PIPE = "|";
    public static final String MINUS = "-";
    public static final String PLUS = "+";
    public static final String PERCENT = "%";
    public static final String DOLLAR = "$";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String AMPERSAND = "&";
    public static final String ASTERISK = "*";
    public static final String UNDERSCORE = "_";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String COMMA = ",";
    public static final String PERIOD = ".";
    public static final String APOSTROPHE = "'";
    public static final String QUOTE = "\"";
    public static final String CARET = "^";
    public static final String TILDE = "~";
    public static final String AT = "@";
    public static final String EXCLAMATION = "!";
    public static final String QUESTION = "?";
    public static final String INVERTED_EXCLAMATION = "¡";
    public static final String INVERTED_QUESTION = "¿";
    public static final String DEGREE = "°";
    public static final String COPYRIGHT = "©";
    public static final String REGISTERED = "®";
    public static final String TRADEMARK = "™";
    public static final String BULLET = "•";
    public static final String PILCROW = "¶";
    public static final String SECTION = "§";
    public static final String MINOR = "<";
    public static final String MAJOR = ">";
    
}
