/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.io.File;

/**
 * Utility class for working with the current user information.
 */
public class User extends Ace {

	/**
	 * Gets the current user working directory name (system property: user.dir).
	 * 
	 * @return the current user working directory name
	 */
	public static final String getWorkingDirectoryName() {
		return System.getProperty("user.dir");
	}

	/**
	 * Gets the current user working directory file (system property: user.dir).
	 * 
	 * @return the current user working directory file
	 */
	public static final File getWorkingDirectory() {
		return new File(getWorkingDirectoryName());
	}

	/**
	 * Gets the current user home directory name (system property: user.home).
	 * 
	 * @return the current user home directory name
	 */
	public static final String getHomeDirectoryName() {
		return System.getProperty("user.home");
	}

	/**
	 * Gets the current user home directory file (system property: user.home).
	 * 
	 * @return the current user home directory file
	 */
	public static final File getHomeDirectory() {
		return new File(getHomeDirectoryName());
	}

	/**
	 * Gets the current user account name (system property: user.name).
	 * 
	 * @return the current user account name
	 */
	public static final String getAccountName() {
		return System.getProperty("user.name");
	}

}
