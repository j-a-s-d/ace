/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.printing;

import org.junit.Assert;
import org.junit.Test;

public class PrintJobsTest {

	@Test public void testSubmitDataAsSimpleDocumentJob_4args() {
		Assert.assertFalse(PrintJobs.submitDataAsSimpleDocumentJob(null, null, null, null));
	}

	@Test public void testSubmitFileAsSimpleDocumentJob_4args() {
		Assert.assertFalse(PrintJobs.submitFileAsSimpleDocumentJob(null, null, null, null));
	}

	@Test public void testSubmitDataAsSimpleDocumentJob_3args() {
		Assert.assertFalse(PrintJobs.submitDataAsSimpleDocumentJob(null, null, null));
	}

	@Test public void testSubmitFileAsSimpleDocumentJob_3args() {
		Assert.assertFalse(PrintJobs.submitFileAsSimpleDocumentJob(null, null, null));
	}

}
