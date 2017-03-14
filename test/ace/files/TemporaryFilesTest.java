/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class TemporaryFilesTest {

	@Test public void testGetCreatedCount() {
		Assert.assertNotNull(TemporaryFiles.create());
		Assert.assertNotEquals(0, TemporaryFiles.getCreatedCount());
	}

	@Test public void testCreate_0args() {
		Assert.assertNotNull(TemporaryFiles.create());
	}

	@Test public void testCreate_String() {
		final File tmp = TemporaryFiles.create("prefix");
		Assert.assertNotNull(tmp);
		Assert.assertTrue(tmp.getName().startsWith("prefix"));
	}

	@Test public void testCreate_File() {
		Assert.assertNotNull(TemporaryFiles.create(new File(System.getProperty("java.io.tmpdir"))));
	}

	@Test public void testCreate_String_String() {
		final File tmp = TemporaryFiles.create("prefix", System.getProperty("java.io.tmpdir"));
		Assert.assertNotNull(tmp);
		Assert.assertTrue(tmp.getName().startsWith("prefix"));
	}

	@Test public void testCreate_String_File() {
		final File tmp = TemporaryFiles.create("prefix", new File(System.getProperty("java.io.tmpdir")));
		Assert.assertNotNull(tmp);
		Assert.assertTrue(tmp.getName().startsWith("prefix"));
	}

	@Test public void testGetDirectory() {
		Assert.assertNotNull(TemporaryFiles.getDirectory());
	}

	@Test public void testGetDirectoryPath() {
		Assert.assertNotNull(TemporaryFiles.getDirectoryPath());
	}

}
