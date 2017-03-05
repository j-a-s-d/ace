/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Utility class for working with the JVM.
 */
public class JVM extends Ace {

	public static final String getInstanceId() {
		return ManagementFactory.getRuntimeMXBean().getName();
	}

	public static final String getBootClassPath() {
		return ManagementFactory.getRuntimeMXBean().getBootClassPath();
	}

	public static final String getClassPath() {
		return ManagementFactory.getRuntimeMXBean().getClassPath();
	}

	public static final String getLibraryPath() {
		return ManagementFactory.getRuntimeMXBean().getLibraryPath();
	}

	public static final String getManagementSpecVersion() {
		return ManagementFactory.getRuntimeMXBean().getManagementSpecVersion();
	}

	public static final String getName() {
		return ManagementFactory.getRuntimeMXBean().getName();
	}

	public static final String getSpecName() {
		return ManagementFactory.getRuntimeMXBean().getSpecName();
	}

	public static final String getSpecVendor() {
		return ManagementFactory.getRuntimeMXBean().getSpecVendor();
	}

	public static final String getSpecVersion() {
		return ManagementFactory.getRuntimeMXBean().getSpecVersion();
	}

	public static final String getVmName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	public static final String getVmVendor() {
		return ManagementFactory.getRuntimeMXBean().getVmVendor();
	}

	public static final String getVmVersion() {
		return ManagementFactory.getRuntimeMXBean().getVmVersion();
	}

	public static final List<String> getInputArguments() {
		return ManagementFactory.getRuntimeMXBean().getInputArguments();
	}

	public static final long getStartTime() {
		return ManagementFactory.getRuntimeMXBean().getStartTime();
	}

	public static final long getUptime() {
		return ManagementFactory.getRuntimeMXBean().getUptime();
	}

	public static final long getTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}

	public static final long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	public static final long getMaximumMemory() {
		final long maxMemory = Runtime.getRuntime().maxMemory();
		return (maxMemory == Long.MAX_VALUE ? -1 : maxMemory);
	}

}
