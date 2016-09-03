/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.classes;

import org.junit.Assert;
import org.junit.Test;

public class SemanticVersionTest {
    
    @Test public void testFromAndToString() {
        Assert.assertEquals("1.2.3", SemanticVersion.fromString("1.2.3").toString());
    }
    
    @Test public void testGetMajorNumber() {
        Assert.assertEquals(1, new SemanticVersion(1, 2, 3).getMajorNumber());
    }
    
    @Test public void testGetMinorNumber() {
        Assert.assertEquals(2, new SemanticVersion(1, 2, 3).getMinorNumber());
    }
    
    @Test public void testGetPatchNumber() {
        Assert.assertEquals(3, new SemanticVersion(1, 2, 3).getPatchNumber());
    }
    
}
