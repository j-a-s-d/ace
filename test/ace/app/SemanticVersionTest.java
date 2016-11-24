/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.app;

import org.junit.Assert;
import org.junit.Test;

public class SemanticVersionTest {

	@Test public void testFromAndToString() {
		Assert.assertEquals("1.2.3", SemanticVersion.fromString("1.2.3").toString());
	}

	@Test public void testGetMajorNumber() {
		Assert.assertEquals(1, new SemanticVersion(1, 2, 3).getMajorNumber());
	}

	@Test public void testGetMinorNumber() {
		Assert.assertEquals(2, new SemanticVersion(1, 2, 3).getMinorNumber());
	}

	@Test public void testGetPatchNumber() {
		Assert.assertEquals(3, new SemanticVersion(1, 2, 3).getPatchNumber());
	}

	@Test public void testFromString() {
		Assert.assertEquals(1, SemanticVersion.fromString("1.0.0").getMajorNumber());
	}

	@Test public void testToString() {
		Assert.assertEquals("1.0.0", new SemanticVersion(1, 0, 0).toString());
	}

	@Test public void testEquals() {
		Assert.assertTrue(new SemanticVersion(1, 0, 0).equals(SemanticVersion.fromString("1.0.0")));
	}

	@Test public void testIsNewer() {
		Assert.assertTrue(new SemanticVersion(1, 0, 1).isNewer(SemanticVersion.fromString("1.0.0")));
	}

	@Test public void testIsOlder() {
		Assert.assertTrue(new SemanticVersion(1, 0, 0).isOlder(SemanticVersion.fromString("1.0.1")));
	}

}
