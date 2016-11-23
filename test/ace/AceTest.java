/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import org.junit.Assert;
import org.junit.Test;

public class AceTest {
    
    @Test public void testAssigned() {
        Assert.assertEquals(true, Ace.assigned(Ace.EMPTY_OBJECT));
        Assert.assertEquals(false, Ace.assigned(null, null, null));
        Assert.assertEquals(false, Ace.assigned());
    }
    
    @Test public void testEnsure() {
        Assert.assertEquals(Ace.EMPTY_OBJECT, Ace.ensure(null, Ace.EMPTY_OBJECT));
    }
    
}
