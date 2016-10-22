/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

	@Test public void testGetWorkingDirectoryName() {
		Assert.assertNotNull(User.getWorkingDirectoryName());
	}

	@Test public void testGetWorkingDirectory() {
		Assert.assertNotNull(User.getWorkingDirectory());
	}

	@Test public void testGetHomeDirectoryName() {
		Assert.assertNotNull(User.getHomeDirectoryName());
	}

	@Test public void testGetHomeDirectory() {
		Assert.assertNotNull(User.getHomeDirectory());
	}

	@Test public void testGetAccountName() {
		Assert.assertNotNull(User.getAccountName());
	}

}
