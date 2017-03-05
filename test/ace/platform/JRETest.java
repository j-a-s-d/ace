/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import org.junit.Assert;
import org.junit.Test;

public class JRETest {

	@Test public void testGetDirectoryName() {
		Assert.assertNotNull(JRE.getDirectoryName());
	}

	@Test public void testGetDirectory() {
		Assert.assertNotNull(JRE.getDirectory());
	}

	@Test public void testGetVendorName() {
		Assert.assertNotNull(JRE.getVendorName());
	}

	@Test public void testGetVendorUrl() {
		Assert.assertNotNull(JRE.getVendorUrl());
	}

	@Test public void testGetVersion() {
		Assert.assertNotNull(JRE.getVersion());
	}

	@Test public void testGetClassPath() {
		Assert.assertNotNull(JRE.getClassPath());
	}

	@Test public void testGetClassPathElements() {
		Assert.assertNotNull(JRE.getClassPathElements());
	}

}
