/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import org.junit.Assert;
import org.junit.Test;

public class TimemeterTest {

	final Timemeter _meter = new Timemeter() {};
	
	@Test public void testReset() {
		try {
			_meter.reset();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testBegin() {
		try {
			_meter.begin(0L);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testEnd() {
		try {
			_meter.end(0L);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testSnapshot() {
		try {
			_meter.snapshot(0L);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testMeasure() {
		try {
			_meter.measure();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
