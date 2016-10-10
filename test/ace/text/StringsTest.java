/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import java.io.ByteArrayInputStream;
import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

	private static final String A_VALUE = "123";
	private static final String TEST = "test";

	@Test public void testMake_int() {
		Assert.assertEquals("\0\0\0", Strings.make(3));
	}

	@Test public void testMake_int_char() {
		Assert.assertEquals("qqq", Strings.make(3, 'q'));
	}

	@Test public void testMake_int_String() {
		Assert.assertEquals("-|-|-|", Strings.make(3, "-|"));
	}

	@Test public void testFromByteArray_byteArr() {
		Assert.assertEquals("abcd", Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }));
		Assert.assertEquals(null, Strings.fromByteArray(null));
	}

	@Test public void testFromByteArray_byteArr_String() {
		Assert.assertEquals("abcd", Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, "ASCII"));
		Assert.assertEquals(null, Strings.fromByteArray(null, "EBCDIC"));
	}

	@Test public void testFromByteArray_3args() {
		Assert.assertEquals("bc", Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, 1, 2));
		Assert.assertEquals(null, Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, -1, 2));
		Assert.assertEquals(null, Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, 1, -1));
		Assert.assertEquals(null, Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, 1, 4));
		Assert.assertEquals(null, Strings.fromByteArray(null, 0, 0));
	}

	@Test public void testFromByteArray_4args() {
		Assert.assertEquals("bc", Strings.fromByteArray(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, 1, 2, "ASCII"));
		Assert.assertEquals(null, Strings.fromByteArray(null, 0, 0, "EBCDIC"));
	}

	@Test public void testToByteArray_String() {
		Assert.assertArrayEquals(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, Strings.toByteArray("abcd"));
	}

	@Test public void testToByteArray_String_String() {
		Assert.assertArrayEquals(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }, Strings.toByteArray("abcd", "ASCII"));
	}

	@Test public void testFromInputStream() {
		Assert.assertEquals(Strings.fromInputStream(
			new ByteArrayInputStream(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }
		)), Strings.fromInputStream(Strings.toInputStream("abcd")));
		Assert.assertEquals(null, Strings.fromInputStream(null));
	}

	@Test public void testToInputStream() {
		Assert.assertEquals(Strings.fromInputStream(
			new ByteArrayInputStream(new byte[] { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd' }
		)), Strings.fromInputStream(Strings.toInputStream("abcd")));
		Assert.assertEquals(null, Strings.toInputStream(null));
	}

	@Test public void testEnsure() {
		Assert.assertEquals(A_VALUE, Strings.ensure(A_VALUE));
		final String NIL = null;
		Assert.assertEquals(STRINGS.EMPTY, Strings.ensure(NIL));
	}

	@Test public void testConcat() {
		Assert.assertEquals("A0B1C2", Strings.concat("A0", "B1", "C2"));
		Assert.assertEquals("A0C2", Strings.concat("A0", null, "C2"));
		Assert.assertEquals("D3E4", Strings.concat(new String[] { "D3", "E4" }));
		Assert.assertEquals(STRINGS.EMPTY, Strings.concat(new String[] {}));
		Assert.assertEquals(STRINGS.EMPTY, Strings.concat(null, null));
	}

	@Test public void testHasContent() {
		Assert.assertEquals(false, Strings.hasContent(STRINGS.EMPTY));
		Assert.assertEquals(true, Strings.hasContent(TEST));
	}

	@Test public void testHaveContent() {
		Assert.assertEquals(false, Strings.haveContent(STRINGS.EMPTY, STRINGS.EMPTY));
		Assert.assertEquals(false, Strings.haveContent(TEST, STRINGS.EMPTY));
		Assert.assertEquals(true, Strings.haveContent(TEST, "abcd"));
	}

	@Test public void testSameContent() {
		Assert.assertEquals(true, Strings.sameContent(A_VALUE, "123"));
		Assert.assertEquals(false, Strings.sameContent(A_VALUE, "1234"));
		Assert.assertEquals(false, Strings.sameContent(null, null));
	}

	@Test public void testAreEqual() {
		Assert.assertEquals(true, Strings.sameContent(A_VALUE, "123"));
		Assert.assertEquals(false, Strings.sameContent(A_VALUE, "1234"));
		Assert.assertEquals(true, Strings.areEqual(null, null));
	}

	@Test public void testHasText() {
		Assert.assertEquals(false, Strings.hasText("    "));
		Assert.assertEquals(true, Strings.hasText(TEST));
	}

	@Test public void testHaveText() {
		Assert.assertEquals(false, Strings.haveText("    ", STRINGS.TAB));
		Assert.assertEquals(true, Strings.haveText(TEST, "abcd"));
	}

	@Test public void testIn() {
		Assert.assertEquals(true, Strings.in(A_VALUE, new String[] { A_VALUE, "456", "789" }));
		Assert.assertEquals(false, Strings.in("0", new String[] { A_VALUE, "456", "789" }));
	}

	@Test public void testOccurs_String_char() {
		Assert.assertEquals(3, Strings.occurs("AAABBB", 'A'));
		Assert.assertEquals(4, Strings.occurs("ABBABBABBA", 'A'));
	}

	@Test public void testOccurs_String_String() {
		Assert.assertEquals(2, Strings.occurs("AAABBB", "BB"));
		Assert.assertEquals(3, Strings.occurs("ABBABBABBA", "ABBA"));
	}

	@Test public void testLeftOccurrences() {
		Assert.assertEquals(3, Strings.leftOccurrences("AAABBB", 'A'));
		Assert.assertEquals(0, Strings.leftOccurrences(TEST, '1'));
	}

	@Test public void testRightOccurrences() {
		Assert.assertEquals(3, Strings.rightOccurrences("AAABBB", 'B'));
		Assert.assertEquals(0, Strings.rightOccurrences(TEST, '1'));
	}

	@Test public void testStartsWithIgnoreCase() {
		Assert.assertEquals(true, Strings.startsWithIgnoreCase("AAABBB", "A"));
		Assert.assertEquals(true, Strings.startsWithIgnoreCase("AAABBB", "a"));
		Assert.assertEquals(false, Strings.startsWithIgnoreCase("AAABBB", "B"));
	}

	@Test public void testEndsWithIgnoreCase() {
		Assert.assertEquals(true, Strings.endsWithIgnoreCase("AAABBB", "B"));
		Assert.assertEquals(true, Strings.endsWithIgnoreCase("AAABBB", "b"));
		Assert.assertEquals(false, Strings.endsWithIgnoreCase("AAABBB", "A"));
	}

	@Test public void testPadLeft() {
		Assert.assertEquals("00AAABBB", Strings.padLeft(8, '0', "AAABBB"));
		Assert.assertEquals("AAABBB", Strings.padLeft(4, '0', "AAABBB"));
	}

	@Test public void testPadRight() {
		Assert.assertEquals("AAABBB00", Strings.padRight(8, '0', "AAABBB"));
		Assert.assertEquals("AAABBB", Strings.padRight(4, '0', "AAABBB"));
	}

	@Test public void testDropLeft() {
		Assert.assertEquals("BBB", Strings.dropLeft("AAABBB", 3));
		Assert.assertEquals("AAABBB", Strings.dropLeft("AAABBB", 0));
		Assert.assertEquals(null, Strings.dropLeft("AAABBB", -1));
		Assert.assertEquals(null, Strings.dropLeft("AAABBB", 6));
	}

	@Test public void testDropRight() {
		Assert.assertEquals("AAA", Strings.dropRight("AAABBB", 3));
		Assert.assertEquals("AAABBB", Strings.dropRight("AAABBB", 0));
		Assert.assertEquals(null, Strings.dropRight("AAABBB", -1));
		Assert.assertEquals(null, Strings.dropRight("AAABBB", 6));
	}

	@Test public void testDropBoth() {
		Assert.assertEquals("AB", Strings.dropBoth("AAABBB", 2));
		Assert.assertEquals("AAABBB", Strings.dropBoth("AAABBB", 0));
		Assert.assertEquals(null, Strings.dropBoth("AAABBB", -1));
		Assert.assertEquals(null, Strings.dropBoth("AAABBB", 3));
	}

	@Test public void testStripLeft() {
		Assert.assertEquals("BBB", Strings.stripLeft("AAABBB", 'A'));
		Assert.assertEquals("AAABBB", Strings.stripLeft("AAABBB", '0'));
		Assert.assertEquals(STRINGS.EMPTY, Strings.stripLeft("AAA", 'A'));
	}

	@Test public void testStripRight() {
		Assert.assertEquals("AAA", Strings.stripRight("AAABBB", 'B'));
		Assert.assertEquals("AAABBB", Strings.stripRight("AAABBB", '0'));
		Assert.assertEquals(STRINGS.EMPTY, Strings.stripRight("AAA", 'A'));
	}

	@Test public void testStripBoth() {
		Assert.assertEquals("BBB", Strings.stripBoth("AAABBBAAA", 'A'));
		Assert.assertEquals("AAA", Strings.stripBoth("AAABBB", 'B'));
		Assert.assertEquals("AAABBB", Strings.stripBoth("AAABBB", '0'));
		Assert.assertEquals(STRINGS.EMPTY, Strings.stripBoth("AAA", 'A'));
	}

	@Test public void testStripAll() {
		Assert.assertEquals("BBB", Strings.stripAll("ABAABAABA", "A"));
		Assert.assertEquals(STRINGS.EMPTY, Strings.stripAll("AAA", "A"));
		Assert.assertEquals(STRINGS.EMPTY, Strings.stripAll("", "A"));
		final String NIL = null;
		Assert.assertEquals("AAA", Strings.stripAll("AAA", NIL));
		Assert.assertEquals(null, Strings.stripAll(null, "A"));
	}

}
