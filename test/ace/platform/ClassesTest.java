/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import org.junit.*;

public class ClassesTest {
    
    @Test public void testIsClassLoaded() {
        Assert.assertEquals(true, Classes.isClassLoaded("java.lang.String"));
        Assert.assertEquals(false, Classes.isClassLoaded("blah"));
    }
    
}
