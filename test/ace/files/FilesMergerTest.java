/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class FilesMergerTest {

	final File _f0;
	final File _f1;
	final File _f2;
	final FilesMerger _fm;

	{
		Assert.assertNotNull(_f0 = TemporaryFiles.create());
		Assert.assertNotNull(_f1 = TemporaryFiles.create());
		Assert.assertNotNull(_f2 = TemporaryFiles.create());
		Assert.assertTrue(TextFiles.write(_f1, "hello ", "ASCII"));
		Assert.assertTrue(TextFiles.write(_f2, "world!", "ASCII"));
		Assert.assertNotNull(_fm = new FilesMerger(_f0));
	}

	@Test public void testGetBufferSize() {
		Assert.assertTrue(_fm.getBufferSize() > 0);
	}

	@Test public void testSetBufferSize() {
		Assert.assertTrue(_fm.setBufferSize(_fm.getBufferSize() * 2).getBufferSize() > 0);
	}

	@Test public void testGetFile() {
		Assert.assertNotNull(_fm.getFile());
	}

	@Test public void testSetFile() {
		Assert.assertNotNull(_fm.setFile(_f0).getFile());
	}

	@Test public void testMerge() {
		Assert.assertTrue(_fm.merge(_f1, _f2));
		Assert.assertEquals("hello world!", TextFiles.read(_f0));
	}

}
