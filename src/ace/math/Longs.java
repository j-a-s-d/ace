/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;
import ace.constants.NUMBERS;

/**
 * Utility class for mathematic operations with longs.
 */
public class Longs extends Ace {

	// UNBOXED

	/**
	 * Adds all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final long add(final long... numbers) {
		long result = NUMBERS.L0;
		for (final long number : numbers) {
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
	public static final long subtract(final long... numbers) {
		long result = numbers.length < 2 ? NUMBERS.L0 : numbers[0] * 2;
		for (final long number : numbers) {
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
	public static final long max(final long... numbers) {
		long result = Integer.MIN_VALUE;
		for (final long number : numbers) {
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
	public static final long min(final long... numbers) {
		long result = Long.MAX_VALUE;
		for (final long number : numbers) {
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
	public static final boolean isEven(final long number) {
		return (number & 1) == 0;
	}

	/**
	 * Determines the specified number is odd.
	 * 
	 * @param number
	 * @return <tt>true</tt> if the specified number is odd, <tt>false</tt> otherwise
	 */
	public static final boolean isOdd(final long number) {
		return (number & 1) != 0;
	}

	/**
	 * Determines if the specified number is divisible by the specified factor.
	 * 
	 * @param number
	 * @param factor
	 * @return <tt>true</tt> if the specified number is divisible by the specified factor, <tt>false</tt> otherwise
	 */
	public static final boolean isDivisibleBy(final long number, final long factor) {
		return number % factor == 0;
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final long parse(final String string, final long defaultValue) {
		try {
			return Long.parseLong(string);
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
	public static final long parse(final String string, final int radix, final long defaultValue) {
		try {
			return Long.parseLong(string, radix);
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
	public static final Long boxedAdd(final Long... numbers) {
		Long result = NUMBERS.L0;
		for (final Long number : numbers) {
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
	public static final Long boxedSubtract(final Long... numbers) {
		Long result = null;
		if (numbers.length > 0) {
			result = numbers.length == 1 ? NUMBERS.L0 : numbers[0] * 2;
			for (final Long number : numbers) {
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
	public static final Long boxedMax(final Long... numbers) {
		Long result = Long.MIN_VALUE;
		for (final Long number : numbers) {
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
	public static final Long boxedMin(final Long... numbers) {
		Long result = Long.MAX_VALUE;
		for (final Long number : numbers) {
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
	public static final boolean boxedIsEven(final Long number) {
		return assigned(number) && ((number & 1) == 0);
	}

	/**
	 * Determines the specified number is odd.
	 * 
	 * @param number
	 * @return <tt>true</tt> if the specified number is odd, <tt>false</tt> otherwise
	 */
	public static final boolean boxedIsOdd(final Long number) {
		return assigned(number) && ((number & 1) != 0);
	}

	/**
	 * Determines if the specified number is divisible by the specified factor.
	 * 
	 * @param number
	 * @param factor
	 * @return <tt>true</tt> if the specified number is divisible by the specified factor, <tt>false</tt> otherwise
	 */
	public static final boolean boxedIsDivisibleBy(final Long number, final Long factor) {
		return assigned(number, factor) && (number % factor == 0);
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final Long boxedParse(final String string, final Long defaultValue) {
		try {
			return Long.parseLong(string);
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
	public static final Long boxedParse(final String string, final int radix, final Long defaultValue) {
		try {
			return Long.parseLong(string, radix);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

}
