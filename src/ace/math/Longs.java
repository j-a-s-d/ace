/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;

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
    
}
