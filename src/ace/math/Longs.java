/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;
import ace.constants.NUMBERS;

/**
 * Utility class for mathematic operations with longs.
 */
public class Longs extends Ace {

	// UNBOXED
	public static final long add(final long... numbers) {
		long result = NUMBERS.L0;
		for (final long number : numbers) {
			result += number;
		}
		return result;
	}

	public static final long subtract(final long... numbers) {
		long result = numbers.length < 2 ? NUMBERS.L0 : numbers[0] * 2;
		for (final long number : numbers) {
			result -= number;
		}
		return result;
	}

	public static final long max(final long... numbers) {
		long result = Integer.MIN_VALUE;
		for (final long number : numbers) {
			result = Math.max(result, number);
		}
		return result;
	}

	public static final long min(final long... numbers) {
		long result = Long.MAX_VALUE;
		for (final long number : numbers) {
			result = Math.min(result, number);
		}
		return result;
	}

	public static final boolean isEven(final long number) {
		return (number & 1) == 0;
	}

	public static final boolean isOdd(final long number) {
		return (number & 1) != 0;
	}

	public static final boolean isDivisibleBy(final long number, final long factor) {
		return number % factor == 0;
	}

	public static final long parse(final String string, final long defaultValue) {
		try {
			return Long.parseLong(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	public static final long parse(final String string, final int radix, final long defaultValue) {
		try {
			return Long.parseLong(string, radix);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	// BOXED
	public static final Long boxedAdd(final Long... numbers) {
		Long result = NUMBERS.L0;
		for (final Long number : numbers) {
			if (number != null) {
				result += number;
			}
		}
		return result;
	}

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

	public static final Long boxedMax(final Long... numbers) {
		Long result = Long.MIN_VALUE;
		for (final Long number : numbers) {
			if (number != null) {
				result = Math.max(result, number);
			}
		}
		return result;
	}

	public static final Long boxedMin(final Long... numbers) {
		Long result = Long.MAX_VALUE;
		for (final Long number : numbers) {
			if (number != null) {
				result = Math.min(result, number);
			}
		}
		return result;
	}

	public static final boolean boxedIsEven(final Long number) {
		return assigned(number) && ((number & 1) == 0);
	}

	public static final boolean boxedIsOdd(final Long number) {
		return assigned(number) && ((number & 1) != 0);
	}

	public static final boolean boxedIsDivisibleBy(final Long number, final Long factor) {
		return assigned(number, factor) && (number % factor == 0);
	}

	public static final Long boxedParse(final String string, final Long defaultValue) {
		try {
			return Long.parseLong(string);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

	public static final Long boxedParse(final String string, final int radix, final Long defaultValue) {
		try {
			return Long.parseLong(string, radix);
		} catch (final Exception e) {
			GEH.setLastException(e);
			return defaultValue;
		}
	}

}
