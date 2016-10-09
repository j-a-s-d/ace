/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ace.AceTest.class,
    ace.arrays.ImmutableArrayTest.class,
    ace.classes.SemanticVersionTest.class,
    ace.platform.ClassesTest.class,
    ace.platform.PackagesTest.class,
    ace.text.StringsTest.class,
    ace.math.IntegersTest.class,
    ace.math.LongsTest.class,
    ace.math.FloatsTest.class,
    ace.math.DoublesTest.class
})
public class AceTestSuite {
    
}