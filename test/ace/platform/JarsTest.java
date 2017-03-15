/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.constants.STRINGS;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class JarsTest {

	@Test public void testHasCurrent() {
		Assert.assertTrue(Jars.hasCurrent());
	}

	@Test public void testGetCurrent() {
		Assert.assertNotNull(Jars.getCurrent());
	}

	@Test public void testPackageNameToPackagePath() {
		Assert.assertEquals("/a/b/c/", Jars.packageNameToPackagePath("a.b.c"));
	}

	@Test public void testPackagePathToPackageName() {
		Assert.assertEquals("a.b.c", Jars.packagePathToPackageName("a/b/c/"));
		Assert.assertEquals("a.b.c", Jars.packagePathToPackageName("a/b/c"));
		Assert.assertEquals("a.b.c", Jars.packagePathToPackageName("/a/b/c/"));
		Assert.assertEquals("a.b.c", Jars.packagePathToPackageName("/a/b/c"));
	}

	@Test public void testClassNameToClassPath() {
		Assert.assertEquals("/a/b/c/D", Jars.classNameToClassPath("a.b.c.D"));
	}

	@Test public void testClassPathToClassName() {
		Assert.assertEquals("a.b.c.D", Jars.classPathToClassName("a/b/c/D"));
	}

	@Test public void testGetJarForClass() {
		Assert.assertNotNull(Jars.getJarForClass(ace.Ace.class));
		Assert.assertNotEquals(Jars.getJarForClass(java.lang.String.class), Jars.getJarForClass(ace.Ace.class));
	}

	@Test public void testGetClassLoaderForJarFile_String_Class() {
		Assert.assertNotNull(Jars.getClassLoaderForJarFile(new File(STRINGS.EMPTY), JarsTest.class));
	}

	@Test public void testGetClassLoaderForJarFile_File_Class() {
		Assert.assertNotNull(Jars.getClassLoaderForJarFile(STRINGS.EMPTY, JarsTest.class));
	}

	@Test public void testGetClassFromJarFile_3args_1() {
		Assert.assertNull(Jars.getClassFromJarFile(new File(STRINGS.EMPTY), STRINGS.EMPTY, JarsTest.class));
	}

	@Test public void testGetClassFromJarFile_3args_2() {
		Assert.assertNull(Jars.getClassFromJarFile(STRINGS.EMPTY, STRINGS.EMPTY, JarsTest.class));
	}

}
