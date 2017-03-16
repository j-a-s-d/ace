/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import org.junit.Assert;
import org.junit.Test;

public class SampleProfilerTest {

	final SampleProfiler _profiler = new SampleProfiler();

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
		Assert.assertEquals(0, _profiler.getCurrent());
	}

	@Test public void testGetMinimum() {
		Assert.assertEquals(0, _profiler.getMinimum());
	}

	@Test public void testGetMaximum() {
		Assert.assertEquals(0, _profiler.getMaximum());
	}

	@Test public void testGetAverage() {
		Assert.assertEquals(0, _profiler.getAverage());
	}

	@Test public void testGetAverageMinimum() {
		Assert.assertEquals(0, _profiler.getAverageMinimum());
	}

	@Test public void testGetAverageMaximum() {
		Assert.assertEquals(0, _profiler.getAverageMaximum());
	}

	@Test public void testGetCurrentTimestamp() {
		Assert.assertEquals(0, _profiler.getCurrentTimestamp());
	}

	@Test public void testGetMinimumTimestamp() {
		Assert.assertEquals(0, _profiler.getMinimumTimestamp());
	}

	@Test public void testGetMaximumTimestamp() {
		Assert.assertEquals(0, _profiler.getMaximumTimestamp());
	}

	@Test public void testGetAverageMinimumTimestamp() {
		Assert.assertEquals(0, _profiler.getAverageMinimumTimestamp());
	}

	@Test public void testGetAverageMaximumTimestamp() {
		Assert.assertEquals(0, _profiler.getAverageMaximumTimestamp());
	}

	@Test public void testGetLastErrorTimestamp() {
		Assert.assertEquals(0, _profiler.getLastErrorTimestamp());
	}

	@Test public void testGetTotalErrors() {
		Assert.assertEquals(0, _profiler.getTotalErrors());
	}

	@Test public void testGetTotalCount() {
		Assert.assertEquals(0, _profiler.getTotalCount());
	}

	@Test public void testSnapshot() {
		Assert.assertNotNull(_profiler.snapshot());
	}

}
