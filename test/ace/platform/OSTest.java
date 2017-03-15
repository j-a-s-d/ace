/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.arrays.GenericArrays;
import ace.files.Directories;
import org.junit.Assert;
import org.junit.Test;

public class OSTest {

	private final String[] ENVIRONMENT = GenericArrays.make("LC_ALL=en_US.UTF-8", "HOME=" + User.getHomeDirectoryName());

	@Test public void testGetArchitectureName() {
		Assert.assertNotNull(OS.getArchitectureName());
	}

	@Test public void testGetName() {
		Assert.assertNotNull(OS.getName());
	}

	@Test public void testGetVersion() {
		Assert.assertNotNull(OS.getVersion());
	}

	@Test public void testIsWindows() {
		Assert.assertNotNull(OS.isWindows());
	}

	@Test public void testIsMac() {
		Assert.assertNotNull(OS.isMac());
	}

	@Test public void testIsUnix() {
		Assert.assertNotNull(OS.isUnix());
	}

	@Test public void testIsSolaris() {
		Assert.assertNotNull(OS.isSolaris());
	}

	@Test public void testRunCommand_String() {
		Assert.assertNotNull(OS.runCommand("ls -l"));
	}

	@Test public void testRunCommand_String_String() {
		Assert.assertNotNull(OS.runCommand("ls -l", "./"));
	}

	@Test public void testRunCommand_String_File() {
		Assert.assertNotNull(OS.runCommand("ls -l", Directories.CURRENT));
	}

	@Test public void testRunCommand_String_StringArr() {
		Assert.assertNotNull(OS.runCommand("ls -l", ENVIRONMENT));
	}

	@Test public void testRunCommand_3args_1() {
		Assert.assertNotNull(OS.runCommand("ls -l", ENVIRONMENT, "./"));
	}

	@Test public void testRunCommand_3args_2() {
		Assert.assertNotNull(OS.runCommand("ls -l", ENVIRONMENT, Directories.CURRENT));
	}

	@Test public void testRunCommand_3args_3() {
		Assert.assertNotNull(OS.runCommand(GenericArrays.make("/bin/bash", "-c", "ls", "-l"), ENVIRONMENT, "./"));
	}

	@Test public void testRunCommand_3args_4() {
		Assert.assertNotNull(OS.runCommand(GenericArrays.make("/bin/bash", "-c", "ls", "-l"), ENVIRONMENT, Directories.CURRENT));
	}

	@Test public void testIsWindowsProcess() {
		Assert.assertFalse(OS.isWindowsProcess(null));
	}

	@Test public void testIsUnixProcess() {
		Assert.assertFalse(OS.isUnixProcess(null));
	}

	@Test public void testGetUnixProcessId_0args() {
		Assert.assertNotNull(OS.getUnixProcessId());
	}

	@Test public void testGetUnixProcessId_Process() {
		Assert.assertNull(OS.getUnixProcessId(null));
	}

	@Test public void testGetUnixProcessHasExited() {
		Assert.assertNull(OS.getUnixProcessHasExited(null));
	}

	@Test public void testGetUnixProcessExitCode() {
		Assert.assertNull(OS.getUnixProcessExitCode(null));
	}

	@Test public void testGetUnixProcessCpuUsagePercentage() {
		Assert.assertNotNull(OS.getUnixProcessCpuUsagePercentage(OS.getUnixProcessId()));
	}

	@Test public void testGetUnixProcessMemoryUsagePercentage() {
		Assert.assertNotNull(OS.getUnixProcessMemoryUsagePercentage(OS.getUnixProcessId()));
	}

	@Test public void testGetUnixProcessStartupCommandLine() {
		Assert.assertNotNull(OS.getUnixProcessStartupCommandLine(OS.getUnixProcessId()));
	}

	@Test public void testIsUnixProcessRunning() {
		Assert.assertTrue(OS.isUnixProcessRunning(OS.getUnixProcessId()));
	}

}
