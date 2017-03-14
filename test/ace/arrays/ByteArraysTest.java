/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.constants.BYTES;
import org.junit.Assert;
import org.junit.Test;

public class ByteArraysTest {

	private static final byte[] DUMMY = new byte[] { (byte) 0x01, (byte) 0x02, (byte) 0x03 };
	private static final byte[] DUMMY2 = new byte[] { (byte) 0x04, (byte) 0x05, (byte) 0x06 };
	private static final byte[] DUMMY3 = new byte[] { (byte) 0x07, (byte) 0x08, (byte) 0x09 };

	@Test public void testMake() {
		Assert.assertArrayEquals(DUMMY, ByteArrays.make((byte) 0x01, (byte) 0x02, (byte) 0x03));
		Assert.assertArrayEquals(BYTES.EMPTY, ByteArrays.make());
	}

	@SuppressWarnings({ "ConfusingArrayVararg", "PrimitiveArrayArgumentToVariableArgMethod" })
	@Test public void testConcat() {
		Assert.assertArrayEquals(new byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06
		}, ByteArrays.concat(DUMMY, DUMMY2));
		Assert.assertArrayEquals(new byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09
		}, ByteArrays.concat(DUMMY, DUMMY2, DUMMY3));
		Assert.assertArrayEquals(DUMMY, ByteArrays.concat(DUMMY, BYTES.EMPTY));
		Assert.assertArrayEquals(DUMMY, ByteArrays.concat(DUMMY));
		Assert.assertArrayEquals(null, ByteArrays.concat());
	}

	@Test public void testCopy() {
		Assert.assertArrayEquals(new byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x03
		}, ByteArrays.copy(DUMMY, 0, 3));
		Assert.assertArrayEquals(DUMMY, ByteArrays.copy(new byte[] {
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04
		}, 1, 3));
	}

	@Test public void testInvertedCopy() {
		Assert.assertArrayEquals(new byte[] {
			(byte) 0x03, (byte) 0x02, (byte) 0x01
		}, ByteArrays.invertedCopy(DUMMY, 0, 3));
		Assert.assertArrayEquals(DUMMY, ByteArrays.invertedCopy(new byte[] {
			(byte) 0x04, (byte) 0x03, (byte) 0x02, (byte) 0x01, (byte) 0x00
		}, 1, 3));
	}

	@Test public void testHasContent() {
		Assert.assertEquals(true, ByteArrays.hasContent(DUMMY));
		Assert.assertEquals(false, ByteArrays.hasContent(BYTES.EMPTY));
		Assert.assertEquals(false, ByteArrays.hasContent(null));
	}

	@Test public void testIndexOf_3args() {
		final byte[] aux = new byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09
		};
		Assert.assertEquals(3, ByteArrays.indexOf(aux, 0, DUMMY2));
		Assert.assertEquals(3, ByteArrays.indexOf(aux, 2, DUMMY2));
		Assert.assertEquals(-1, ByteArrays.indexOf(aux, 4, DUMMY2));
		Assert.assertEquals(-1, ByteArrays.indexOf(null, 4, DUMMY2));
	}

	@Test public void testIndexOf_byteArr_byteArr() {
		final byte[] aux = new byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09
		};
		Assert.assertEquals(3, ByteArrays.indexOf(aux, DUMMY2));
		Assert.assertEquals(-1, ByteArrays.indexOf(null, DUMMY2));
		Assert.assertEquals(-1, ByteArrays.indexOf(aux, null));
	}

	@Test public void testIndexesOf() {
		final byte[] aux = new byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x11, (byte) 0x12, (byte) 0x05, (byte) 0x11, (byte) 0x12, (byte) 0x08, (byte) 0x09
		};
		Assert.assertArrayEquals(new int[] { 2, 5 }, ByteArrays.indexesOf(aux, new byte[] { (byte) 0x11, (byte) 0x12 }));
		Assert.assertArrayEquals(new int[] { 8 }, ByteArrays.indexesOf(aux, new byte[] { (byte) 0x09 }));
		Assert.assertArrayEquals(new int[] {}, ByteArrays.indexesOf(aux, new byte[] { (byte) 0xDD, (byte) 0xAA }));
		Assert.assertArrayEquals(new int[] {}, ByteArrays.indexesOf(null, new byte[] { (byte) 0xDD, (byte) 0xAA }));
		Assert.assertArrayEquals(new int[] {}, ByteArrays.indexesOf(aux, null));
	}

	@Test public void testWriteBigEndianShort() {
		final byte[] aux = new byte[] { (byte) 0x00, (byte) 0x00 };
		ByteArrays.writeBigEndianShort(aux, 0, (short) 0xFEDC);
		Assert.assertArrayEquals(new byte[] { (byte) 0xFE, (byte) 0xDC }, aux);
	}

	@Test public void testReadBigEndianShort() {
		final byte[] aux = new byte[] { (byte) 0x89, (byte) 0xAB };
		Assert.assertEquals((short) 0x89AB, ByteArrays.readBigEndianShort(aux, 0));
	}

	@Test public void testWriteLittleEndianShort() {
		final byte[] aux = new byte[] { (byte) 0x00, (byte) 0x00 };
		ByteArrays.writeLittleEndianShort(aux, 0, (short) 0xFEDC);
		Assert.assertArrayEquals(new byte[] { (byte) 0xDC, (byte) 0xFE }, aux);
	}

	@Test public void testReadLittleEndianShort() {
		final byte[] aux = new byte[] { (byte) 0xAB, (byte) 0x89 };
		Assert.assertEquals((short) 0x89AB, ByteArrays.readLittleEndianShort(aux, 0));
	}

	@Test public void testWriteBigEndianInt() {
		final byte[] aux = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		ByteArrays.writeBigEndianInt(aux, 0, 0xFEDCBA98);
		Assert.assertArrayEquals(new byte[] { (byte) 0xFE, (byte) 0xDC, (byte) 0xBA, (byte) 0x98 }, aux);
	}

	@Test public void testReadBigEndianInt() {
		final byte[] aux = new byte[] { (byte) 0x89, (byte) 0xAB, (byte) 0xDC, (byte) 0xFE };
		Assert.assertEquals(0x89ABDCFE, ByteArrays.readBigEndianInt(aux, 0));
	}

	@Test public void testWriteLittleEndianInt() {
		final byte[] aux = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		ByteArrays.writeLittleEndianInt(aux, 0, 0xFEDCBA98);
		Assert.assertArrayEquals(new byte[] { (byte) 0x98, (byte) 0xBA, (byte) 0xDC, (byte) 0xFE }, aux);
	}

	@Test public void testReadLittleEndianInt() {
		final byte[] aux = new byte[] { (byte) 0xFE, (byte) 0xDC, (byte) 0xAB, (byte) 0x89 };
		Assert.assertEquals(0x89ABDCFE, ByteArrays.readLittleEndianInt(aux, 0));
	}

	@Test public void testWriteBigEndianLong() {
		final byte[] aux = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		ByteArrays.writeBigEndianLong(aux, 0, 0xFEDCBA9889ABCDEFL);
		Assert.assertArrayEquals(new byte[] { (byte) 0xFE, (byte) 0xDC, (byte) 0xBA, (byte) 0x98, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }, aux);
	}

	@Test public void testReadBigEndianLong() {
		final byte[] aux = new byte[] { (byte) 0xFE, (byte) 0xDC, (byte) 0xBA, (byte) 0x98, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
		Assert.assertEquals(0xFEDCBA9889ABCDEFL, ByteArrays.readBigEndianLong(aux, 0));
	}

	@Test public void testWriteLittleEndianLong() {
		final byte[] aux = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		ByteArrays.writeLittleEndianLong(aux, 0, 0xFECDAB8998BADCEFL);
		Assert.assertArrayEquals(new byte[] { (byte) 0xEF, (byte) 0xDC, (byte) 0xBA, (byte) 0x98, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xFE }, aux);
	}

	@Test public void testReadLittleEndianLong() {
		final byte[] aux = new byte[] { (byte) 0xEF, (byte) 0xDC, (byte) 0xBA, (byte) 0x98, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xFE };
		Assert.assertEquals(0xFECDAB8998BADCEFL, ByteArrays.readLittleEndianLong(aux, 0));
	}

	@Test public void testEnsure() {
		Assert.assertNotNull(ByteArrays.ensure(null));
	}

}
