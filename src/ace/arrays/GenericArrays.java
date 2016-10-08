/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;

public class GenericArrays extends Ace {
    
    // COMPOSITION
    
    public static final <T> T[] make(final T... items) {
        return items;
    }
	
    // CONTENT
    
    public static final <T> boolean hasContent(final T[] array) {
        return array != null && array.length > 0;
    }

    public static final <T> boolean nullFree(final T[] array) {
        if (hasContent(array)) {
            boolean result = true;
            for (final Object o : array) {
                result &= o != null;
            }
            return result;
        }
        return false;
    }

    public static final <T> boolean contains(final T[] array, final T v) {
        if (hasContent(array)) {
            if (v == null) {
                for (final T e : array) {
                    if (e == null) {
                        return true;
                    }
                }
            } else {
                for (final T e : array) {
                    if (e == v || v.equals(e)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static final <T> T get(final T[] array, final int index) {
        return hasContent(array) && array.length > index && index >= 0 ? array[index] : null;
    }
    
    public static final <T> T first(final T[] array) {
        return hasContent(array) ? array[0] : null;
    }
    
    public static final <T> T last(final T[] array) {
        return hasContent(array) ? array[array.length - 1] : null;
    }
    
}
