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

	/**
	 * Default constructor.
	 */
	public Nanometer() {
		this(false);
	}

	/**
	 * Constructor accepting an auto start flag.
	 * 
	 * @param autostart
	 */
	public Nanometer(final boolean autostart) {
		if (autostart) {
			begin(System.nanoTime());
		}
	}

	/**
	 * Starts the time measure.
	 */
	public void start() {
		begin(System.nanoTime());
	}

	/**
	 * Stops the time measure.
	 * 
	 * @return the measured time as nanoseconds
	 */
	public long stop() {
		end(System.nanoTime());
		return asNanoseconds();
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as nanoseconds
	 */
	public long snapshotNanoseconds() {
		return snapshot(System.nanoTime());
	}

	/**
	 * Gets the measured time as nanoseconds.
	 * 
	 * @return the measured time as nanoseconds
	 */
	public long asNanoseconds() {
		return measure();
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as milliseconds
	 */
	public double snapshotMilliseconds() {
		return snapshotNanoseconds() / FACTOR;
	}

	/**
	 * Gets the measured time as milliseconds.
	 * 
	 * @return the measured time as milliseconds
	 */
	public double asMilliseconds() {
		return asNanoseconds() / FACTOR;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as seconds
	 */
	public double snapshotSeconds() {
		return snapshotNanoseconds() / SECOND;
	}

	/**
	 * Gets the measured time as seconds.
	 * 
	 * @return the measured time as seconds
	 */
	public double asSeconds() {
		return asNanoseconds() / SECOND;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as minutes
	 */
	public double snapshotMinutes() {
		return snapshotNanoseconds() / MINUTE;
	}

	/**
	 * Gets the measured time as minutes.
	 * 
	 * @return the measured time as minutes
	 */
	public double asMinutes() {
		return asNanoseconds() / MINUTE;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as hours
	 */
	public double snapshotHours() {
		return snapshotNanoseconds() / HOUR;
	}

	/**
	 * Gets the measured time as hours.
	 * 
	 * @return the measured time as hours
	 */
	public double asHours() {
		return asNanoseconds() / HOUR;
	}

	/**
	 * Takes the measured time up to this moment without stopping the time measure process.
	 * 
	 * @return the measured time as days
	 */
	public double snapshotDays() {
		return snapshotNanoseconds() / DAY;
	}

	/**
	 * Gets the measured time as days.
	 * 
	 * @return the measured time as days
	 */
	public double asDays() {
		return asNanoseconds() / DAY;
	}

}
