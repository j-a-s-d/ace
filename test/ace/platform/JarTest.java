/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.constants.STRINGS;
import ace.files.TemporaryFiles;
import org.junit.Assert;
import org.junit.Test;

public class JarTest {

	final Jar _jar = Jars.getCurrent();

	@Test public void testReload() {
		try {
			_jar.reload();
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test public void testGetRequester() {
		Assert.assertNotNull(_jar.getRequester());
	}

	@Test public void testGetClassLoader() {
		Assert.assertNotNull(_jar.getClassLoader());
	}

	@Test public void testGetFile() {
		Assert.assertNotNull(_jar.getFile());
	}

	@Test public void testGetMetaInfo() {
		Assert.assertNotNull(_jar.getMetaInfo());
	}

	@Test public void testGetContents() {
		Assert.assertNotNull(_jar.getContents());
	}

	@Test public void testGetPackages() {
		Assert.assertNotNull(_jar.getPackages());
	}

	@Test public void testHasPackage() {
		Assert.assertFalse(_jar.hasPackage(STRINGS.EMPTY));
	}

	@Test public void testGetPackageContents() {
		Assert.assertNotNull(_jar.getPackageContents(STRINGS.EMPTY));
	}

	@Test public void testGetPackageClasses() {
		Assert.assertNotNull(_jar.getPackageClasses(STRINGS.EMPTY));
	}

	@Test public void testGetResourceAsString() {
		Assert.assertNotNull(_jar.getResourceAsString(STRINGS.EMPTY));
	}

	@Test public void testGetResourceAsStream() {
		Assert.assertNotNull(_jar.getResourceAsStream(STRINGS.EMPTY));
	}

	@Test public void testExtractResourceToFile_String_String() {
		Assert.assertFalse(_jar.extractResourceToFile(null, TemporaryFiles.getDirectoryPath()));
	}

	@Test public void testExtractResourceToFile_String_File() {
		Assert.assertFalse(_jar.extractResourceToFile(null, TemporaryFiles.getDirectory()));
	}

	@Test public void testExtractPackageResourcesToFolder_String_String() {
		Assert.assertFalse(_jar.extractPackageResourcesToFolder(null, TemporaryFiles.getDirectoryPath()));
	}

	@Test public void testExtractPackageResourcesToFolder_String_File() {
		Assert.assertFalse(_jar.extractPackageResourcesToFolder(null, TemporaryFiles.getDirectory()));
	}

	@Test public void testGetContentAsString() {
		Assert.assertNull(_jar.getContentAsString(STRINGS.EMPTY));
	}

	@Test public void testGetContentAsByteArray() {
		Assert.assertNull(_jar.getContentAsByteArray(STRINGS.EMPTY));
	}

	@Test public void testExtractContentToFile_String_String() {
		Assert.assertFalse(_jar.extractContentToFile(null, TemporaryFiles.getDirectoryPath()));
	}

	@Test public void testExtractContentToFile_String_File() {
		Assert.assertFalse(_jar.extractContentToFile(null, TemporaryFiles.getDirectory()));
	}

	@Test public void testExtractPackageContentsToFolder_String_String() {
		Assert.assertFalse(_jar.extractPackageContentsToFolder(null, TemporaryFiles.getDirectoryPath()));
	}

	@Test public void testExtractPackageContentsToFolder_String_File() {
		Assert.assertFalse(_jar.extractPackageContentsToFolder(null, TemporaryFiles.getDirectory()));
	}

	@Test public void testGetClass_String_Class() {
		Assert.assertNull(_jar.getClass(STRINGS.EMPTY, JarTest.class));
	}

	@Test public void testGetClass_String() {
		Assert.assertNull(_jar.getClass(STRINGS.EMPTY));
	}

}
