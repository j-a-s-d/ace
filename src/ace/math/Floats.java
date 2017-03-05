/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;
import ace.constants.NUMBERS;

/**
 * Utility class for mathematic operations with floats.
 */
public class Floats extends Ace {

	// UNBOXED
	public static final float add(final float... numbers) {
		float result = NUMBERS.F0;
		for (final float number : numbers) {
			result += number;
		}
		return result;
	}

	public static final float subtract(final float... numbers) {
		float result = numbers.length < 2 ? NUMBERS.F0 : numbers[0] * 2;
		for (final float number : numbers) {
			result -= number;
		}
		return result;
	}

	public static final float max(final float... numbers) {
		float result = Float.MIN_VALUE;
		for (final float number : numbers) {
			result = Math.max(result, number);
		}
		return result;
	}

	public static final float min(final float... numbers) {
		float result = Float.MAX_VALUE;
		for (final float number : numbers) {
			result = Math.min(result, number);
		}
		return result;
	}

	public static final boolean isDivisibleBy(final float number, final float factor) {
		return number % factor == 0;
	}

	public static final float parse(final String string, final float defaultValue) {
		try {
			return Float.parseFloat(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	// BOXED
	public static final Float boxedAdd(final Float... numbers) {
		Float result = NUMBERS.F0;
		for (final Float number : numbers) {
			if (number != null) {
				result += number;
			}
		}
		return result;
	}

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

	public static final Float boxedMax(final Float... numbers) {
		Float result = Float.MIN_VALUE;
		for (final Float number : numbers) {
			if (number != null) {
				result = Math.max(result, number);
			}
		}
		return result;
	}

	public static final Float boxedMin(final Float... numbers) {
		Float result = Float.MAX_VALUE;
		for (final Float number : numbers) {
			if (number != null) {
				result = Math.min(result, number);
			}
		}
		return result;
	}

	public static final boolean boxedIsDivisibleBy(final Float number, final Float factor) {
		return assigned(number, factor) && (number % factor == 0);
	}

	public static final Float boxedParse(final String string, final Float defaultValue) {
		try {
			return Float.parseFloat(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

}
