/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import junit.framework.Assert;
import org.junit.Test;

public class SandboxedTest {

	@Test public void testGo_defaultValueArg() {
		final Sandboxed sb1 = new Sandboxed<String>() {
			@Override public String run() throws Exception {
				int i = 1/0;
				return null;
			}
		};
		Assert.assertFalse(sb1.hadException());
		Assert.assertEquals("testing", sb1.go("testing"));
		Assert.assertTrue(sb1.hadException());
		final Sandboxed sb2 = new Sandboxed<String>() {
			@Override public String run() throws Exception {
				return null;
			}
		};
		Assert.assertFalse(sb2.hadException());
		Assert.assertNull(sb2.go("testing"));
		Assert.assertFalse(sb2.hadException());
	}

	@Test public void testGo_0args() {
		final Sandboxed sb1 = new Sandboxed<String>() {
			@Override public String run() throws Exception {
				int i = 1/0;
				return "test";
			}
		};
		Assert.assertFalse(sb1.hadException());
		Assert.assertNull(sb1.go());
		Assert.assertTrue(sb1.hadException());
		final Sandboxed sb2 = new Sandboxed<String>() {
			@Override public String run() throws Exception {
				return "test";
			}
		};
		Assert.assertFalse(sb2.hadException());
		Assert.assertEquals("test", sb2.go());
		Assert.assertFalse(sb2.hadException());
	}

}