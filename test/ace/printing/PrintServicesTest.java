/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.printing;

import org.junit.Assert;
import org.junit.Test;

public class PrintServicesTest {

	@Test public void testList() {
		Assert.assertNotNull(PrintServices.list());
	}

	@Test public void testListNames_0args() {
		Assert.assertNotNull(PrintServices.listNames());
	}

	@Test public void testListNames_DocFlavor() {
		Assert.assertNotNull(PrintServices.listNames(null));
	}

	@Test public void testListNames_DocFlavor_PrintRequestAttributeSet() {
		Assert.assertNotNull(PrintServices.listNames(null, null));
	}

	@Test public void testGetByName() {
		Assert.assertNull(PrintServices.getByName(null));
	}

	@Test public void testPickBest() {
		try {
			PrintServices.pickBest(null, null);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
