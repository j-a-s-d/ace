/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.arrays.ByteArrays;
import ace.interfaces.Treater;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class FilesDirectoryTest {

	final File _d;
	final FilesDirectory _dir;

	{
		Assert.assertNotNull(_d = new File(TemporaryFiles.getDirectory(), "d"));
		Assert.assertTrue(Directories.ensure(_d));
		Assert.assertNotNull(_dir = new FilesDirectory(_d, TemporaryFiles.FILE_EXTENSION) {});
	}
	
	@Test public void testGetPath() {
		Assert.assertNotNull(_dir.getPath());
	}

	@Test public void testComposeFilename() {
		Assert.assertNotNull(_dir.composeFilename("test"));
	}

	@Test public void testComposeFile() {
		Assert.assertNotNull(_dir.composeFile("tmp1"));
	}

	@Test public void testHas() {
		Assert.assertTrue(_dir.has(FilenameUtils.stripExtension(TemporaryFiles.create(_d).getName())));
	}

	@Test public void testDelete() {
		final String n = FilenameUtils.stripExtension(TemporaryFiles.create(_d).getName());
		Assert.assertTrue(_dir.has(n));
		Assert.assertTrue(_dir.delete(n));
		Assert.assertFalse(_dir.has(n));
	}

	@Test public void testList() {
		Assert.assertNotNull(_dir.list());
	}

	@Test public void testListNames() {
		Assert.assertNotNull(_dir.listNames());
	}

	@Test public void testFindNames() {
		TemporaryFiles.create(_d);
		Assert.assertTrue(_dir.findNames(TemporaryFiles.FILE_PREFIX + "*").size() > 0);
	}

	@Test public void testLength() {
		TemporaryFiles.create(_d);
		Assert.assertTrue(_dir.length() > 0);
	}

	@Test public void testTreat() {
		final File f = TemporaryFiles.create(_d);
		Assert.assertTrue(BinaryFiles.write(f, "hello".getBytes()));
		Assert.assertTrue(_dir.treat(new Treater<byte[]>() {
			public byte[] treat(final byte[] content) {
				return ByteArrays.ensure(ByteArrays.invertedCopy(content, 0, content.length));
			}
		}));
		Assert.assertArrayEquals("olleh".getBytes(), BinaryFiles.read(f));
	}

}
