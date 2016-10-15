/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

import org.junit.Assert;
import org.junit.Test;

public class FloatsTest {

	@Test public void testAdd() {
		Assert.assertEquals(11f, Floats.add(1.1f, 2.2f, 3.3f, 4.4f), 0f);
	}

	@Test public void testSubtract() {
		final float f = 3.3f;
		Assert.assertEquals(-3.3f, Floats.subtract(f), 0f);
		Assert.assertEquals(0, Floats.subtract(f, f), 0f);
		Assert.assertEquals(-3.3f, Floats.subtract(f, f, f), 0f);
		Assert.assertEquals(-6.6f, Floats.subtract(f, f, f, f), 0f);
	}

	@Test public void testMax() {
		Assert.assertEquals(2.2f, Floats.max(1.1f, 2.2f), 0f);
	}

	@Test public void testMin() {
		Assert.assertEquals(1.1f, Floats.min(1.1f, 2.2f), 0f);
	}

	@Test public void testBoxedAdd() {
		Assert.assertEquals(new Float(1.1), Floats.boxedAdd(1.1f));
		Assert.assertEquals(new Float(11), Floats.boxedAdd(1.1f, 2.2f, 3.3f, 4.4f));
	}

	@Test public void testBoxedSubtract() {
		final Float f = 3.3f;
		Assert.assertEquals(new Float(-3.3), Floats.boxedSubtract(f));
		Assert.assertEquals(new Float(0), Floats.boxedSubtract(f, f));
		Assert.assertEquals(new Float(-3.3), Floats.boxedSubtract(f, f, f));
		Assert.assertEquals(new Float(-6.6), Floats.boxedSubtract(f, f, f, f));
	}

	@Test public void testBoxedMax() {
		Assert.assertEquals(new Float(2.2), Floats.boxedMax(1.1f, 2.2f));
	}

	@Test public void testBoxedMin() {
		Assert.assertEquals(new Float(1.1), Floats.boxedMin(1.1f, 2.2f));
	}

	@Test public void testIsDivisibleBy() {
		Assert.assertTrue(Floats.isDivisibleBy(4f, 2f));
		Assert.assertFalse(Floats.isDivisibleBy(5f, 3f));
	}

	@Test public void testBoxedIsDivisibleBy() {
		Assert.assertTrue(Floats.boxedIsDivisibleBy(new Float(4), new Float(2)));
		Assert.assertFalse(Floats.boxedIsDivisibleBy(new Float(5), new Float(3)));
		Assert.assertFalse(Floats.boxedIsDivisibleBy(new Float(5), null));
		Assert.assertFalse(Floats.boxedIsDivisibleBy(null, new Float(3)));
		Assert.assertFalse(Floats.boxedIsDivisibleBy(null, null));
	}

}
