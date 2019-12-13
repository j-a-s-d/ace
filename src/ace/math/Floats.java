/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;
import ace.constants.NUMBERS;

/**
 * Utility class for mathematic operations with floats.
 */
public class Floats extends Ace {

	// UNBOXED

	/**
	 * Adds all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final float add(final float... numbers) {
		float result = NUMBERS.F0;
		for (final float number : numbers) {
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
	public static final float subtract(final float... numbers) {
		float result = numbers.length < 2 ? NUMBERS.F0 : numbers[0] * 2;
		for (final float number : numbers) {
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
	public static final float max(final float... numbers) {
		float result = Float.MIN_VALUE;
		for (final float number : numbers) {
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
	public static final float min(final float... numbers) {
		float result = Float.MAX_VALUE;
		for (final float number : numbers) {
			result = Math.min(result, number);
		}
		return result;
	}

	/**
	 * Determines if the specified number is divisible by the specified factor.
	 * 
	 * @param number
	 * @param factor
	 * @return <tt>true</tt> if the specified number is divisible by the specified factor, <tt>false</tt> otherwise
	 */
	public static final boolean isDivisibleBy(final float number, final float factor) {
		return number % factor == 0;
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final float parse(final String string, final float defaultValue) {
		try {
			return Float.parseFloat(string);
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
	public static final Float boxedAdd(final Float... numbers) {
		Float result = NUMBERS.F0;
		for (final Float number : numbers) {
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
	public static final Float boxedSubtract(final Float... numbers) {
		Float result = null;
		if (numbers.length > 0) {
			result = numbers.length == 1 ? NUMBERS.F0 : numbers[0] * 2;
			for (final Float number : numbers) {
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
	public static final Float boxedMax(final Float... numbers) {
		Float result = Float.MIN_VALUE;
		for (final Float number : numbers) {
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
	public static final Float boxedMin(final Float... numbers) {
		Float result = Float.MAX_VALUE;
		for (final Float number : numbers) {
			if (number != null) {
				result = Math.min(result, number);
			}
		}
		return result;
	}

	/**
	 * Determines if the specified number is divisible by the specified factor.
	 * 
	 * @param number
	 * @param factor
	 * @return <tt>true</tt> if the specified number is divisible by the specified factor, <tt>false</tt> otherwise
	 */
	public static final boolean boxedIsDivisibleBy(final Float number, final Float factor) {
		return assigned(number, factor) && (number % factor == 0);
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final Float boxedParse(final String string, final Float defaultValue) {
		try {
			return Float.parseFloat(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

}
