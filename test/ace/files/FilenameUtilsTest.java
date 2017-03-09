/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import org.junit.Assert;
import org.junit.Test;

public class FilenameUtilsTest {

	@Test public void testSanitizeFilename() {
		Assert.assertEquals("___", FilenameUtils.sanitizeFilename(">|<"));
	}

	@Test public void testStripExtension() {
		Assert.assertEquals("test", FilenameUtils.stripExtension("test.txt"));
	}

	@Test public void testReplaceExtension() {
		Assert.assertEquals("test.md", FilenameUtils.replaceExtension("test.txt", ".md"));
	}

	@Test public void testExtractExtension() {
		Assert.assertEquals(".txt", FilenameUtils.extractExtension("test.txt"));
	}

	@Test public void testExtractFilename_String() {
		Assert.assertEquals("test.txt", FilenameUtils.extractFilename("/abc/def/test.txt"));
	}

	@Test public void testExtractFilename_String_String() {
		Assert.assertEquals("test.txt", FilenameUtils.extractFilename("C:\\abc\\def\\test.txt", "\\"));
	}

	@Test public void testExtractFilenameWithoutExtension() {
		Assert.assertEquals("test", FilenameUtils.extractFilenameWithoutExtension("/abc/def/test.txt"));
	}

	@Test public void testEnsureLastDirectorySeparator_String() {
		Assert.assertEquals("/abc/def/", FilenameUtils.ensureLastDirectorySeparator("/abc/def"));
	}

	@Test public void testEnsureLastDirectorySeparator_String_String() {
		Assert.assertEquals("C:\\abc\\def\\", FilenameUtils.ensureLastDirectorySeparator("C:\\abc\\def", "\\"));
	}

}
