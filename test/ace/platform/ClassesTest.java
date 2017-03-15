/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import java.io.File;
import java.net.URLClassLoader;
import org.junit.*;

public class ClassesTest {

	@Test public void testIsClassLoaded() {
		Assert.assertTrue(Classes.isClassLoaded("java.lang.String"));
		Assert.assertFalse(Classes.isClassLoaded("blah"));
	}

	@Test public void testInstantiate_Class() {
		Assert.assertNotNull(Classes.instantiate(java.lang.String.class));
	}

	@Test public void testInstantiate_String() {
		Assert.assertNotNull(Classes.instantiate("java.lang.String"));
	}

	@Test public void testInstantiate_3args_1() {
		Assert.assertEquals("test", Classes.instantiate("java.lang.String", new Class[] { java.lang.String.class }, new Object[] { "test" }));
	}

	@Test public void testInstantiate_3args_2() {
		Assert.assertEquals("test", Classes.instantiate(java.lang.String.class, new Class[] { java.lang.String.class }, new Object[] { "test" }));
	}

	@Test public void testListHierarchy() {
		Assert.assertNotNull(Classes.listHierarchy(java.lang.String.class));
		Assert.assertNull(Classes.listHierarchy(null));
	}

	@Test public void testGetShortName() {
		Assert.assertEquals("String", Classes.getShortName(java.lang.String.class.getCanonicalName()));
		Assert.assertEquals("String", Classes.getShortName(java.lang.String.class.getName()));
		Assert.assertEquals("String", Classes.getShortName("String"));
		Assert.assertEquals(null, Classes.getShortName(""));
		Assert.assertEquals(null, Classes.getShortName(null));
	}

	@Test public void testLoadFromFile() {
		final URLClassLoader l = Classes.makeURLClassLoaderFromDirectory("./build/classes");
		final Class<?> c = Classes.loadFromFile(l,
			"ace.Ace" // NOTE: here is the canonical name, that is ace.Something instead of ./ace/Something.class
		);
		Assert.assertNotNull(c);
	}

	@Test public void testMakeURLClassLoaderFromDirectory_String() {
		Assert.assertNotNull(Classes.makeURLClassLoaderFromDirectory("./build/classes"));
	}

	@Test public void testMakeURLClassLoaderFromDirectory_File() {
		Assert.assertNotNull(Classes.makeURLClassLoaderFromDirectory(new File("./build/classes")));
	}

}
