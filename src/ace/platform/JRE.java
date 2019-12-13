/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.io.File;

/**
 * Utility class for working with the JRE.
 */
public class JRE extends Ace {

	/**
	 * Gets the JRE directory name (system property: java.home).
	 * 
	 * @return the JRE directory name
	 */
	public static final String getDirectoryName() {
		return System.getProperty("java.home");
	}

	/**
	 * Gets the JRE directory file (system property: java.home).
	 * 
	 * @return the JRE directory file
	 */
	public static final File getDirectory() {
		return new File(getDirectoryName());
	}

	/**
	 * Gets the JRE vendor name (system property: java.vendor).
	 * 
	 * @return the JRE vendor name
	 */
	public static final String getVendorName() {
		return System.getProperty("java.vendor");
	}

	/**
	 * Gets the JRE vendor url (system property: java.vendor.url).
	 * 
	 * @return the JRE vendor url
	 */
	public static final String getVendorUrl() {
		return System.getProperty("java.vendor.url");
	}

	/**
	 * Gets the JRE version (system property: java.version).
	 * 
	 * @return the JRE version
	 */
	public static final String getVersion() {
		return System.getProperty("java.version");
	}

	/**
	 * Gets the JRE class path (system property: java.class.path).
	 * 
	 * @return the JRE class path
	 */
	public static final String getClassPath() {
		return System.getProperty("java.class.path");
	}

	/**
	 * Gets the JRE class path (system property: java.class.path) elements as a string array.
	 * 
	 * @return the JRE class path elements as a string array
	 */
	public static final String[] getClassPathElements() {
		return getClassPath().split(System.getProperty("path.separator"));
	}

}
