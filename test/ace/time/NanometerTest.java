/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

import static ace.Ace.nap;
import org.junit.Assert;
import org.junit.Test;

public class NanometerTest {

	final Nanometer _meter = new Nanometer() {{
		start();
		nap(10);
		stop();
	}};

	@Test public void testStart() {
		try {
			new Nanometer().start();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testStop() {
		try {
			new Nanometer().stop();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testSnapshotNanoseconds() {
		Assert.assertTrue(_meter.snapshotNanoseconds() > 0);
	}

	@Test public void testAsNanoseconds() {
		Assert.assertTrue(_meter.asNanoseconds() > 0);
	}

	@Test public void testSnapshotMilliseconds() {
		Assert.assertTrue(_meter.snapshotMilliseconds() > 0);
	}

	@Test public void testAsMilliseconds() {
		Assert.assertTrue(_meter.asMilliseconds() > 0);
	}

	@Test public void testSnapshotSeconds() {
		Assert.assertTrue(_meter.snapshotSeconds() > 0);
	}

	@Test public void testAsSeconds() {
		Assert.assertTrue(_meter.asSeconds() > 0);
	}

	@Test public void testSnapshotMinutes() {
		Assert.assertTrue(_meter.snapshotMinutes() > 0);
	}

	@Test public void testAsMinutes() {
		Assert.assertTrue(_meter.asMinutes() > 0);
	}

	@Test public void testSnapshotHours() {
		Assert.assertTrue(_meter.snapshotHours() > 0);
	}

	@Test public void testAsHours() {
		Assert.assertTrue(_meter.asHours() > 0);
	}

	@Test public void testSnapshotDays() {
		Assert.assertTrue(_meter.snapshotDays() > 0);
	}

	@Test public void testAsDays() {
		Assert.assertTrue(_meter.asDays() > 0);
	}

}
