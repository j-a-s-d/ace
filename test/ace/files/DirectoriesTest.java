/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import static ace.files.Directories.walkDirectory;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class DirectoriesTest {

	@Test public void testExists() {
		Assert.assertTrue(Directories.exists(new File("./")));
	}

	boolean walked = false;
	@Test public void testWalkDirectory() {
		walkDirectory(new File("./"), false, new Directories.DirectoryWalkerHandler() {
			@Override public void processFile(final File file) {
				walked = true;
			}
		});
		Assert.assertTrue(walked);
	}

	@Test public void testListFiles_String() {
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectoryPath()));
	}

	@Test public void testListFiles_String_boolean() {
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectoryPath(), false));
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectoryPath(), true));
	}

	@Test public void testListFiles_3args_1() {
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectoryPath(), false, FilenameValidator.makeAllFilesValidator()));
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectoryPath(), true, FilenameValidator.makeAllFilesValidator()));
	}

	@Test public void testListFiles_File() {
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectory()));
	}

	@Test public void testListFiles_File_boolean() {
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectory(), false));
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectory(), true));
	}

	@Test public void testListFiles_3args_2() {
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectory(), false, FilenameValidator.makeAllFilesValidator()));
		Assert.assertNotNull(Directories.listFiles(TemporaryFiles.getDirectory(), true, FilenameValidator.makeAllFilesValidator()));
	}

	@Test public void testListDirectories_String_boolean() {
		Assert.assertNotNull(Directories.listDirectories(TemporaryFiles.getDirectoryPath(), false));
		Assert.assertNotNull(Directories.listDirectories(TemporaryFiles.getDirectoryPath(), true));
	}

	@Test public void testListDirectories_File_boolean() {
		Assert.assertNotNull(Directories.listDirectories(TemporaryFiles.getDirectory(), false));
		Assert.assertNotNull(Directories.listDirectories(TemporaryFiles.getDirectory(), true));
	}

	@Test public void testEnsure_String() {
		Assert.assertTrue(Directories.ensure(TemporaryFiles.getDirectoryPath()));
	}

	@Test public void testEnsure_File() {
		Assert.assertTrue(Directories.ensure(TemporaryFiles.getDirectory()));
	}

	@Test public void testDelete_String() {
		final String aux = FilenameUtils.ensureLastDirectorySeparator(TemporaryFiles.getDirectoryPath()) + "test";
		Assert.assertTrue(Directories.ensure(aux));
		Assert.assertTrue(Directories.delete(aux));
	}

	@Test public void testDelete_File() {
		final File aux = new File(TemporaryFiles.getDirectory(), "test");
		Assert.assertTrue(Directories.ensure(aux));
		Assert.assertTrue(Directories.delete(aux));
	}

	@Test public void testGetDirectorySize_String() {
		Assert.assertTrue(Directories.calculateSize(TemporaryFiles.getDirectoryPath()) > 0);
	}

	@Test public void testGetDirectorySize_File() {
		Assert.assertTrue(Directories.calculateSize(TemporaryFiles.getDirectory()) > 0);
	}

	@Test public void testCopy_String_String() {
		final String tmp1 = FilenameUtils.ensureLastDirectorySeparator(TemporaryFiles.getDirectoryPath()) + "tmp1";
		Assert.assertTrue(Directories.ensure(tmp1));
		final String n = TemporaryFiles.create(new File(tmp1)).getName();
		Assert.assertNotNull(n);
		final String tmp2 = FilenameUtils.ensureLastDirectorySeparator(TemporaryFiles.getDirectoryPath()) + "tmp2";
		Assert.assertTrue(Directories.copy(tmp1, tmp2));
		Assert.assertTrue(Files.exists(new File(tmp2, n)));
	}

	@Test public void testCopy_File_File() {
		final File aux1 = new File(TemporaryFiles.getDirectory(), "aux1");
		Assert.assertTrue(Directories.ensure(aux1));
		final String n = TemporaryFiles.create(aux1).getName();
		Assert.assertNotNull(n);
		final File aux2 = new File(TemporaryFiles.getDirectory(), "aux2");
		Assert.assertTrue(Directories.copy(aux1, aux2));
		Assert.assertTrue(Files.exists(new File(aux2, n)));
	}

}
