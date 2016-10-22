/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.io.File;

/**
 * Utility class for working with the JRE.
 */
public class JRE extends Ace {

	public static final String getDirectoryName() {
		return System.getProperty("java.home");
	}

	public static final File getDirectory() {
		return new File(getDirectoryName());
	}

	public static final String getVendorName() {
		return System.getProperty("java.vendor");
	}

	public static final String getVendorUrl() {
		return System.getProperty("java.vendor.url");
	}

	public static final String getVersion() {
		return System.getProperty("java.version");
	}

	public static final String getClassPath() {
		return System.getProperty("java.class.path");
	}

	public static final String[] getClassPathElements() {
		return getClassPath().split(System.getProperty("path.separator"));
	}

}
