/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.constants.STRINGS;
import ace.interfaces.Immutable;

/**
 * Semantic versioning class.
 *
 * @see <a href="http://semver.org/">semver.org</a>
 */
public class SemanticVersion extends Ace implements Immutable {

	private static final String SCAPED_PERIOD = "\\.";

	private static final int DEFAULT_MAJOR = 0;
	private static final int DEFAULT_MINOR = 0;
	private static final int DEFAULT_PATCH = 0;

	private final int _major;
	private final int _minor;
	private final int _patch;
	private final String _string;

	/**
	 * Makes a semantic version object instance.
	 * 
	 * Note: The major, minor and release/patch numbers will be defaulted to 0.
	 */
	public SemanticVersion() {
		this(DEFAULT_MAJOR, DEFAULT_MINOR, DEFAULT_PATCH);
	}

	/**
	 * Makes a semantic version object instance from the passed integer values.
	 * 
	 * Note: The minor and release/patch numbers will be defaulted to 0.
	 * 
	 * @param major
	 */
	public SemanticVersion(final int major) {
		this(major, DEFAULT_MINOR, DEFAULT_PATCH);
	}

	/**
	 * Makes a semantic version object instance from the passed integer values.
	 * 
	 * Note: The release/patch number will be defaulted to 0.
	 * 
	 * @param major
	 * @param minor
	 */
	public SemanticVersion(final int major, final int minor) {
		this(major, minor, DEFAULT_PATCH);
	}

	/**
	 * Makes a semantic version object instance from the passed integer values.
	 * 
	 * @param major
	 * @param minor
	 * @param release 
	 */
	public SemanticVersion(final int major, final int minor, final int release) {
		_major = major;
		_minor = minor;
		_patch = release;
		_string = Integer.toString(_major) + STRINGS.PERIOD + Integer.toString(_minor) + STRINGS.PERIOD + Integer.toString(_patch);
	}

	/**
	 * Makes a semantic version object instance from the passed string value.
	 * 
	 * Note: That if an exception is thrown inside this method,
	 * it will be forwarded to the Global Exception Handler.
	 * 
	 * @param value
	 * @return the semantic version object instance created from the passed string value
	 * or an instance equivalent to "0.0.0" if it fails to convert the passed string
	 */
	public static final SemanticVersion fromString(final String value) {
		int major = DEFAULT_MAJOR;
		int minor = DEFAULT_MINOR;
		int patch = DEFAULT_PATCH;
		if (assigned(value)) {
			try {
				final String[] numbers = value.split(SCAPED_PERIOD);
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
				GEH.setLastException(e);
			}
		}
		return new SemanticVersion(major, minor, patch);
	}

	/**
	 * Returns the string representation for the current semantic version object instance.
	 * 
	 * @return the string representation for the current semantic version object instance
	 */
	@Override public final String toString() {
		return _string;
	}

	/**
	 * Returns the major version number of the current semantic version.
	 * 
	 * @return the value of the major version number
	 */
	public final int getMajorNumber() {
		return _major;
	}

	/**
	 * Returns the minor version number of the current semantic version.
	 * 
	 * @return the value of the minor version number
	 */
	public final int getMinorNumber() {
		return _minor;
	}

	/**
	 * Returns the patch version number of the current semantic version.
	 * 
	 * @return the value of the patch version number
	 */
	public final int getPatchNumber() {
		return _patch;
	}

	private static int compareVersionAsStrings(final String version1, final String version2) {
		final String[] arr1 = version1.split(SCAPED_PERIOD);
		final String[] arr2 = version2.split(SCAPED_PERIOD);
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

	/**
	 * Compares the actual semantic version against the passed as parameter and
	 * returns <tt>true</tt> if they are equal.
	 * 
	 * @param version
	 * @return <tt>true</tt> if the actual sematic version is equal to the passed one
	 */
	public final boolean equals(final SemanticVersion version) {
		return compareVersionAsStrings(this.toString(), version.toString()) == 0;
	}

	/**
	 * Compares the actual semantic version against the passed as parameter and
	 * returns <tt>true</tt> if it is newer.
	 * 
	 * @param version
	 * @return <tt>true</tt> if the actual sematic version is newer than the passed one
	 */
	public final boolean isNewer(final SemanticVersion version) {
		return compareVersionAsStrings(this.toString(), version.toString()) == 1;
	}

	/**
	 * Compares the actual semantic version against the passed as parameter and
	 * returns <tt>true</tt> if it is older.
	 * 
	 * @param version
	 * @return <tt>true</tt> if the actual sematic version is older than the passed one
	 */
	public final boolean isOlder(final SemanticVersion version) {
		return compareVersionAsStrings(this.toString(), version.toString()) == -1;
	}

}
