/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import org.junit.Assert;
import org.junit.Test;

public class IntegersTest {
    
    @Test public void testAdd() {
        Assert.assertEquals(10, Integers.add(1, 2, 3, 4));
    }
    
    @Test public void testSubtract() {
        final int i = 3;
        Assert.assertEquals(-3, Integers.subtract(i));
        Assert.assertEquals(0, Integers.subtract(i, i));
        Assert.assertEquals(-3, Integers.subtract(i, i, i));
        Assert.assertEquals(-6, Integers.subtract(i, i, i, i));
    }
    
    @Test public void testMax() {
        Assert.assertEquals(2, Integers.max(1, 2));
    }
    
    @Test public void testMin() {
        Assert.assertEquals(1, Integers.min(1, 2));
    }
    
    @Test public void testBoxedAdd() {
        Assert.assertEquals(new Integer(1), Integers.boxedAdd(1));
        Assert.assertEquals(new Integer(10), Integers.boxedAdd(1, 2, 3, 4));
    }
    
    @Test public void testBoxedSubtract() {
        final Integer i = 3;
        Assert.assertEquals(new Integer(-3), Integers.boxedSubtract(i));
        Assert.assertEquals(new Integer(0), Integers.boxedSubtract(i, i));
        Assert.assertEquals(new Integer(-3), Integers.boxedSubtract(i, i, i));
        Assert.assertEquals(new Integer(-6), Integers.boxedSubtract(i, i, i, i));
    }
    
    @Test public void testBoxedMax() {
        Assert.assertEquals(new Integer(2), Integers.boxedMax(1, 2));
    }
    
    @Test public void testBoxedMin() {
        Assert.assertEquals(new Integer(1), Integers.boxedMin(1, 2));
    }
    
}