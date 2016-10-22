/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.io.File;

/**
 * Utility class for working with the current user information.
 */
public class User extends Ace {

	public static final String getWorkingDirectoryName() {
		return System.getProperty("user.dir");
	}

	public static final File getWorkingDirectory() {
		return new File(getWorkingDirectoryName());
	}

	public static final String getHomeDirectoryName() {
		return System.getProperty("user.home");
	}

	public static final File getHomeDirectory() {
		return new File(getHomeDirectoryName());
	}

	public static final String getAccountName() {
		return System.getProperty("user.name");
	}

}
