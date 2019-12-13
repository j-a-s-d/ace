/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;
import ace.constants.SIZES;
import ace.constants.STRINGS;

/**
 * Utility class for working with sizes and related formating.
 */
public class Sizes extends Ace {

	/**
	 * Formats the specified byte size.
	 * 
	 * @param bytes
	 * @return the formatted byte size
	 */
	public static final String formatByteSize(final long bytes) {
		return formatByteSize(bytes, 2);
	}

	/**
	 * Formats the specified byte size with the specified amount of decimals.
	 * 
	 * @param bytes
	 * @param decimals
	 * @return the formatted byte size
	 */
	public static final String formatByteSize(final long bytes, final int decimals) {
		final String unit_suffix;
		final double unit_bytes;
		if (bytes >= SIZES.EXABYTE) {
			unit_suffix = SIZES.EXABYTE_UNIT;
			unit_bytes = SIZES.EXABYTE;
		} else if (bytes >= SIZES.PETABYTE) {
			unit_suffix = SIZES.PETABYTE_UNIT;
			unit_bytes = SIZES.PETABYTE;
		} else if (bytes >= SIZES.TERABYTE) {
			unit_suffix = SIZES.TERABYTE_UNIT;
			unit_bytes = SIZES.TERABYTE;
		} else if (bytes >= SIZES.GIGABYTE) {
			unit_suffix = SIZES.GIGABYTE_UNIT;
			unit_bytes = SIZES.GIGABYTE;
		} else if (bytes >= SIZES.MEGABYTE) {
			unit_suffix = SIZES.MEGABYTE_UNIT;
			unit_bytes = SIZES.MEGABYTE;
		} else if (bytes >= SIZES.KILOBYTE) {
			unit_suffix = SIZES.KILOBYTE_UNIT;
			unit_bytes = SIZES.KILOBYTE;
		} else {
			unit_suffix = "bytes";
			unit_bytes = 1;
		}
		final double precission_scale = Math.pow(10, decimals);
		final double value = Math.floor((double) bytes / unit_bytes * precission_scale) / precission_scale;
		if (decimals == 0 || value % 1 == 0) {
			return STRINGS.EMPTY + ((long) value) + STRINGS.SPACE + unit_suffix;
		} else {
			return STRINGS.EMPTY + value + STRINGS.SPACE + unit_suffix;
		}
	}

}
