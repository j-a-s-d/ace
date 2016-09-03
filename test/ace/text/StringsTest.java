/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {
    
    private static final String A_VALUE = "123";
    
    @Test public void testHasContent() {
        Assert.assertEquals(false, Strings.hasContent(STRINGS.EMPTY));
        Assert.assertEquals(true, Strings.hasContent("test"));
    }
    
    @Test public void testEnsure() {
        Assert.assertEquals(STRINGS.EMPTY, Strings.ensure(null));
        Assert.assertEquals(A_VALUE, Strings.ensure(A_VALUE));
    }
    
    @Test public void testIn() {
        Assert.assertEquals(true, Strings.in(A_VALUE, new String[] { A_VALUE, "456", "789" }));
        Assert.assertEquals(false, Strings.in("0", new String[] { A_VALUE, "456", "789" }));
    }
    
    @Test public void testStartOccurrences() {
        Assert.assertEquals(3, Strings.startOccurrences("AAABBB", 'A'));
        Assert.assertEquals(0, Strings.startOccurrences("test", '1'));
    }
    
}
