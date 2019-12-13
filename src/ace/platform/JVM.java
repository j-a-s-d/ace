/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Utility class for working with the JVM.
 */
public class JVM extends Ace {

	/**
	 * Gets the JVM instance id.
	 * 
	 * @return the JVM instance id
	 */
	public static final String getInstanceId() {
		return ManagementFactory.getRuntimeMXBean().getName();
	}

	/**
	 * Gets the JVM boot class path.
	 * 
	 * @return the JVM boot class path
	 */
	public static final String getBootClassPath() {
		return ManagementFactory.getRuntimeMXBean().getBootClassPath();
	}

	/**
	 * Gets the JVM class path.
	 * 
	 * @return the JVM class path
	 */
	public static final String getClassPath() {
		return ManagementFactory.getRuntimeMXBean().getClassPath();
	}

	/**
	 * Gets the JVM library path.
	 * 
	 * @return the JVM library path
	 */
	public static final String getLibraryPath() {
		return ManagementFactory.getRuntimeMXBean().getLibraryPath();
	}

	/**
	 * Gets the JVM management specification version.
	 * 
	 * @return the JVM management specification version
	 */
	public static final String getManagementSpecVersion() {
		return ManagementFactory.getRuntimeMXBean().getManagementSpecVersion();
	}

	/**
	 * Gets the JVM name.
	 * 
	 * @return the JVM name
	 */
	public static final String getName() {
		return ManagementFactory.getRuntimeMXBean().getName();
	}

	/**
	 * Gets the JVM specification name.
	 * 
	 * @return the JVM specification name
	 */
	public static final String getSpecName() {
		return ManagementFactory.getRuntimeMXBean().getSpecName();
	}

	/**
	 * Gets the JVM specification vendor.
	 * 
	 * @return the JVM specification vendor
	 */
	public static final String getSpecVendor() {
		return ManagementFactory.getRuntimeMXBean().getSpecVendor();
	}

	/**
	 * Gets the JVM specification version.
	 * 
	 * @return the JVM specification version
	 */
	public static final String getSpecVersion() {
		return ManagementFactory.getRuntimeMXBean().getSpecVersion();
	}

	/**
	 * Gets the JVM virtual machine name.
	 * 
	 * @return the JVM virtual machine name
	 */
	public static final String getVmName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	/**
	 * Gets the JVM virtual machine vendor.
	 * 
	 * @return the JVM virtual machine vendor
	 */
	public static final String getVmVendor() {
		return ManagementFactory.getRuntimeMXBean().getVmVendor();
	}

	/**
	 * Gets the JVM virtual machine version.
	 * 
	 * @return the JVM virtual machine version
	 */
	public static final String getVmVersion() {
		return ManagementFactory.getRuntimeMXBean().getVmVersion();
	}

	/**
	 * Gets the JVM input arguments as a list of strings.
	 * 
	 * @return the JVM input arguments as a list of strings
	 */
	public static final List<String> getInputArguments() {
		return ManagementFactory.getRuntimeMXBean().getInputArguments();
	}

	/**
	 * Gets the JVM start time.
	 * 
	 * @return the JVM start time
	 */
	public static final long getStartTime() {
		return ManagementFactory.getRuntimeMXBean().getStartTime();
	}

	/**
	 * Gets the JVM up time.
	 * 
	 * @return the JVM up time
	 */
	public static final long getUptime() {
		return ManagementFactory.getRuntimeMXBean().getUptime();
	}

	/**
	 * Gets the JVM total memory.
	 * 
	 * @return the JVM total memory
	 */
	public static final long getTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Gets the JVM free memory.
	 * 
	 * @return the JVM free memory
	 */
	public static final long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Gets the JVM maximum memory will attempt to use.
	 * 
	 * @return the JVM maximum memory will attempt to use
	 */
	public static final long getMaximumMemory() {
		final long maxMemory = Runtime.getRuntime().maxMemory();
		return (maxMemory == Long.MAX_VALUE ? -1 : maxMemory);
	}

}
