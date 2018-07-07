/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import junit.framework.Assert;
import org.junit.Test;

public class ReflectionPropertyHelperTest {

	class TestClass {
		private String _something = "nothing";
		protected String getSomething() {
			return _something;
		}
		protected void setSomething(final String something) {
			_something = something;
		}
	}

	@Test public void test() {
		final ReflectionPropertyHelper<String> rph = new ReflectionPropertyHelper<String>(TestClass.class, "getSomething", "setSomething", String.class);
		Assert.assertTrue(rph.initialize());
		final TestClass testObject = new TestClass();
		Assert.assertEquals("nothing", rph.get(testObject));
		rph.set(testObject, "blah");
		Assert.assertEquals("blah", rph.get(testObject));
	}

}