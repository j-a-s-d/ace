/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import org.junit.Assert;
import org.junit.Test;

public class JVMTest {

	@Test public void testGetInstanceId() {
		Assert.assertNotNull(JVM.getInstanceId());
	}

	@Test public void testGetBootClassPath() {
		Assert.assertNotNull(JVM.getBootClassPath());
	}

	@Test public void testGetClassPath() {
		Assert.assertNotNull(JVM.getClassPath());
	}

	@Test public void testGetLibraryPath() {
		Assert.assertNotNull(JVM.getLibraryPath());
	}

	@Test public void testGetManagementSpecVersion() {
		Assert.assertNotNull(JVM.getManagementSpecVersion());
	}

	@Test public void testGetName() {
		Assert.assertNotNull(JVM.getName());
	}

	@Test public void testGetSpecName() {
		Assert.assertNotNull(JVM.getSpecName());
	}

	@Test public void testGetSpecVendor() {
		Assert.assertNotNull(JVM.getSpecVendor());
	}

	@Test public void testGetSpecVersion() {
		Assert.assertNotNull(JVM.getSpecVersion());
	}

	@Test public void testGetVmName() {
		Assert.assertNotNull(JVM.getVmName());
	}

	@Test public void testGetVmVendor() {
		Assert.assertNotNull(JVM.getVmVendor());
	}

	@Test public void testGetVmVersion() {
		Assert.assertNotNull(JVM.getVmVersion());
	}

	@Test public void testGetInputArguments() {
		Assert.assertNotNull(JVM.getInputArguments());
	}

	@Test public void testGetStartTime() {
		Assert.assertNotNull(JVM.getStartTime());
	}

	@Test public void testGetUptime() {
		Assert.assertNotNull(JVM.getUptime());
	}

	@Test public void testGetTotalMemory() {
		Assert.assertNotNull(JVM.getTotalMemory());
	}

	@Test public void testGetFreeMemory() {
		Assert.assertNotNull(JVM.getFreeMemory());
	}

	@Test public void testGetMaximumMemory() {
		Assert.assertNotNull(JVM.getMaximumMemory());
	}

}
