/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.files.BinaryFiles;
import org.junit.Assert;
import org.junit.Test;

public class BinaryClassLoaderTest {

	final BinaryClassLoader _l = new BinaryClassLoader();

	@Test public void test() {
		try {
			Assert.assertTrue(_l.registerClass("ace.Ace", BinaryFiles.read("./build/classes/ace/Ace.class")));
			Class<?> c = _l.findClass("ace.Ace");
			Assert.assertNotNull(c);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
