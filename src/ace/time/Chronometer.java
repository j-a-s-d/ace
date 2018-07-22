/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

/**
 * Useful chronometer class based on System.currentTimeMillis().
 */
public class Chronometer extends Timemeter {

	private static final double FACTOR = 1000.0;
	private static final double MINUTE = 60 * FACTOR; // 60000.0
	private static final double HOUR = 60 * MINUTE; // 3600000.0
	private static final double DAY = 24 * HOUR;

	public Chronometer() {
		this(false);
	}

	public Chronometer(final boolean autostart) {
		if (autostart) {
			begin(System.currentTimeMillis());
		}
	}

	public void start() {
		begin(System.currentTimeMillis());
	}

	public long stop() {
		end(System.currentTimeMillis());
		return asMilliseconds();
	}

	public long snapshotMilliseconds() {
		return snapshot(System.currentTimeMillis());
	}

	public long asMilliseconds() {
		return measure();
	}

	public double snapshotSeconds() {
		return snapshotMilliseconds() / FACTOR;
	}

	public double asSeconds() {
		return asMilliseconds() / FACTOR;
	}

	public double snapshotMinutes() {
		return snapshotMilliseconds() / MINUTE;
	}

	public double asMinutes() {
		return asMilliseconds() / MINUTE;
	}

	public double snapshotHours() {
		return snapshotMilliseconds() / HOUR;
	}

	public double asHours() {
		return asMilliseconds() / HOUR;
	}

	public double snapshotDays() {
		return snapshotMilliseconds() / DAY;
	}

	public double asDays() {
		return asMilliseconds() / DAY;
	}

}
