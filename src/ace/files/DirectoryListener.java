/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.LocalExceptionHandler;
import ace.interfaces.Initializable;
import ace.interfaces.Startable;
import ace.interfaces.Stoppable;
import java.io.File;

/**
 * Useful directory listener class.
 */
public abstract class DirectoryListener extends LocalExceptionHandler implements Initializable, Startable, Stoppable {

	protected boolean _running = false;
	protected int _interval = 10; // NOTE: seconds
	protected File _folder;

	/**
	 * Gets the monitoring interval (in seconds).
	 * 
	 * @return the monitoring interval
	 */
	protected final int getInterval() {
		return _interval;
	}

	/**
	 * Sets the monitoring interval (in seconds).
	 * 
	 * @param interval 
	 */
	protected final void setInterval(final int interval) {
		_interval = interval;
	}

	/**
	 * Gets the monitored directory.
	 * 
	 * @return the monitored directory
	 */
	protected final File getFolder() {
		return _folder;
	}

	/**
	 * Sets the monitored directory.
	 * 
	 * @param folder 
	 */
	protected final void setFolder(final File folder) {
		_folder = folder;
	}

	/**
	 * Determines if this directory listener is active.
	 * 
	 * @return <tt>true</tt> if this directory listener is monitoring, <tt>false</tt> otherwise
	 */
	protected boolean isListening() {
		return _running;
	}

	/**
	 * Starts the monitoring of the directory.
	 */
	/*@Override*/ public final void start() {
		onStartListening();
		_running = true;
		while (_running) {
			try {
				if (_running = onProcessFiles(_folder.listFiles())) {
					if (hadException()) {
						stop();
					} else {
						Thread.sleep(_interval * 1000);
					}
				}
			} catch (final Exception e) {
				setLastException(e);
				stop();
			}
		}
		onStopListening();
	}

	/**
	 * Stops the monitoring of the directory.
	 */
	/*@Override*/ public final void stop() {
		_running = false;
	}

	/**
	 * Utility method to send only the files of the specified array to the onProcessFile method.
	 * 
	 * @param files
	 * @return <tt>true</tt> if all the files were processed successfully, <tt>false</tt> otherwise
	 */
	protected boolean processFilesOnly(final File[] files) {
		for (final File file : files) {
			if (file.isFile() && !onProcessFile(file)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Utility method to send only the directories of the specified array to the onProcessFile method.
	 * 
	 * @param files
	 * @return <tt>true</tt> if all the directories were processed successfully, <tt>false</tt> otherwise
	 */
	protected boolean processDirectoriesOnly(final File[] files) {
		for (final File file : files) {
			if (file.isDirectory() && !onProcessFile(file)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Utility method to send all the files (and directories) of the specified array to the onProcessFile method.
	 * 
	 * @param files
	 * @return <tt>true</tt> if all the files (and directories) were processed successfully, <tt>false</tt> otherwise
	 */
	protected boolean processAll(final File[] files) {
		for (final File file : files) {
			if (!onProcessFile(file)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method called on every monitoring cycle with the array of files (and directories) included in the monitored directory.
	 * 
	 * From its code, you should call your onProcessFile override with the selected subgroup of files.
	 * For this purpose, utility methods like processFilesOnly, processDirectoriesOnly and processAll are provided.
	 * 
	 * @param files
	 * @return <tt>true</tt> if all the selected files were processed successfully, <tt>false</tt> otherwise
	 */
	public abstract boolean onProcessFiles(final File[] files);

	/**
	 * Method that should be called from your onProcessFiles override directly or indirectly (via process* convenience methods).
	 * 
	 * @param file
	 * @return <tt>true</tt> if the file process was successful, <tt>false</tt> otherwise
	 */
	public abstract boolean onProcessFile(final File file);

	/**
	 * Method called when the directory monitoring starts.
	 */
	public abstract void onStartListening();

	/**
	 * Method called when the directory monitoring stops.
	 */
	public abstract void onStopListening();

}
