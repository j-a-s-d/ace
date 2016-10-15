/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.binary;

import ace.Ace;

/**
 * Class providing binary sizes and orders of magnitudes constants.
 */
public class SIZES extends Ace {

	// ORDERS OF MAGNITUDE
	// BIT COUNT
	public static final long BIT = 1; // 1 bit
	public static final long PAIR = 2 * BIT; // 2 bits
	public static final long NIBBLE = 4 * BIT; // 4 bits -- i4004
	public static final long BYTE = 2 * NIBBLE; // 8 bits -- i8008, i8080
	public static final long WORD = 4 * NIBBLE; // 16 bits -- i8086
	public static final long DWORD = 2 * WORD; // 32 bits -- vax, arm1, mips
	public static final long QWORD = 4 * WORD; // 64 bits -- mmx, ia64
	public static final long DQWORD = 2 * QWORD; // 128 bits -- sse
	public static final long QQWORD = 4 * QWORD; // 256 bits -- avx
	public static final long DQQWORD = 2 * QQWORD; // 512 bits

	// BYTE COUNT
	public static final long KILOBYTE = 1024;
	public static final long MEGABYTE = KILOBYTE * KILOBYTE;
	public static final long GIGABYTE = MEGABYTE * KILOBYTE;
	public static final long TERABYTE = GIGABYTE * KILOBYTE;
	public static final long PETABYTE = TERABYTE * KILOBYTE;
	public static final long EXABYTE = PETABYTE * KILOBYTE;

}
