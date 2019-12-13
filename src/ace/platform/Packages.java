/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.arrays.ImmutableArray;
import java.util.ArrayList;

/**
 * Utility class for working with packages.
 */
public class Packages extends Ace {

	/**
	 * Gets an immutable array of strings containing the loaded package names.
	 * 
	 * @return an immutable array of strings containing the loaded package names
	 */
    public static ImmutableArray<String> listLoadedPackageNames() {
        return new ImmutableArray(new ArrayList<String>() {{
            for (final Package p : Package.getPackages()) {
                add(p.getName());
            }
        }});
    }

	/**
	 * Determines if the package with the specified name is loaded.
	 * 
	 * @param packageName
	 * @return <tt>true</tt> if the package with the specified name is loaded, <tt>false</tt> otherwise
	 */
    public static boolean isPackageLoaded(final String packageName) {
        return assigned(Package.getPackage(packageName));
    }

	/**
	 * Determines if the package with the specified name is loaded in the local class loader.
	 * 
	 * @param packageName
	 * @return <tt>true</tt> if the package with the specified name is loaded in the local class loader, <tt>false</tt> otherwise
	 */
    public static boolean hasLocalPackage(final String packageName) {
        return hasLocalPackage(Packages.class.getClassLoader(), packageName);
    }

	/**
	 * Determines if the package with the specified name is loaded in the specified class loader.
	 * 
	 * @param classLoader
	 * @param packageName
	 * @return <tt>true</tt> if the package with the specified name is specified in the local class loader, <tt>false</tt> otherwise
	 */
    public static boolean hasLocalPackage(final ClassLoader classLoader, final String packageName) {
        return assigned(classLoader.getResource(packageName));
    }

}
