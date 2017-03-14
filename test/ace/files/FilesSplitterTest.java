/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class FilesSplitterTest {

	final File _f;
	final FilesSplitter _fs;

	{
		Assert.assertNotNull(_f = TemporaryFiles.create());
		Assert.assertTrue(TextFiles.write(_f, "hello world!", "ASCII"));
		Assert.assertNotNull(_fs = new FilesSplitter(_f));
	}

	@Test public void testGetBufferSize() {
		Assert.assertTrue(_fs.getBufferSize() > 0);
	}

	@Test public void testSetBufferSize() {
		Assert.assertTrue(_fs.setBufferSize(_fs.getBufferSize() * 2).getBufferSize() > 0);
	}

	@Test public void testGetFile() {
		Assert.assertNotNull(_fs.getFile());
	}

	@Test public void testSetFile() {
		Assert.assertNotNull(_fs.setFile(TemporaryFiles.create()).getFile());
	}

	@Test public void testSplitIntoParts() {
		final File[] parts = _fs.splitIntoParts(2);
		Assert.assertEquals(2, parts.length);
		Assert.assertEquals("hello ", TextFiles.read(parts[0]));
		Assert.assertEquals("world!", TextFiles.read(parts[1]));
	}

	@Test public void testSplitBySize() {
		final File[] parts = _fs.splitBySize(2);
		Assert.assertEquals(6, parts.length);
		Assert.assertEquals("he", TextFiles.read(parts[0]));
		Assert.assertEquals("ll", TextFiles.read(parts[1]));
		Assert.assertEquals("o ", TextFiles.read(parts[2]));
		Assert.assertEquals("wo", TextFiles.read(parts[3]));
		Assert.assertEquals("rl", TextFiles.read(parts[4]));
		Assert.assertEquals("d!", TextFiles.read(parts[5]));
	}

}
