/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import org.junit.Assert;
import org.junit.Test;

public class SampleProfilerTest {

	final SampleProfiler _profiler = new SampleProfiler() {{
		sample(true, 1);
		sample(false, 2);
		sample(true, 3);
		sample(false, 4);
		sample(true, 5);
		sample(false, 6);
		sample(true, 7);
	}};

	@Test public void testIsPrecisionSuffixNone() {
		Assert.assertFalse(_profiler.isPrecisionSuffixNone());
	}

	@Test public void testIsPrecisionSuffixMilliseconds() {
		Assert.assertTrue(_profiler.isPrecisionSuffixMilliseconds());
	}

	@Test public void testIsPrecisionSuffixNanoseconds() {
		Assert.assertFalse(_profiler.isPrecisionSuffixNanoseconds());
	}

	@Test public void testSetPrecisionSuffixToNone() {
		Assert.assertEquals(_profiler, _profiler.setPrecisionSuffixToNone());
	}

	@Test public void testSetPrecisionSuffixToMilliseconds() {
		Assert.assertEquals(_profiler, _profiler.setPrecisionSuffixToMilliseconds());
	}

	@Test public void testSetPrecisionSuffixToNanoseconds() {
		Assert.assertEquals(_profiler, _profiler.setPrecisionSuffixToNanoseconds());
	}

	@Test public void testReset() {
		try {
			_profiler.reset();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testSample() {
		try {
			new SampleProfiler().sample(true, 0L);
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testGetCurrent() {
		Assert.assertEquals(7, _profiler.getCurrent());
	}

	@Test public void testGetPreciseCurrent() {
		Assert.assertEquals(7.0, _profiler.getPreciseCurrent(), 0);
	}

	@Test public void testGetMinimum() {
		Assert.assertEquals(1, _profiler.getMinimum());
	}

	@Test public void testGetPreciseMinimum() {
		Assert.assertEquals(1.0, _profiler.getPreciseMinimum(), 0);
	}

	@Test public void testGetMaximum() {
		Assert.assertEquals(7, _profiler.getMaximum());
	}

	@Test public void testGetPreciseMaximum() {
		Assert.assertEquals(7.0, _profiler.getPreciseMaximum(), 0);
	}

	@Test public void testGetAverage() {
		Assert.assertEquals(4, _profiler.getAverage());
	}

	@Test public void testGetPreciseAverage() {
		Assert.assertEquals(4.0, _profiler.getPreciseAverage(), 0);
	}

	@Test public void testGetAverageMinimum() {
		Assert.assertEquals(1, _profiler.getAverageMinimum());
	}

	@Test public void testGetPreciseAverageMinimum() {
	}

	@Test public void testGetAverageMaximum() {
		Assert.assertEquals(4, _profiler.getAverageMaximum());
	}

	@Test public void testGetPreciseAverageMaximum() {
	}

	@Test public void testGetCurrentTimestamp() {
		Assert.assertNotEquals(0, _profiler.getCurrentTimestamp());
	}

	@Test public void testGetMinimumTimestamp() {
		Assert.assertNotEquals(0, _profiler.getMinimumTimestamp());
	}

	@Test public void testGetMaximumTimestamp() {
		Assert.assertNotEquals(0, _profiler.getMaximumTimestamp());
	}

	@Test public void testGetAverageMinimumTimestamp() {
		Assert.assertNotEquals(0, _profiler.getAverageMinimumTimestamp());
	}

	@Test public void testGetAverageMaximumTimestamp() {
		Assert.assertNotEquals(0, _profiler.getAverageMaximumTimestamp());
	}

	@Test public void testGetLastErrorTimestamp() {
		Assert.assertNotEquals(0, _profiler.getLastErrorTimestamp());
	}

	@Test public void testGetTotalErrors() {
		Assert.assertEquals(3, _profiler.getTotalErrors());
	}

	@Test public void testGetTotalCount() {
		Assert.assertEquals(7, _profiler.getTotalCount());
	}

	@Test public void testSnapshot() {
		Assert.assertNotNull(_profiler.snapshot());
	}

	@Test public void testSnapshotPrecisely() {
		Assert.assertNotNull(_profiler.snapshotPrecisely());
	}

}
