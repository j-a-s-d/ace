/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ace.AceTest.class,
	ace.app.ArgumentsManagerTest.class,
	ace.app.SemanticVersionTest.class,
	ace.arrays.ByteArraysTest.class,
	ace.arrays.GenericArraysTest.class,
	ace.arrays.ImmutableArrayTest.class,
	ace.html.ShorthandTest.class,
	ace.platform.ClassesTest.class,
	ace.platform.PackagesTest.class,
	ace.math.IntegersTest.class,
	ace.math.LongsTest.class,
	ace.math.FloatsTest.class,
	ace.math.DoublesTest.class,
	ace.platform.ClassesTest.class,
	ace.platform.PackagesTest.class,
	ace.platform.ReflectionTest.class,
	ace.text.StringComposerTest.class,
	ace.text.StringListTest.class,
	ace.text.StringsTest.class
})

public class AceTestSuite {

}
