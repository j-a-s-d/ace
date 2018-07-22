/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.time;

/**
 * Useful nanometer class based on System.nanoTime().
 */
public class Nanometer extends Timemeter {

	private static final double FACTOR = 1000000.0;
	private static final double SECOND = 1000 * FACTOR;
	private static final double MINUTE = 60 * SECOND;
	private static final double HOUR = 60 * MINUTE;
	private static final double DAY = 24 * HOUR;

	public Nanometer() {
		this(false);
	}

	public Nanometer(final boolean autostart) {
		if (autostart) {
			begin(System.nanoTime());
		}
	}

	public void start() {
		begin(System.nanoTime());
	}

	public long stop() {
		end(System.nanoTime());
		return asNanoseconds();
	}

	public long snapshotNanoseconds() {
		return snapshot(System.nanoTime());
	}

	public long asNanoseconds() {
		return measure();
	}

	public double snapshotMilliseconds() {
		return snapshotNanoseconds() / FACTOR;
	}

	public double asMilliseconds() {
		return asNanoseconds() / FACTOR;
	}

	public double snapshotSeconds() {
		return snapshotNanoseconds() / SECOND;
	}

	public double asSeconds() {
		return asNanoseconds() / SECOND;
	}

	public double snapshotMinutes() {
		return snapshotNanoseconds() / MINUTE;
	}

	public double asMinutes() {
		return asNanoseconds() / MINUTE;
	}

	public double snapshotHours() {
		return snapshotNanoseconds() / HOUR;
	}

	public double asHours() {
		return asNanoseconds() / HOUR;
	}

	public double snapshotDays() {
		return snapshotNanoseconds() / DAY;
	}

	public double asDays() {
		return asNanoseconds() / DAY;
	}

}
