/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.interfaces.Treater;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.junit.Assert;
import org.junit.Test;

public class TextFilesTest {

	private static String CONTENT = "hello world!";
	private static String NEW_CONTENT = "test testing";
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

	@Test public void testRead_String() {
		Assert.assertEquals(CONTENT, TextFiles.read(tempFile.getAbsolutePath()));
	}

	@Test public void testRead_File() {
		Assert.assertEquals(CONTENT, TextFiles.read(tempFile));
	}

	@Test public void testRead_File_String() {
		Assert.assertEquals(CONTENT, TextFiles.read(tempFile, "utf8"));
	}

	@Test public void testRead_String_String() {
		Assert.assertEquals(CONTENT, TextFiles.read(tempFile.getAbsolutePath(), "utf8"));
	}

	@Test public void testWrite_3args_1() {
		Assert.assertTrue(TextFiles.write(tempFile, NEW_CONTENT, "utf8"));
		Assert.assertEquals(NEW_CONTENT, TextFiles.read(tempFile, "utf8"));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT, "utf8"));
	}

	@Test public void testWrite_3args_2() {
		Assert.assertTrue(TextFiles.write(tempFile.getAbsolutePath(), NEW_CONTENT, "utf8"));
		Assert.assertEquals(NEW_CONTENT, TextFiles.read(tempFile.getAbsolutePath(), "utf8"));
		Assert.assertTrue(TextFiles.write(tempFile.getAbsolutePath(), CONTENT, "utf8"));
	}

	@Test public void testWrite_String_String() {
		Assert.assertTrue(TextFiles.write(tempFile.getAbsolutePath(), NEW_CONTENT));
		Assert.assertEquals(NEW_CONTENT, TextFiles.read(tempFile.getAbsolutePath()));
		Assert.assertTrue(TextFiles.write(tempFile.getAbsolutePath(), CONTENT));
	}

	@Test public void testWrite_File_String() {
		Assert.assertTrue(TextFiles.write(tempFile, NEW_CONTENT));
		Assert.assertEquals(NEW_CONTENT, TextFiles.read(tempFile));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT));
	}

	@Test public void testAppend_String_String() {
		Assert.assertTrue(TextFiles.append(tempFile.getAbsolutePath(), NEW_CONTENT));
		Assert.assertEquals(CONTENT + NEW_CONTENT, TextFiles.read(tempFile));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT));
	}

	@Test public void testAppend_File_String() {
		Assert.assertTrue(TextFiles.append(tempFile, NEW_CONTENT));
		Assert.assertEquals(CONTENT + NEW_CONTENT, TextFiles.read(tempFile));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT));
	}

	@Test public void testAppend_3args_1() {
		Assert.assertTrue(TextFiles.append(tempFile.getAbsolutePath(), NEW_CONTENT, "utf8"));
		Assert.assertEquals(CONTENT + NEW_CONTENT, TextFiles.read(tempFile, "utf8"));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT));
	}

	@Test public void testAppend_3args_2() {
		Assert.assertTrue(TextFiles.append(tempFile, NEW_CONTENT, "utf8"));
		Assert.assertEquals(CONTENT + NEW_CONTENT, TextFiles.read(tempFile, "utf8"));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT));
	}

	@Test public void testTranscode_4args_1() {
		Assert.assertTrue(TextFiles.transcode(tempFile, "utf8", tempFile2, "UTF-16BE"));
		Assert.assertEquals(CONTENT, TextFiles.read(tempFile, "utf8"));
		try {
			Assert.assertEquals(new String(CONTENT.getBytes("UTF-16BE"), "UTF-16BE"), TextFiles.read(tempFile2, "UTF-16BE"));
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testTranscode_4args_2() {
		Assert.assertTrue(TextFiles.transcode(tempFile.getAbsolutePath(), "UTF-8", tempFile2.getAbsolutePath(), "UTF-16LE"));
		Assert.assertEquals(CONTENT, TextFiles.read(tempFile, "UTF-8"));
		try {
			Assert.assertEquals(new String(CONTENT.getBytes("UTF-16LE"), "UTF-16LE"), TextFiles.read(tempFile2, "UTF-16LE"));
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void testTreat() {
		Assert.assertTrue(TextFiles.treat(tempFile, new Treater<String>() {
			/*OVerride*/ public String treat(final String item) {
				return item.toUpperCase();
			}
		}));
		Assert.assertEquals(CONTENT.toUpperCase(), TextFiles.read(tempFile));
		Assert.assertTrue(TextFiles.write(tempFile, CONTENT));
	}

}
