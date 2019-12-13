/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;
import ace.constants.NUMBERS;

/**
 * Utility class for mathematic operations with doubles.
 */
public class Doubles extends Ace {

	// UNBOXED

	/**
	 * Adds all the numbers specified.
	 * 
	 * @param numbers
	 * @return the resulting number
	 */
	public static final double add(final double... numbers) {
		double result = NUMBERS.D0;
		for (final double number : numbers) {
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
	public static final double subtract(final double... numbers) {
		double result = numbers.length < 2 ? NUMBERS.D0 : numbers[0] * 2;
		for (final double number : numbers) {
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
	public static final double max(final double... numbers) {
		double result = Double.MIN_VALUE;
		for (final double number : numbers) {
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
	public static final double min(final double... numbers) {
		double result = Double.MAX_VALUE;
		for (final double number : numbers) {
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
	public static final boolean isDivisibleBy(final double number, final double factor) {
		return number % factor == 0;
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final double parse(final String string, final double defaultValue) {
		try {
			return Double.parseDouble(string);
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
	public static final Double boxedAdd(final Double... numbers) {
		Double result = NUMBERS.D0;
		for (final Double number : numbers) {
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
	public static final Double boxedSubtract(final Double... numbers) {
		Double result = null;
		if (numbers.length > 0) {
			result = numbers.length == 1 ? NUMBERS.D0 : numbers[0] * 2;
			for (final Double number : numbers) {
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
	public static final Double boxedMax(final Double... numbers) {
		Double result = Double.MIN_VALUE;
		for (final Double number : numbers) {
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
	public static final Double boxedMin(final Double... numbers) {
		Double result = Double.MAX_VALUE;
		for (final Double number : numbers) {
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
	public static final boolean boxedIsDivisibleBy(final Double number, final Double factor) {
		return assigned(number, factor) && (number % factor == 0);
	}

	/**
	 * Parses the specified string as a number using the specified default value if it fails doing so.
	 * 
	 * @param string
	 * @param defaultValue
	 * @return the number parsed from the specified string or the default value if the parsing failed
	 */
	public static final Double boxedParse(final String string, final Double defaultValue) {
		try {
			return Double.parseDouble(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

}
