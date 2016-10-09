/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.app;

import ace.Ace;
import ace.interfaces.Immutable;

/**
 * Semantic versioning handler.
 *
 * @see <a href="http://semver.org/">semver.org</a>
 */
public class SemanticVersion extends Ace implements Immutable {

	private static final int DEFAULT_MAJOR = 0;
	private static final int DEFAULT_MINOR = 0;
	private static final int DEFAULT_PATCH = 0;

	private final int _major;
	private final int _minor;
	private final int _patch;
	private final String _string;

	public SemanticVersion() {
		this(DEFAULT_MAJOR, DEFAULT_MINOR, DEFAULT_PATCH);
	}

	public SemanticVersion(final int major) {
		this(major, DEFAULT_MINOR, DEFAULT_PATCH);
	}

	public SemanticVersion(final int major, final int minor) {
		this(major, minor, DEFAULT_PATCH);
	}

	public SemanticVersion(final int major, final int minor, final int release) {
		_major = major;
		_minor = minor;
		_patch = release;
		_string = Integer.toString(_major) + "." + Integer.toString(_minor) + "." + Integer.toString(_patch);
	}

	public static final SemanticVersion fromString(final String value) {
		int major = DEFAULT_MAJOR;
		int minor = DEFAULT_MINOR;
		int patch = DEFAULT_PATCH;
		try {
			final String[] numbers = value.split("\\.");
			if (numbers.length > 0) {
				major = Integer.parseInt(numbers[0]);
				if (numbers.length > 1) {
					minor = Integer.parseInt(numbers[1]);
					if (numbers.length > 2) {
						patch = Integer.parseInt(numbers[2]);
					}
				}
			}
		} catch (final Exception e) {
			debug(e.getMessage());
		}
		return new SemanticVersion(major, minor, patch);
	}

	@Override public final String toString() {
		return _string;
	}

	public final int getMajorNumber() {
		return _major;
	}

	public final int getMinorNumber() {
		return _minor;
	}

	public final int getPatchNumber() {
		return _patch;
	}

}
