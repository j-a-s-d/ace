/* CRL @ 2016 */

package ace.text;

import org.junit.Assert;
import org.junit.Test;

public class StringComposerTest {

	@Test public void testCapacity() {
		final StringComposer sc = new StringComposer();
		final int ic = sc.capacity();
		sc.append(Strings.make(ic + 1));
		Assert.assertEquals(true, sc.capacity() > ic);
	}

	@Test public void testLength() {
		final StringComposer sc = new StringComposer();
		Assert.assertEquals(0, sc.length());
		sc.append("1234");
		Assert.assertEquals(4, sc.length());
	}

	@Test public void testReset() {
		final StringComposer sc = new StringComposer();
		Assert.assertEquals(0, sc.length());
		sc.append("1234");
		Assert.assertEquals(4, sc.length());
		sc.reset();
		Assert.assertEquals(0, sc.length());
	}

	@Test public void testAppend_String() {
		final StringComposer sc = new StringComposer();
		sc.append("1234");
		Assert.assertEquals("1234", sc.toString());
	}

	@Test public void testAppend_Character() {
		final StringComposer sc = new StringComposer();
		sc.append(new Character('A'));
		Assert.assertEquals("A", sc.toString());
	}

	@Test public void testAppend_char() {
		final StringComposer sc = new StringComposer();
		sc.append('A');
		Assert.assertEquals("A", sc.toString());
	}

	@Test public void testAppend_charArr() {
		final StringComposer sc = new StringComposer();
		sc.append(new char[] { 'A', 'B', 'C' });
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_CharSequence() {
		final StringComposer sc = new StringComposer();
		sc.append("ABC".subSequence(0, 3));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_StringBuffer() {
		final StringComposer sc = new StringComposer();
		sc.append(new StringBuffer("ABC"));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_StringBuilder() {
		final StringComposer sc = new StringComposer();
		sc.append(new StringBuilder("ABC"));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_StringComposer() {
		final StringComposer sc = new StringComposer();
		sc.append(new StringComposer("ABC"));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_StringArr() {
		final StringComposer sc = new StringComposer();
		sc.append(new String[] { "A", "B", "C" });
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_boolean_String() {
		final StringComposer sc = new StringComposer();
		sc.append(true, "ABC");
		Assert.assertEquals("ABC", sc.toString());
		sc.append(false, "ABC");
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_boolean_Character() {
		final StringComposer sc = new StringComposer();
		sc.append(true, new Character('A'));
		Assert.assertEquals("A", sc.toString());
		sc.append(false, new Character('A'));
		Assert.assertEquals("A", sc.toString());
	}

	@Test public void testAppend_boolean_char() {
		final StringComposer sc = new StringComposer();
		sc.append(true, 'A');
		Assert.assertEquals("A", sc.toString());
		sc.append(false, 'A');
		Assert.assertEquals("A", sc.toString());
	}

	@Test public void testAppend_boolean_charArr() {
		final StringComposer sc = new StringComposer();
		sc.append(true, new char[] { 'A', 'B', 'C' });
		Assert.assertEquals("ABC", sc.toString());
		sc.append(false, new char[] { 'A', 'B', 'C' });
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_boolean_CharSequence() {
		final StringComposer sc = new StringComposer();
		sc.append(true, "ABC".subSequence(0, 3));
		Assert.assertEquals("ABC", sc.toString());
		sc.append(false, "ABC".subSequence(0, 3));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_boolean_StringBuffer() {
		final StringComposer sc = new StringComposer();
		sc.append(true, new StringBuffer("ABC"));
		Assert.assertEquals("ABC", sc.toString());
		sc.append(false, new StringBuffer("ABC"));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_boolean_StringBuilder() {
		final StringComposer sc = new StringComposer();
		sc.append(true, new StringBuilder("ABC"));
		Assert.assertEquals("ABC", sc.toString());
		sc.append(false, new StringBuilder("ABC"));
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testAppend_boolean_StringArr() {
		final StringComposer sc = new StringComposer();
		sc.append(true, new String[] { "A", "B", "C" });
		Assert.assertEquals("ABC", sc.toString());
		sc.append(false, new String[] { "A", "B", "C" });
		Assert.assertEquals("ABC", sc.toString());
	}

	@Test public void testToString() {
		Assert.assertEquals("ABC", new StringComposer("ABC").toString());
	}

}