/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.containers.Lists;
import ace.randomness.GUID;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.junit.Assert;
import org.junit.Test;

public class FilesTest {

	private static String CONTENT = "hello world!";
	private static File tempFile;
	private static File tempFile2;

	static {
		try {
			new BufferedWriter(new FileWriter(tempFile = File.createTempFile(
				"test", ".tmp", new File(System.getProperty("java.io.tmpdir"))
			), true)) {{
				write(CONTENT);
				close();
			}};
			tempFile.deleteOnExit();
			tempFile2 = File.createTempFile(
				"test", ".tmp", new File(System.getProperty("java.io.tmpdir"))
			);
			tempFile2.deleteOnExit();
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testHasContent() {
		Assert.assertTrue(Files.hasContent(tempFile));
	}

	@Test public void testMake_File_String() {
		final File tf = Files.make(new File(System.getProperty("java.io.tmpdir")), GUID.makeAsString());
		tf.deleteOnExit();
		Assert.assertNotNull(tf);
	}

	@Test public void testMake_String_String() {
		final File tf = Files.make(System.getProperty("java.io.tmpdir"), GUID.makeAsString());
		tf.deleteOnExit();
		Assert.assertNotNull(tf);
	}

	@Test public void testExists() {
		Assert.assertTrue(Files.exists(tempFile));
	}

	@Test public void testExist_List() {
		Assert.assertTrue(Files.exist(Lists.make(tempFile, tempFile2)));
	}

	@Test public void testExist_FileArr() {
		Assert.assertTrue(Files.exist(tempFile, tempFile2));
	}

	@Test public void testEnsure_File() {
		Assert.assertTrue(Files.ensure(tempFile));
	}

	@Test public void testEnsure_String() {
		Assert.assertTrue(Files.ensure(tempFile.getAbsolutePath()));
	}

	@Test public void testHasSameBytes() {
		Assert.assertFalse(Files.hasSameBytes(tempFile, tempFile2));
	}

	@Test public void testCopy_File_File() {
		Assert.assertTrue(Files.copy(tempFile, new File(System.getProperty("java.io.tmpdir"), GUID.makeAsString())));
	}

	@Test public void testCopy_3args() {
		Assert.assertTrue(Files.copy(tempFile, new File(System.getProperty("java.io.tmpdir"), GUID.makeAsString()), true));
	}

}
