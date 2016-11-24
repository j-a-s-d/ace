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

	private static int compareVersionAsStrings(final String version1, final String version2) {
		final String[] arr1 = version1.split("\\.");
		final String[] arr2 = version2.split("\\.");
		int i = 0;
		while (i < arr1.length || i < arr2.length) {
			if (i < arr1.length && i < arr2.length) {
				if (Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])) {
					return -1;
				} else if (Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])) {
					return 1;
				}
			} else if (i < arr1.length) {
				if (Integer.parseInt(arr1[i]) != 0) {
					return 1;
				}
			} else if (i < arr2.length) {
				if (Integer.parseInt(arr2[i]) != 0) {
					return -1;
				}
			}
			i++;
		}
		return 0;
	}

	public final boolean equals(final SemanticVersion version) {
		return compareVersionAsStrings(this.toString(), version.toString()) == 0;
	}

	public final boolean isNewer(final SemanticVersion version) {
		return compareVersionAsStrings(this.toString(), version.toString()) == 1;
	}

	public final boolean isOlder(final SemanticVersion version) {
		return compareVersionAsStrings(this.toString(), version.toString()) == -1;
	}

}
