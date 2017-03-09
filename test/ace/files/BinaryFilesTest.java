/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import ace.arrays.ByteArrays;
import ace.binary.Streams;
import ace.interfaces.Treater;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.junit.Assert;
import org.junit.Test;

public class BinaryFilesTest {
	
	private static byte[] CONTENT = "hello world!".getBytes();
	private static byte[] NEW_CONTENT = "test testing".getBytes();
	private static File tempFile;
	private static File tempFile2;

	static {
		try {
			new DataOutputStream(new FileOutputStream(tempFile = File.createTempFile(
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
		Assert.assertArrayEquals(CONTENT, BinaryFiles.read(tempFile.getAbsolutePath()));
	}

	@Test public void testRead_File() {
		Assert.assertArrayEquals(CONTENT, BinaryFiles.read(tempFile));
	}

	@Test public void testRead_3args_1() {
		Assert.assertArrayEquals(CONTENT, BinaryFiles.read(tempFile.getAbsolutePath(), 0L, (int) CONTENT.length));
	}

	@Test public void testRead_3args_2() {
		Assert.assertArrayEquals(CONTENT, BinaryFiles.read(tempFile, 0L, (int) CONTENT.length));
	}

	@Test public void testRead_3args_3() {
		Assert.assertTrue(Streams.hasSameBytes(
			new BufferedInputStream(new ByteArrayInputStream(CONTENT)),
			new BufferedInputStream(BinaryFiles.read(tempFile.getAbsolutePath(), (int) CONTENT.length, 0L))
		));
	}

	@Test public void testRead_3args_4() {
		Assert.assertTrue(Streams.hasSameBytes(
			new BufferedInputStream(new ByteArrayInputStream(CONTENT)),
			new BufferedInputStream(BinaryFiles.read(tempFile, (int) CONTENT.length, 0L))
		));
	}

	@Test public void testWrite_String_byteArr() {
		Assert.assertTrue(BinaryFiles.write(tempFile.getAbsolutePath(), NEW_CONTENT));
		Assert.assertArrayEquals(NEW_CONTENT, BinaryFiles.read(tempFile.getAbsolutePath()));
		Assert.assertTrue(BinaryFiles.write(tempFile.getAbsolutePath(), CONTENT));
	}

	@Test public void testWrite_File_byteArr() {
		Assert.assertTrue(BinaryFiles.write(tempFile, NEW_CONTENT));
		Assert.assertArrayEquals(NEW_CONTENT, BinaryFiles.read(tempFile));
		Assert.assertTrue(BinaryFiles.write(tempFile, CONTENT));
	}

	@Test public void testWrite_String_InputStream() {
		Assert.assertTrue(BinaryFiles.write(tempFile.getAbsolutePath(), new ByteArrayInputStream(NEW_CONTENT)));
		Assert.assertArrayEquals(NEW_CONTENT, BinaryFiles.read(tempFile.getAbsolutePath()));
		Assert.assertTrue(BinaryFiles.write(tempFile.getAbsolutePath(), new ByteArrayInputStream(CONTENT)));
	}

	@Test public void testWrite_File_InputStream() {
		Assert.assertTrue(BinaryFiles.write(tempFile, new ByteArrayInputStream(NEW_CONTENT)));
		Assert.assertArrayEquals(NEW_CONTENT, BinaryFiles.read(tempFile));
		Assert.assertTrue(BinaryFiles.write(tempFile, new ByteArrayInputStream(CONTENT)));
	}

	@Test public void testAppend_String_byteArr() {
		Assert.assertTrue(BinaryFiles.append(tempFile.getAbsolutePath(), NEW_CONTENT));
		Assert.assertArrayEquals(ByteArrays.concat(CONTENT, NEW_CONTENT), BinaryFiles.read(tempFile.getAbsolutePath()));
		Assert.assertTrue(BinaryFiles.write(tempFile.getAbsolutePath(), CONTENT));
	}

	@Test public void testAppend_File_byteArr() {
		Assert.assertTrue(BinaryFiles.append(tempFile, NEW_CONTENT));
		Assert.assertArrayEquals(ByteArrays.concat(CONTENT, NEW_CONTENT), BinaryFiles.read(tempFile));
		Assert.assertTrue(BinaryFiles.write(tempFile, CONTENT));
	}

	@Test public void testAppend_String_InputStream() {
		Assert.assertTrue(BinaryFiles.append(tempFile.getAbsolutePath(), new ByteArrayInputStream(NEW_CONTENT)));
		Assert.assertArrayEquals(ByteArrays.concat(CONTENT, NEW_CONTENT), BinaryFiles.read(tempFile.getAbsolutePath()));
		Assert.assertTrue(BinaryFiles.write(tempFile.getAbsolutePath(), new ByteArrayInputStream(CONTENT)));
	}

	@Test public void testAppend_File_InputStream() {
		Assert.assertTrue(BinaryFiles.append(tempFile, new ByteArrayInputStream(NEW_CONTENT)));
		Assert.assertArrayEquals(ByteArrays.concat(CONTENT, NEW_CONTENT), BinaryFiles.read(tempFile));
		Assert.assertTrue(BinaryFiles.write(tempFile, new ByteArrayInputStream(CONTENT)));
	}

	@Test public void testTreat() {
		Assert.assertTrue(BinaryFiles.treat(tempFile, new Treater<byte[]>() {
			/*OVerride*/ public byte[] treat(final byte[] bytes) {
				return ByteArrays.invertedCopy(bytes, 0, bytes.length);
			}
		}));
		Assert.assertArrayEquals(ByteArrays.invertedCopy(CONTENT, 0, CONTENT.length), BinaryFiles.read(tempFile));
		Assert.assertTrue(BinaryFiles.write(tempFile, new ByteArrayInputStream(CONTENT)));
	}

	@Test public void testCopySegmentToStream() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Assert.assertTrue(BinaryFiles.copySegmentToStream(tempFile, 0, 5, baos));
		Assert.assertArrayEquals(baos.toByteArray(), ByteArrays.copy(BinaryFiles.read(tempFile), 0, 5));
		
	}

	@Test public void testReadSegment() {
		Assert.assertArrayEquals(ByteArrays.copy(CONTENT, 0, 5), BinaryFiles.readSegment(tempFile, 0, 5));
	}

}
