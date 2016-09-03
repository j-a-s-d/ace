/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import ace.Ace;

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
    
}
