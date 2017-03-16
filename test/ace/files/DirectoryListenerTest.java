/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.concurrency.SelfSpawned;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class DirectoryListenerTest {

	boolean PROCESS_FILES_RESULT = false;
	
	final DirectoryListener _listener = new DirectoryListener() {
		/*@Override*/ public boolean onProcessFiles(final File[] files) {
			return PROCESS_FILES_RESULT;
		}

		/*@Override*/ public boolean onProcessFile(final File file) {
			return false;
		}

		/*@Override*/ public void onStartListening() {
			//
		}

		/*@Override*/ public void onStopListening() {
			//
		}

		public boolean initialize() {
			return true;
		}

	};

	@Test public void testGetInterval() {
		Assert.assertNotNull(_listener.getInterval());
	}

	@Test public void testSetInterval() {
		try {
			final int old = _listener.getInterval();
			_listener.setInterval(0);
			Assert.assertEquals(0, _listener.getInterval());
			_listener.setInterval(old);
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testGetFolder() {
		Assert.assertNull(_listener.getFolder());
	}

	@Test public void testSetFolder() {
		try {
			final File old = _listener.getFolder();
			_listener.setFolder(TemporaryFiles.getDirectory());
			Assert.assertEquals(TemporaryFiles.getDirectory(), _listener.getFolder());
			_listener.setFolder(old);
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testIsListening() {
		Assert.assertFalse(_listener.isListening());
	}

	@Test public void testStart() {
		Assert.assertFalse(_listener.isListening());
		final File oldFolder = _listener.getFolder();
		final int oldInterval = _listener.getInterval();
		try {
			_listener.setFolder(TemporaryFiles.getDirectory());
			_listener.setInterval(1);
			_listener.start();
		} catch (final Exception e) {
			Assert.fail();
		}
		_listener.setFolder(oldFolder);
		_listener.setInterval(oldInterval);
		Assert.assertFalse(_listener.isListening());
	}

	@Test public void testStop() {
		Assert.assertFalse(_listener.isListening());
		final File oldFolder = _listener.getFolder();
		final int oldInterval = _listener.getInterval();
		PROCESS_FILES_RESULT = true;
		try {
			_listener.setFolder(TemporaryFiles.getDirectory());
			_listener.setInterval(3);
			new SelfSpawned() {
				/*@Override*/ public void run() {
					nap(1500);
					_listener.stop();
				}
			};
			_listener.start();
		} catch (final Exception e) {
			Assert.fail();
		}
		PROCESS_FILES_RESULT = false;
		_listener.setFolder(oldFolder);
		_listener.setInterval(oldInterval);
		Assert.assertFalse(_listener.isListening());
	}

}
