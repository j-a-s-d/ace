/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import org.junit.Assert;
import org.junit.Test;

public class GenericArraysTest {
    
    @Test public void testMake() {
        Assert.assertArrayEquals(new Object[] {}, GenericArrays.make());
        Assert.assertArrayEquals(new Byte[] {
            (byte) 0x01, (byte) 0x02, (byte) 0x03
        }, GenericArrays.make((byte) 0x01, (byte) 0x02, (byte) 0x03));
    }
    
    @Test public void testHasContent() {
        Assert.assertEquals(true, GenericArrays.hasContent(GenericArrays.make(1, 2, 3)));
        Assert.assertEquals(false, GenericArrays.hasContent(GenericArrays.make()));
        Assert.assertEquals(false, GenericArrays.hasContent(null));
    }
    
    @Test public void testNullFree() {
        Assert.assertEquals(true, GenericArrays.nullFree(GenericArrays.make(1, 2, 3)));
        Assert.assertEquals(false, GenericArrays.nullFree(GenericArrays.make(1, null, 3)));
        Assert.assertEquals(false, GenericArrays.nullFree(null));
    }
    
    @Test public void testContains() {
        Assert.assertEquals(true, GenericArrays.contains(GenericArrays.make(1, 2, 3), 1));
        Assert.assertEquals(true, GenericArrays.contains(GenericArrays.make(1, 2, 3), 2));
        Assert.assertEquals(true, GenericArrays.contains(GenericArrays.make(1, 2, 3), 3));
        Assert.assertEquals(false, GenericArrays.contains(GenericArrays.make(1, 2, 3), 4));
        Assert.assertEquals(false, GenericArrays.contains(GenericArrays.make(1, 2, 3), null));
        Assert.assertEquals(false, GenericArrays.contains(null, 4));
    }
    
    @Test public void testGet() {
        Assert.assertEquals("def", GenericArrays.get(GenericArrays.make("abc", "def"), 1));
        Assert.assertEquals(null, GenericArrays.get(GenericArrays.make(), 0));
    }
    
    @Test public void testFirst() {
        Assert.assertEquals(Boolean.TRUE, GenericArrays.first(GenericArrays.make(true, false)));
        final int x = GenericArrays.first(GenericArrays.make(1, 2, 3));
        Assert.assertEquals(1, x);
    }
    
    @Test public void testLast() {
        Assert.assertEquals(Boolean.FALSE, GenericArrays.last(GenericArrays.make(true, false)));
        final int x = GenericArrays.last(GenericArrays.make(1, 2, 3));
        Assert.assertEquals(3, x);
    }
    
}