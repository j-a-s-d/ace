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
	
	public static final String getArchitectureName() {
		return System.getProperty("os.arch");
	}
	
	public static final String getName() {
		return System.getProperty("os.name");
	}
	
	public static final String getVersion() {
		return System.getProperty("os.version");
	}
	
	public static final boolean isWindows() {
		return getName().toLowerCase().indexOf("win") >= 0;
	}
	
	public static final boolean isMac() {
		return getName().toLowerCase().indexOf("mac") >= 0;
	}
	
	public static final boolean isUnix() {
		final String os = getName().toLowerCase();
		return (os.indexOf("nix") > 0 || os.indexOf("nux") > 0 || os.indexOf("aix") > 0);
	}
	
	public static boolean isSolaris() {
		return getName().toLowerCase().indexOf("sunos") >= 0;
	}
	
	public static final String runCommand(final String commandLine) {
		return runCommand(commandLine, new String[] {}, new File("./"));
	}
	
	public static final String runCommand(final String commandLine, final String directoryPath) {
		return runCommand(commandLine, new String[] {}, new File(directoryPath));
	}
	
	public static final String runCommand(final String commandLine, final File directory) {
		return runCommand(commandLine, new String[] {}, directory);
	}
	
	public static final String runCommand(final String commandLine, final String[] environment) {
		return runCommand(commandLine.split(" "), environment, new File("./"));
	}
	
	public static final String runCommand(final String commandLine, final String[] environment, final String directoryPath) {
		return runCommand(commandLine.split(" "), environment, new File(directoryPath));
	}
	
	public static final String runCommand(final String commandLine, final String[] environment, final File directory) {
		return runCommand(commandLine.split(" "), environment, directory);
	}
	
	public static final String runCommand(final String[] command, final String[] environment, final String directoryPath) {
		return runCommand(command, environment, new File(directoryPath));
	}
	
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
	
	public static final boolean isWindowsProcess(final Process process) {
		return assigned(process) && Strings.sameContent(process.getClass().getName(), "java.lang.Win32Process");
	}
	
	public static final boolean isUnixProcess(final Process process) {
		return assigned(process) && Strings.sameContent(process.getClass().getName(), "java.lang.UNIXProcess");
	}
	
	public static final Integer getUnixProcessId() {
		return new Sandboxed<Integer>() {
			/*@Override*/ public Integer run() throws Exception {
				return Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
			}
		}.go();
	}
	
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
	
	public static final Boolean getUnixProcessHasExited(final Process process) {
		return new Sandboxed<Boolean>() {
			/*@Override*/ public Boolean run() throws Exception {
				final Field f = Reflection.getFieldAsAccessible(process.getClass(), "hasExited");
				return assigned(f) && isUnixProcess(process) ? f.getBoolean(process) : null;
			}
		}.go();
	}
	
	public static final Integer getUnixProcessExitCode(final Process process) {
		return new Sandboxed<Integer>() {
			/*@Override*/ public Integer run() throws Exception {
				final Field f = Reflection.getFieldAsAccessible(process.getClass(), "exitcode");
				return assigned(f) && isUnixProcess(process) ? f.getInt(process) : null;
			}
		}.go();
	}
	
	private static String getUnixProcessSnapshotInfo(final int pid, final String info) {
		return runCommand("ps -p " + pid + " -o " + info).trim();
	}
	
	private static String getUnixProcessSnapshotData(final int pid, final String info) {
		final String[] p = getUnixProcessSnapshotInfo(pid, info).split("\n");
		return p.length == 1 ? STRINGS.EMPTY : p[1].trim();
	}
	
	public static final Float getUnixProcessCpuUsagePercentage(final int pid) {
		return Floats.parse(getUnixProcessSnapshotData(pid, "%cpu"), NUMBERS.F0);
	}
	
	public static final Float getUnixProcessMemoryUsagePercentage(final int pid) {
		return Floats.parse(getUnixProcessSnapshotData(pid, "%mem"), NUMBERS.F0);
	}
	
	public static final String getUnixProcessStartupCommandLine(final int pid) {
		return getUnixProcessSnapshotData(pid, "cmd");
	}
	
	public static final boolean isUnixProcessRunning(final int pid) {
		return Strings.hasText(getUnixProcessSnapshotInfo(pid, "pid="));
	}

}
