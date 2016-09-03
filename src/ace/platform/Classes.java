/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;

/**
 * Utility class for working with classes.
 */
public class Classes extends Ace {
    
    public static boolean isClassLoaded(final String className) {
        try {
            Class.forName(className);
            return true;
        } catch (final Exception e) {
            return false;
        }
    }
    
}
