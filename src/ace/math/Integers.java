/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;
import ace.constants.NUMBERS;

/**
 * Utility class for mathematic operations with integers.
 */
public class Integers extends Ace {

	// UNBOXED

	/**
	 * Adds all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final int add(final int... numbers) {
		int result = NUMBERS.I0;
		for (final int number : numbers) {
			result += number;
		}
		return result;
	}

	/**
	 * Subtract all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final int subtract(final int... numbers) {
		int result = numbers.length < 2 ? NUMBERS.I0 : numbers[0] * 2;
		for (final int number : numbers) {
			result -= number;
		}
		return result;
	}

	/**
	 * Determines the maximum value from all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final int max(final int... numbers) {
		int result = Integer.MIN_VALUE;
		for (final int number : numbers) {
			result = Math.max(result, number);
		}
		return result;
	}

	/**
	 * Determines the minimum value from all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final int min(final int... numbers) {
		int result = Integer.MAX_VALUE;
		for (final int number : numbers) {
			result = Math.min(result, number);
		}
		return result;
	}

	/**
	 * Determines the specified number is even.
	 * 
	 * @param number
	 * @return <tt>true</tt> if the specified number is even, <tt>false</tt> otherwise
	 */
	public static final boolean isEven(final int number) {
		return (number & 1) == 0;
	}

	/**
	 * Determines the specified number is odd.
	 * 
	 * @param number
	 * @return <tt>true</tt> if the specified number is odd, <tt>false</tt> otherwise
	 */
	public static final boolean isOdd(final int number) {
		return (number & 1) != 0;
	}

	/**
	 * Determines if the specified number is divisible by the specified factor.
	 * 
	 * @param number
	 * @param factor
	 * @return <tt>true</tt> if the specified number is divisible by the specified factor, <tt>false</tt> otherwise
	 */
	public static final boolean isDivisibleBy(final int number, final int factor) {
		return number % factor == 0;
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final int parse(final String string, final int defaultValue) {
		try {
			return Integer.parseInt(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	/**
	 * Parses the specified string as a number in the specified radix using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param radix
	 * @param defaultValue
	 * @return the number parsed in the specified radix from the specified string or the default value if the parsing failed
	 */
	public static final int parse(final String string, final int radix, final int defaultValue) {
		try {
			return Integer.parseInt(string, radix);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	// BOXED

	/**
	 * Adds all the number instances specified.
	 * 
	 * It ignores the specified null values.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final Integer boxedAdd(final Integer... numbers) {
		Integer result = NUMBERS.I0;
		for (final Integer number : numbers) {
			if (number != null) {
				result += number;
			}
		}
		return result;
	}

	/**
	 * Subtract all the number instances specified.
	 * 
	 * It ignores the specified null values.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final Integer boxedSubtract(final Integer... numbers) {
		Integer result = null;
		if (numbers.length > 0) {
			result = numbers.length == 1 ? NUMBERS.I0 : numbers[0] * 2;
			for (final Integer number : numbers) {
				if (number != null) {
					result -= number;
				}
			}
		}
		return result;
	}

	/**
	 * Determines the maximum value from all the number instances specified.
	 * 
	 * It ignores the specified null values.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final Integer boxedMax(final Integer... numbers) {
		Integer result = Integer.MIN_VALUE;
		for (final Integer number : numbers) {
			if (number != null) {
				result = Math.max(result, number);
			}
		}
		return result;
	}

	/**
	 * Determines the minimum value from all the number instances specified.
	 * 
	 * It ignores the specified null values.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final Integer boxedMin(final Integer... numbers) {
		Integer result = Integer.MAX_VALUE;
		for (final Integer number : numbers) {
			if (number != null) {
				result = Math.min(result, number);
			}
		}
		return result;
	}

	/**
	 * Determines the specified number is even.
	 * 
	 * @param number
	 * @return <tt>true</tt> if the specified number is even, <tt>false</tt> otherwise
	 */
	public static final boolean boxedIsEven(final Integer number) {
		return assigned(number) && ((number & 1) == 0);
	}

	/**
	 * Determines the specified number is odd.
	 * 
	 * @param number
	 * @return <tt>true</tt> if the specified number is odd, <tt>false</tt> otherwise
	 */
	public static final boolean boxedIsOdd(final Integer number) {
		return assigned(number) && ((number & 1) != 0);
	}

	/**
	 * Determines if the specified number is divisible by the specified factor.
	 * 
	 * @param number
	 * @param factor
	 * @return <tt>true</tt> if the specified number is divisible by the specified factor, <tt>false</tt> otherwise
	 */
	public static final boolean boxedIsDivisibleBy(final Integer number, final Integer factor) {
		return assigned(number, factor) && (number % factor == 0);
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final Integer boxedParse(final String string, final Integer defaultValue) {
		try {
			return Integer.parseInt(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	/**
	 * Parses the specified string as a number in the specified radix using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param radix
	 * @param defaultValue
	 * @return the number parsed in the specified radix from the specified string or the default value if the parsing failed
	 */
	public static final Integer boxedParse(final String string, final int radix, final Integer defaultValue) {
		try {
			return Integer.parseInt(string, radix);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

}
