/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.Sandboxed;
import ace.constants.NUMBERS;
import ace.constants.STRINGS;
import ace.math.Floats;
import ace.text.Strings;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

/**
 * Utility class for working with the operative system.
 */
public class OS extends Ace {

	/**
	 * Gets the OS architecture name (system property: os.arch).
	 * 
	 * @return the OS architecture name
	 */
	public static final String getArchitectureName() {
		return System.getProperty("os.arch");
	}

	/**
	 * Gets the OS name (system property: os.name).
	 * 
	 * @return the OS name
	 */
	public static final String getName() {
		return System.getProperty("os.name");
	}

	/**
	 * Gets the OS version (system property: os.version).
	 * 
	 * @return the OS version
	 */
	public static final String getVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * Determines if the current operative system is of the windows family.
	 * 
	 * @return <tt>true</tt> if that is the case, <tt>false</tt> otherwise
	 */
	public static final boolean isWindows() {
		return getName().toLowerCase().indexOf("win") >= 0;
	}

	/**
	 * Determines if the current operative system is of the mac family.
	 * 
	 * @return <tt>true</tt> if that is the case, <tt>false</tt> otherwise
	 */
	public static final boolean isMac() {
		return getName().toLowerCase().indexOf("mac") >= 0;
	}

	/**
	 * Determines if the current operative system is of the unix family.
	 * 
	 * @return <tt>true</tt> if that is the case, <tt>false</tt> otherwise
	 */
	public static final boolean isUnix() {
		final String os = getName().toLowerCase();
		return (os.indexOf("nix") > 0 || os.indexOf("nux") > 0 || os.indexOf("aix") > 0);
	}

	/**
	 * Determines if the current operative system is of the solaris family.
	 * 
	 * @return <tt>true</tt> if that is the case, <tt>false</tt> otherwise
	 */
	public static boolean isSolaris() {
		return getName().toLowerCase().indexOf("sunos") >= 0;
	}

	/**
	 * Runs the specified command line.
	 * 
	 * @param commandLine
	 * @return the string result
	 */
	public static final String runCommand(final String commandLine) {
		return runCommand(commandLine, new String[] {}, new File("./"));
	}

	/**
	 * Runs the specified command line in the specified directory path.
	 * 
	 * @param commandLine
	 * @param directoryPath
	 * @return the string result
	 */
	public static final String runCommand(final String commandLine, final String directoryPath) {
		return runCommand(commandLine, new String[] {}, new File(directoryPath));
	}

	/**
	 * Runs the specified command line in the specified directory.
	 * 
	 * @param commandLine
	 * @param directory
	 * @return the string result
	 */
	public static final String runCommand(final String commandLine, final File directory) {
		return runCommand(commandLine, new String[] {}, directory);
	}

	/**
	 * Runs the specified command line with the specified directory environment variables settings.
	 * 
	 * @param commandLine
	 * @param environment
	 * @return the string result
	 */
	public static final String runCommand(final String commandLine, final String[] environment) {
		return runCommand(commandLine.split(STRINGS.SPACE), environment, new File("./"));
	}

	/**
	 * Runs the specified command line with the specified directory environment variables settings in the specified directory path.
	 * 
	 * @param commandLine
	 * @param environment
	 * @param directoryPath
	 * @return the string result
	 */
	public static final String runCommand(final String commandLine, final String[] environment, final String directoryPath) {
		return runCommand(commandLine.split(STRINGS.SPACE), environment, new File(directoryPath));
	}

	/**
	 * Runs the specified command line with the specified directory environment variables settings in the specified directory.
	 * 
	 * @param commandLine
	 * @param environment
	 * @param directory
	 * @return the string result
	 */
	public static final String runCommand(final String commandLine, final String[] environment, final File directory) {
		return runCommand(commandLine.split(STRINGS.SPACE), environment, directory);
	}

	/**
	 * Runs the specified command line with the specified directory environment variables settings in the specified directory path.
	 * 
	 * @param command
	 * @param environment
	 * @param directoryPath
	 * @return the string result
	 */
	public static final String runCommand(final String[] command, final String[] environment, final String directoryPath) {
		return runCommand(command, environment, new File(directoryPath));
	}

	/**
	 * Runs the specified command line with the specified directory environment variables settings in the specified directory path.
	 * 
	 * @param command
	 * @param environment
	 * @param directory
	 * @return the string result
	 */
	public static final String runCommand(final String[] command, final String[] environment, final File directory) {
		return new Sandboxed<String>() {
			/*@Override*/ public String run() throws Exception {
				final StringBuilder sb = new StringBuilder();
				final Process p = Runtime.getRuntime().exec(command, environment, directory);
				final BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				final BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String s;
				while ((s = stdInput.readLine()) != null) {
					sb.append(s);
					sb.append(STRINGS.EOL);
				}
				while ((s = stdError.readLine()) != null) {
					sb.append(s);
					sb.append(STRINGS.EOL);
				}
				return sb.toString();
			}
		}.go();
	}

	/**
	 * Determines if the current process is windows one.
	 * 
	 * @param process
	 * @return <tt>true</tt> if that is the case, <tt>false</tt> otherwise
	 */
	public static final boolean isWindowsProcess(final Process process) {
		return assigned(process) && Strings.sameContent(process.getClass().getName(), "java.lang.Win32Process");
	}

	/**
	 * Determines if the current process is unix one.
	 * 
	 * @param process
	 * @return <tt>true</tt> if that is the case, <tt>false</tt> otherwise
	 */
	public static final boolean isUnixProcess(final Process process) {
		return assigned(process) && Strings.sameContent(process.getClass().getName(), "java.lang.UNIXProcess");
	}

	/**
	 * Attempts to get the current unix process id.
	 * 
	 * @return the current unix process id if the operation was successful, <tt>null</tt> otherwise
	 */
	public static final Integer getUnixProcessId() {
		return new Sandboxed<Integer>() {
			/*@Override*/ public Integer run() throws Exception {
				return Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
			}
		}.go();
	}

	/**
	 * Attempts to get the unix process id for the specified process.
	 * 
	 * @param process
	 * @return the unix process id for the specified process if the operation was successful, <tt>null</tt> otherwise
	 */
	public static final Integer getUnixProcessId(final Process process) {
		return new Sandboxed<Integer>() {
			/*@Override*/ public Integer run() throws Exception {
				Field f = Reflection.getFieldAsAccessible(process.getClass(), "pid");
				if (!assigned(f)) {
					f = Reflection.getFieldAsAccessible(process.getClass(), "handle");
				}
				return assigned(f) && isUnixProcess(process) ? f.getInt(process) : null;
			}
		}.go();
	}

	/**
	 * Attempts to determine if the specified unix process has exited.
	 * 
	 * @param process
	 * @return <tt>true</tt> if the specified unix process has exited, <tt>false</tt> if it does not exited yet, <tt>null</tt> if the operation failed
	 */
	public static final Boolean getUnixProcessHasExited(final Process process) {
		return new Sandboxed<Boolean>() {
			/*@Override*/ public Boolean run() throws Exception {
				final Field f = Reflection.getFieldAsAccessible(process.getClass(), "hasExited");
				return assigned(f) && isUnixProcess(process) ? f.getBoolean(process) : null;
			}
		}.go();
	}

	/**
	 * Attempts to get the unix process exit code for the specified process.
	 * 
	 * @param process
	 * @return the unix process exit code for the specified process if the operation was successful, <tt>null</tt> otherwise
	 */
	public static final Integer getUnixProcessExitCode(final Process process) {
		return new Sandboxed<Integer>() {
			/*@Override*/ public Integer run() throws Exception {
				final Field f = Reflection.getFieldAsAccessible(process.getClass(), "exitcode");
				return assigned(f) && isUnixProcess(process) ? f.getInt(process) : null;
			}
		}.go();
	}

	/**
	 * Get the snapshot info for the unix process with the specified process id.
	 * 
	 * @param pid
	 * @param info
	 * @return the snapshot info for the unix process with the specified process id
	 */
	private static String getUnixProcessSnapshotInfo(final int pid, final String info) {
		return runCommand("ps -p " + pid + " -o " + info).trim();
	}

	/**
	 * Get the snapshot data for the unix process with the specified process id.
	 * 
	 * @param pid
	 * @param info
	 * @return the snapshot data for the unix process with the specified process id
	 */
	private static String getUnixProcessSnapshotData(final int pid, final String info) {
		final String[] p = getUnixProcessSnapshotInfo(pid, info).split(STRINGS.LF);
		return p.length == 1 ? STRINGS.EMPTY : p[1].trim();
	}

	/**
	 * Get the cpu usage percentage for the unix process with the specified process id.
	 * 
	 * @param pid
	 * @return the cpu usage percentage for the unix process with the specified process id
	 */
	public static final Float getUnixProcessCpuUsagePercentage(final int pid) {
		return Floats.parse(getUnixProcessSnapshotData(pid, "%cpu"), NUMBERS.F0);
	}

	/**
	 * Get the memory usage percentage for the unix process with the specified process id.
	 * 
	 * @param pid
	 * @return the memory usage percentage for the unix process with the specified process id
	 */
	public static final Float getUnixProcessMemoryUsagePercentage(final int pid) {
		return Floats.parse(getUnixProcessSnapshotData(pid, "%mem"), NUMBERS.F0);
	}

	/**
	 * Get the startup command line percentage for the unix process with the specified process id.
	 * 
	 * @param pid
	 * @return the startup command line percentage for the unix process with the specified process id
	 */
	public static final String getUnixProcessStartupCommandLine(final int pid) {
		return getUnixProcessSnapshotData(pid, "cmd");
	}

	/**
	 * Determines if the unix process with the specified process id is still running.
	 * 
	 * @param pid
	 * @return <tt>true</tt> if the unix process with the specified process id is still running, <tt>false</tt> otherwise
	 */
	public static final boolean isUnixProcessRunning(final int pid) {
		return Strings.hasText(getUnixProcessSnapshotInfo(pid, "pid="));
	}

}
