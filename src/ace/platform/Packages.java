/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.arrays.ImmutableArray;
import java.util.ArrayList;

/**
 * Utility class for working with packages.
 */
public class Packages extends Ace {
    
    public static ImmutableArray<String> listLoadedPackageNames() {
        return new ImmutableArray(new ArrayList<String>() {{
            for (final Package p : Package.getPackages()) {
                add(p.getName());
            }
        }});
    }
    
    public static boolean isPackageLoaded(final String packageName) {
        return assigned(Package.getPackage(packageName));
    }
    
    public static boolean hasLocalPackage(final String packageName) {
        return hasLocalPackage(Packages.class.getClassLoader(), packageName);
    }
    
    public static boolean hasLocalPackage(final ClassLoader classLoader, final String packageName) {
        return assigned(classLoader.getResource(packageName));
    }
    
}
