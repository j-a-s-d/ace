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

	protected boolean _running = true;
	protected int _interval = 10; // NOTE: seconds
	protected File _folder;

	protected final int getInterval() {
		return _interval;
	}

	protected final void setInterval(final int interval) {
		_interval = interval;
	}

	protected final File getFolder() {
		return _folder;
	}

	protected final void setFolder(final File folder) {
		_folder = folder;
	}

	protected boolean isListening() {
		return _running;
	}

	/*@Override*/ public final void start() {
		onStartListening();
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

	/*@Override*/ public final void stop() {
		_running = false;
	}

	protected boolean processFilesOnly(final File[] files) {
		for (final File file : files) {
			if (file.isFile() && !onProcessFile(file)) {
				return false;
			}
		}
		return true;
	}

	protected boolean processDirectoriesOnly(final File[] files) {
		for (final File file : files) {
			if (file.isDirectory() && !onProcessFile(file)) {
				return false;
			}
		}
		return true;
	}

	protected boolean processAll(final File[] files) {
		for (final File file : files) {
			if (!onProcessFile(file)) {
				return false;
			}
		}
		return true;
	}

	public abstract boolean onProcessFiles(final File[] files);

	public abstract boolean onProcessFile(final File file);

	public abstract void onStartListening();

	public abstract void onStopListening();

}
