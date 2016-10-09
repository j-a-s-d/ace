/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.app;

import org.junit.Assert;
import org.junit.Test;

public class ArgumentsManagerTest {

	private static final String DUMMY = "dummy";
	private static final String DESCRIPTION = "dummy usage description";

	@Test public void testAddOption() {
		Assert.assertEquals(1, new ArgumentsManager()
			.addOption(DUMMY, DESCRIPTION)
			.countRegisteredOptions());
	}

	@Test public void testAddParameter() {
		Assert.assertEquals(1, new ArgumentsManager()
			.addParameter(DUMMY, DESCRIPTION)
			.countRegisteredParameters());
	}

	@Test public void testAddValuedOption() {
		Assert.assertEquals(1, new ArgumentsManager()
			.addValuedOption(DUMMY, DESCRIPTION, "test")
			.countRegisteredOptions());
	}

	@Test public void testAddValuedParameter() {
		Assert.assertEquals(1, new ArgumentsManager()
			.addValuedParameter(DUMMY, DESCRIPTION)
			.countRegisteredParameters());
	}

	@Test public void testRemove() {
		Assert.assertEquals(0, new ArgumentsManager() {
			{
				addParameter(DUMMY, DESCRIPTION);
				if (!remove(DUMMY)) {
					Assert.fail();
				}
			}
		}.countRegisteredParameters());
	}

	@Test public void testPrefixes() {
		final ArgumentsManager am1 = new ArgumentsManager()
			.addOption(DUMMY, DESCRIPTION);
		if (am1.process(new String[] { "--dummy" })) {
			Assert.assertEquals(true, am1.has(DUMMY));
		} else {
			Assert.fail();
		}
	}

	@Test public void testHadAbsentParameter() {
		final ArgumentsManager am = new ArgumentsManager()
			.addParameter(DUMMY, DESCRIPTION);
		if (!am.process(new String[] {})) {
			Assert.assertEquals(true, am.hadAbsentParameter());
		} else {
			Assert.fail();
		}
	}

	@Test public void testGetAbsentParameterName() {
		final ArgumentsManager am = new ArgumentsManager()
			.addParameter(DUMMY, DESCRIPTION);
		if (!am.process(new String[] {})) {
			Assert.assertEquals(DUMMY, am.getAbsentParameterName());
		} else {
			Assert.fail();
		}
	}

	@Test public void testGetAbsentParameterUsage() {
		final ArgumentsManager am = new ArgumentsManager()
			.addParameter(DUMMY, DESCRIPTION);
		if (!am.process(new String[] {})) {
			Assert.assertEquals(DESCRIPTION, am.getAbsentParameterUsage());
		} else {
			Assert.fail();
		}
	}

	@Test public void testHas() {
		final ArgumentsManager am1 = new ArgumentsManager()
			.addOption(DUMMY, DESCRIPTION);
		if (am1.process(new String[] { "--dummy" })) {
			Assert.assertEquals(true, am1.has(DUMMY));
		} else {
			Assert.fail();
		}
	}

	@Test public void testGet() {
		final ArgumentsManager am1 = new ArgumentsManager()
			.addValuedOption(DUMMY, DESCRIPTION, "1");
		if (am1.process(new String[] { "--dummy", "2" })) {
			Assert.assertEquals("2", am1.get(DUMMY));
		} else {
			Assert.fail();
		}
	}

}
