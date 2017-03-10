/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.text.Strings;
import org.junit.Assert;
import org.junit.Test;

public class BinaryFileWriterTest {

	final BinaryFileWriter _writer = new BinaryFileWriter();

	@Test public void testInit_String_boolean() {
		Assert.assertTrue(_writer.init(TemporaryFiles.create().getAbsolutePath(), true));
	}

	@Test public void testInit_File_boolean() {
		Assert.assertTrue(_writer.init(TemporaryFiles.create(), true));
	}

	@Test public void testWrite_byteArr() {
		Assert.assertTrue(_writer.init(TemporaryFiles.create(), true));
		Assert.assertTrue(_writer.write("test".getBytes()));
	}

	@Test public void testWrite_InputStream() {
		Assert.assertTrue(_writer.init(TemporaryFiles.create(), true));
		Assert.assertTrue(_writer.write(Strings.toInputStream("test")));
	}

	@Test public void testWrite_String() {
		Assert.assertTrue(_writer.init(TemporaryFiles.create(), true));
		Assert.assertTrue(_writer.write("test"));
	}

	@Test public void testClose() {
		try {
			_writer.close();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
