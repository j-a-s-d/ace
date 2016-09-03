/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import org.junit.Assert;
import org.junit.Test;

public class LongsTest {
    
    @Test public void testAdd() {
        Assert.assertEquals(10L, Longs.add(1L, 2L, 3L, 4L));
    }
    
    @Test public void testSubtract() {
        final long l = 3L;
        Assert.assertEquals(-3L, Longs.subtract(l));
        Assert.assertEquals(0L, Longs.subtract(l, l));
        Assert.assertEquals(-3L, Longs.subtract(l, l, l));
        Assert.assertEquals(-6L, Longs.subtract(l, l, l, l));
    }
    
    @Test public void testMax() {
        Assert.assertEquals(2L, Longs.max(1L, 2L));
    }
    
    @Test public void testMin() {
        Assert.assertEquals(1L, Longs.min(1L, 2L));
    }
    
    @Test public void testBoxedAdd() {
        Assert.assertEquals(new Long(1), Longs.boxedAdd(1L));
        Assert.assertEquals(new Long(10), Longs.boxedAdd(1L, 2L, 3L, 4L));
    }
    
    @Test public void testBoxedSubtract() {
        final Long l = 3L;
        Assert.assertEquals(new Long(-3), Longs.boxedSubtract(l));
        Assert.assertEquals(new Long(0), Longs.boxedSubtract(l, l));
        Assert.assertEquals(new Long(-3), Longs.boxedSubtract(l, l, l));
        Assert.assertEquals(new Long(-6), Longs.boxedSubtract(l, l, l, l));
    }
    
    @Test public void testBoxedMax() {
        Assert.assertEquals(new Long(2), Longs.boxedMax(1L, 2L));
    }
    
    @Test public void testBoxedMin() {
        Assert.assertEquals(new Long(1), Longs.boxedMin(1L, 2L));
    }
    
}