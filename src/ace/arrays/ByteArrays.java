/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import ace.Ace;
import ace.constants.BYTES;

/**
 * Utility class for working with byte arrays.
 */
public class ByteArrays extends Ace {

	// COMPOSITION

	/**
	 * Creates a byte array with the specified values as content.
	 * 
	 * @param items
	 * @return the new byte array
	 */
	public static final byte[] make(final byte... items) {
		return items;
	}

	/**
	 * Concatenates the specified byte arrays into a new byte array.
	 * 
	 * @param arrays
	 * @return the resulting byte array
	 */
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

	/**
	 * Retrieves a byte array containing the elements in the specified segment of the specified byte array.
	 * 
	 * @param buffer
	 * @param offset
	 * @param length
	 * @return the resulting byte array
	 */
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

	/**
	 * Retrieves a byte array containing the elements in the specified segment of the specified byte array but in the reverse order.
	 * 
	 * @param buffer
	 * @param offset
	 * @param length
	 * @return the resulting byte array
	 */
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

	/**
	 * Returns a valid byte array instance.
	 * 
	 * @param buffer
	 * @return the specified byte array if it is assigned (not null), otherwise it returns an empty byte array
	 */
	public static final byte[] ensure(final byte[] buffer) {
		return buffer != null ? buffer : BYTES.EMPTY;
	}

	// CONTENT

	/**
	 * Indicates if the specified byte array has one or more elements.
	 * 
	 * @param array
	 * @return <tt>true</tt> if the specified byte array has one or more elements, <tt>false</tt> otherwise
	 */
	public static final boolean hasContent(final byte[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * Returns the index of the specified byte sequence in the specified byte array starting to search in the specified offset.
	 * 
	 * @param buffer
	 * @param startOffset
	 * @param sequence
	 * @return the index of the byte sequence
	 */
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

	/**
	 * Returns the index of the specified byte sequence in the specified byte array.
	 * 
	 * @param buffer
	 * @param sequence
	 * @return the index of the byte sequence
	 */
	public static final int indexOf(final byte[] buffer, final byte[] sequence) {
		return indexOf(buffer, 0, sequence);
	}

	/**
	 * Returns the array of indexes of the specified byte sequence in the specified byte array.
	 * 
	 * @param buffer
	 * @param sequence
	 * @return the array of indexes of the byte sequence in the specified byte array
	 */
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

	/**
	 * Returns a byte array with the specified byte array elements xored with the specified key.
	 * 
	 * @param data
	 * @param key
	 * @return the resulting byte array
	 */
	public static byte[] xor(final byte[] data, final byte key) {
		final int len = data.length;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = (byte) (data[i] ^ key);
		}
		return result;
	}

	// 16-BIT I/O ROUTINES

	/**
	 * Writes in the big-endian ordering the specified 16-bit short value in the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @param value 
	 */
	public static final void writeBigEndianShort(final byte[] array, final int pos, final short value) {
		array[pos] = (byte) (value >>> 8);
		array[pos + 1] = (byte) (value & 0xFF);
	}

	/**
	 * Returns the 16-bit short value read in the big-endian ordering from the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @return the 16-bit short value read in the big-endian ordering
	 */
	public static final short readBigEndianShort(final byte[] array, final int pos) {
		return (short) ((array[pos + 1] & 0xFF) | (array[pos] << 8));
	}

	/**
	 * Writes in the little-endian ordering the specified 16-bit short value in the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @param value 
	 */
	public static final void writeLittleEndianShort(final byte[] array, final int pos, final short value) {
		array[pos + 1] = (byte) (value >>> 8);
		array[pos] = (byte) (value & 0xFF);
	}

	/**
	 * Returns the 16-bit short value read in the little-endian ordering from the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @return the 16-bit short value read in the little-endian ordering
	 */
	public static final short readLittleEndianShort(final byte[] array, final int pos) {
		return (short) ((array[pos] & 0xFF) | (array[pos + 1] << 8));
	}

	// 32-BIT I/O ROUTINES

	/**
	 * Writes in the big-endian ordering the specified 32-bit int value in the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @param value 
	 */
	public static final void writeBigEndianInt(final byte[] array, final int pos, final int value) {
		array[pos] = (byte) ((value >>> 24) & 0xFF);
		array[pos + 1] = (byte) ((value >>> 16) & 0xFF);
		array[pos + 2] = (byte) ((value >>> 8) & 0xFF);
		array[pos + 3] = (byte) ((value) & 0xFF);
	}

	/**
	 * Returns the 32-bit int value read in the big-endian ordering from the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @return the 32-bit int value read in the big-endian ordering
	 */
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

	/**
	 * Writes in the little-endian ordering the specified 32-bit int value in the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @param value 
	 */
	public static final void writeLittleEndianInt(final byte[] array, final int pos, final int value) {
		array[pos + 3] = (byte) (value >>> 24);
		array[pos + 2] = (byte) (value >>> 16);
		array[pos + 1] = (byte) (value >>> 8);
		array[pos] = (byte) (value & 0xFF);
	}

	/**
	 * Returns the 32-bit int value read in the little-endian ordering from the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @return the 32-bit int value read in the little-endian ordering
	 */
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

	/**
	 * Writes in the big-endian ordering the specified 64-bit long value in the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @param value 
	 */
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

	/**
	 * Returns the 64-bit long value read in the big-endian ordering from the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @return the 64-bit long value read in the big-endian ordering
	 */
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

	/**
	 * Writes in the little-endian ordering the specified 64-bit long value in the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @param value 
	 */
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

	/**
	 * Returns the 64-bit long value read in the little-endian ordering from the specified byte array at the specified position.
	 * 
	 * @param array
	 * @param pos
	 * @return the 64-bit long value read in the little-endian ordering
	 */
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
