/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;
import ace.constants.BYTES;

/**
 * Utility class for working with byte arrays.
 */
public class ByteArrays extends Ace {

	// COMPOSITION
	public static final byte[] make(final byte... items) {
		return items;
	}

	public static final byte[] concat(final byte[]... arrays) {
		byte[] result = null;
		if (arrays.length > 0) {
			result = new byte[0];
			for (final byte[] array : arrays) {
				final int rLen = result.length;
				final int aLen = array.length;
				final byte[] aux = new byte[rLen + aLen];
				System.arraycopy(result, 0, aux, 0, rLen);
				System.arraycopy(array, 0, aux, rLen, aLen);
				result = aux;
			}
		}
		return result;
	}

	public static byte[] copy(final byte[] buffer, final int offset, final int length) {
		if (buffer == null || buffer.length == 0 || offset < 0 || length < 0 || offset + length > buffer.length) {
			return null;
		}
		final byte[] result = new byte[length];
		int index = 0;
		for (int i = offset; i < offset + length; i++) {
			result[index++] = buffer[i];
		}
		return result;
	}

	public static byte[] invertedCopy(final byte[] buffer, final int offset, final int length) {
		if (buffer == null || buffer.length == 0 || offset < 0 || length < 0 || offset + length > buffer.length) {
			return null;
		}
		final byte[] inverted = new byte[length];
		int index = length - 1;
		for (int i = offset; (index > -1) && (i < offset + length); i++) {
			inverted[index--] = buffer[i];
		}
		return inverted;
	}

	public static final byte[] ensure(final byte[] buffer) {
		return buffer != null ? buffer : BYTES.EMPTY;
	}

	// CONTENT
	public static final boolean hasContent(final byte[] array) {
		return array != null && array.length > 0;
	}

	public static final int indexOf(final byte[] buffer, final int startOffset, final byte[] sequence) {
		if (buffer != null && buffer.length > 0 && sequence != null && sequence.length > 0) {
			for (int i = startOffset; i < buffer.length - sequence.length + 1; ++i) {
				boolean found = true;
				for (int j = 0; j < sequence.length; ++j) {
					if (buffer[i + j] != sequence[j]) {
						found = false;
						break;
					}
				}
				if (found) {
					return i;
				}
			}
		}
		return -1;
	}

	public static final int indexOf(final byte[] buffer, final byte[] sequence) {
		return indexOf(buffer, 0, sequence);
	}

	public static final int[] indexesOf(final byte[] buffer, final byte[] sequence) {
		int[] result = new int[] {};
		if (buffer != null && buffer.length > 0 && sequence != null && sequence.length > 0) {
			for (int i = 0; i < buffer.length - sequence.length + 1; ++i) {
				boolean found = true;
				for (int j = 0; j < sequence.length; ++j) {
					if (buffer[i + j] != sequence[j]) {
						found = false;
						break;
					}
				}
				if (found) {
					final int l = result.length;
					final int[] aux = new int[l + 1];
					System.arraycopy(result, 0, aux, 0, l);
					aux[l] = i;
					result = aux;
				}
			}
		}
		return result;
	}

	// 16-BIT I/O ROUTINES
	public static final void writeBigEndianShort(final byte[] array, final int pos, final short value) {
		array[pos] = (byte) (value >>> 8);
		array[pos + 1] = (byte) (value & 0xFF);
	}

	public static final short readBigEndianShort(final byte[] array, final int pos) {
		return (short) ((array[pos + 1] & 0xFF) | (array[pos] << 8));
	}

	public static final void writeLittleEndianShort(final byte[] array, final int pos, final short value) {
		array[pos + 1] = (byte) (value >>> 8);
		array[pos] = (byte) (value & 0xFF);
	}

	public static final short readLittleEndianShort(final byte[] array, final int pos) {
		return (short) ((array[pos] & 0xFF) | (array[pos + 1] << 8));
	}

	// 32-BIT I/O ROUTINES
	public static final void writeBigEndianInt(final byte[] array, final int pos, final int value) {
		array[pos] = (byte) ((value >>> 24) & 0xFF);
		array[pos + 1] = (byte) ((value >>> 16) & 0xFF);
		array[pos + 2] = (byte) ((value >>> 8) & 0xFF);
		array[pos + 3] = (byte) ((value) & 0xFF);
	}

	public static final int readBigEndianInt(final byte[] array, final int pos) {
		int temp = 0;
		temp |= array[pos] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 1] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 2] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 3] & 0xFF;
		return temp;
	}

	public static final void writeLittleEndianInt(final byte[] array, final int pos, final int value) {
		array[pos + 3] = (byte) (value >>> 24);
		array[pos + 2] = (byte) (value >>> 16);
		array[pos + 1] = (byte) (value >>> 8);
		array[pos] = (byte) (value & 0xFF);
	}

	public static final int readLittleEndianInt(final byte[] array, final int pos) {
		int temp = 0;
		temp |= array[pos + 3] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 2] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 1] & 0xFF;
		temp <<= 8;
		temp |= array[pos] & 0xFF;
		return temp;
	}

	// 64-BIT I/O ROUTINES
	public static final void writeBigEndianLong(final byte[] array, final int pos, final long value) {
		array[pos] = (byte) (value >>> 56);
		array[pos + 1] = (byte) (value >>> 48);
		array[pos + 2] = (byte) (value >>> 40);
		array[pos + 3] = (byte) (value >>> 32);
		array[pos + 4] = (byte) (value >>> 24);
		array[pos + 5] = (byte) (value >>> 16);
		array[pos + 6] = (byte) (value >>> 8);
		array[pos + 7] = (byte) (value & 0xFF);
	}

	public static final long readBigEndianLong(final byte[] array, final int pos) {
		long temp = 0;
		temp |= array[pos] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 1] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 2] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 3] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 4] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 5] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 6] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 7] & 0xFF;
		return temp;
	}

	public static final void writeLittleEndianLong(final byte[] array, final int pos, final long value) {
		array[pos + 7] = (byte) (value >>> 56);
		array[pos + 6] = (byte) (value >>> 48);
		array[pos + 5] = (byte) (value >>> 40);
		array[pos + 4] = (byte) (value >>> 32);
		array[pos + 3] = (byte) (value >>> 24);
		array[pos + 2] = (byte) (value >>> 16);
		array[pos + 1] = (byte) (value >>> 8);
		array[pos] = (byte) (value & 0xFF);
	}

	public static final long readLittleEndianLong(final byte[] array, final int pos) {
		long temp = 0;
		temp |= array[pos + 7] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 6] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 5] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 4] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 3] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 2] & 0xFF;
		temp <<= 8;
		temp |= array[pos + 1] & 0xFF;
		temp <<= 8;
		temp |= array[pos] & 0xFF;
		return temp;
	}

}
