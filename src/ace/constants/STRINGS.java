/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.constants;

import ace.Ace;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Constants class for strings.
 */
public class STRINGS extends Ace {

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

	// SYMBOLS
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

	// NUMBERS
	public static final String NUMBER_ZERO = "0";
	public static final String NUMBER_ONE = "1";
	public static final String NUMBER_TWO = "2";
	public static final String NUMBER_THREE = "3";
	public static final String NUMBER_FOUR = "4";
	public static final String NUMBER_FIVE = "5";
	public static final String NUMBER_SIX = "6";
	public static final String NUMBER_SEVEN = "7";
	public static final String NUMBER_EIGHT = "8";
	public static final String NUMBER_NINE = "9";

	// LETTERS
	public static final String UPPERCASE_A = "A";
	public static final String UPPERCASE_B = "B";
	public static final String UPPERCASE_C = "C";
	public static final String UPPERCASE_D = "D";
	public static final String UPPERCASE_E = "E";
	public static final String UPPERCASE_F = "F";
	public static final String UPPERCASE_G = "G";
	public static final String UPPERCASE_H = "H";
	public static final String UPPERCASE_I = "I";
	public static final String UPPERCASE_J = "J";
	public static final String UPPERCASE_K = "K";
	public static final String UPPERCASE_L = "L";
	public static final String UPPERCASE_M = "M";
	public static final String UPPERCASE_N = "N";
	public static final String UPPERCASE_O = "O";
	public static final String UPPERCASE_P = "P";
	public static final String UPPERCASE_Q = "Q";
	public static final String UPPERCASE_R = "R";
	public static final String UPPERCASE_S = "S";
	public static final String UPPERCASE_T = "T";
	public static final String UPPERCASE_U = "U";
	public static final String UPPERCASE_V = "V";
	public static final String UPPERCASE_W = "W";
	public static final String UPPERCASE_X = "X";
	public static final String UPPERCASE_Y = "Y";
	public static final String UPPERCASE_Z = "Z";

	public static final String LOWERCASE_A = "a";
	public static final String LOWERCASE_B = "b";
	public static final String LOWERCASE_C = "c";
	public static final String LOWERCASE_D = "d";
	public static final String LOWERCASE_E = "e";
	public static final String LOWERCASE_F = "f";
	public static final String LOWERCASE_G = "g";
	public static final String LOWERCASE_H = "h";
	public static final String LOWERCASE_I = "i";
	public static final String LOWERCASE_J = "j";
	public static final String LOWERCASE_K = "k";
	public static final String LOWERCASE_L = "l";
	public static final String LOWERCASE_M = "m";
	public static final String LOWERCASE_N = "n";
	public static final String LOWERCASE_O = "o";
	public static final String LOWERCASE_P = "p";
	public static final String LOWERCASE_Q = "q";
	public static final String LOWERCASE_R = "r";
	public static final String LOWERCASE_S = "s";
	public static final String LOWERCASE_T = "t";
	public static final String LOWERCASE_U = "u";
	public static final String LOWERCASE_V = "v";
	public static final String LOWERCASE_W = "w";
	public static final String LOWERCASE_X = "x";
	public static final String LOWERCASE_Y = "y";
	public static final String LOWERCASE_Z = "z";

}
