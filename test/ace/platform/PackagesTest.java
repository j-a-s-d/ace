/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import org.junit.Assert;
import org.junit.Test;

public class PackagesTest {
    
    @Test public void testListLoadedPackageNames() {
        Assert.assertNotNull(Packages.listLoadedPackageNames());
    }
    
    @Test public void testIsPackageLoaded() {
        Assert.assertEquals(true, Packages.isPackageLoaded("java.lang"));
        Assert.assertEquals(false, Packages.isPackageLoaded("inexistent"));
    }
    
    @Test public void testHasLocalPackage_String() {
        Assert.assertEquals(false, Packages.hasLocalPackage("java.lang"));
        Assert.assertEquals(true, Packages.hasLocalPackage("ace"));
    }
    
    @Test public void testHasLocalPackage_ClassLoader_String() {
        Assert.assertEquals(false, Packages.hasLocalPackage(this.getClass().getClassLoader(), "java.lang"));
        Assert.assertEquals(true, Packages.hasLocalPackage(this.getClass().getClassLoader(), "ace"));
    }
    
}
