/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.files;

import org.junit.Assert;
import org.junit.Test;

public class FilenameValidatorTest {
	
	@Test public void testMakeAllFilesValidator() {
		Assert.assertNotNull(FilenameValidator.makeAllFilesValidator());
	}

	@Test public void testValidate() {
		Assert.assertNotNull(FilenameValidator.makeExtensionsValidator(".txt", "md"));
	}

	@Test public void testMakeExtensionsValidator() {
		final FilenameValidator fv = FilenameValidator.makeExtensionsValidator(".txt", "md");
		Assert.assertTrue(fv.validate("test.txt"));
		Assert.assertTrue(fv.validate("test.md"));
		Assert.assertTrue(fv.validate(".txt"));
		Assert.assertFalse(fv.validate("test.json"));
	}
	
}
