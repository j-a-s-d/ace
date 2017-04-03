/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import java.util.Stack;
import org.junit.Assert;
import org.junit.Test;

public class StacksTest {

	final Stack<String> _stack = new Stack() {
		{
			push("A");
			push("B");
			push("C");
		}
	};

	@Test public void testHasContent() {
		Assert.assertTrue(Stacks.hasContent(_stack));
	}

	@Test public void testMake() {
		Assert.assertNotNull(Stacks.make(1, 2, 3));
	}

	@Test public void testCombine() {
		Assert.assertEquals(4, Stacks.combine(Stacks.make(1, 2), Stacks.make(3, 4)).size());
	}

	@Test public void testGetAsStringArray() {
		Assert.assertArrayEquals(new String[] { "A", "B", "C" }, Stacks.getAsStringArray(_stack));
	}

	@Test public void testHas_Stack_GenericType() {
		Assert.assertTrue(Stacks.has(_stack, "A", "B"));
		Assert.assertFalse(Stacks.has(_stack, "D", "E"));
	}

	@Test public void testHas_Stack_Collection() {
		Assert.assertTrue(Stacks.has(_stack, Stacks.make("A", "B")));
		Assert.assertFalse(Stacks.has(_stack, Stacks.make("D", "E")));
	}

}
