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

	/**
	 * Default constructor.
	 */
	public Chronometer() {
		this(false);
	}

	/**
	 * Constructor accepting an auto start flag.
	 * 
	 * @param autostart
	 */
	public Chronometer(final boolean autostart) {
		if (autostart) {
			begin(System.currentTimeMillis());
		}
	}

	/**
	 * Starts the time measure.
	 */
	public void start() {
		begin(System.currentTimeMillis());
	}

	/**
	 * Stops the time measure.
	 * 
	 * @return the measured time as milliseconds
	 */
	public long stop() {
		end(System.currentTimeMillis());
		return asMilliseconds();
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as milliseconds
	 */
	public long snapshotMilliseconds() {
		return snapshot(System.currentTimeMillis());
	}

	/**
	 * Gets the measured time as milliseconds.
	 * 
	 * @return the measured time as milliseconds
	 */
	public long asMilliseconds() {
		return measure();
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as seconds
	 */
	public double snapshotSeconds() {
		return snapshotMilliseconds() / FACTOR;
	}

	/**
	 * Gets the measured time as seconds.
	 * 
	 * @return the measured time as seconds
	 */
	public double asSeconds() {
		return asMilliseconds() / FACTOR;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as minutes
	 */
	public double snapshotMinutes() {
		return snapshotMilliseconds() / MINUTE;
	}

	/**
	 * Gets the measured time as minutes.
	 * 
	 * @return the measured time as minutes
	 */
	public double asMinutes() {
		return asMilliseconds() / MINUTE;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as hours
	 */
	public double snapshotHours() {
		return snapshotMilliseconds() / HOUR;
	}

	/**
	 * Gets the measured time as hours.
	 * 
	 * @return the measured time as hours
	 */
	public double asHours() {
		return asMilliseconds() / HOUR;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as days
	 */
	public double snapshotDays() {
		return snapshotMilliseconds() / DAY;
	}

	/**
	 * Gets the measured time as days.
	 * 
	 * @return the measured time as days
	 */
	public double asDays() {
		return asMilliseconds() / DAY;
	}

}
