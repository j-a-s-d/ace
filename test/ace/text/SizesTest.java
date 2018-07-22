/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.constants.SIZES;
import junit.framework.Assert;
import org.junit.Test;

public class SizesTest {

	@Test public void testFormatByteSize_long() {
		Assert.assertEquals("10 bytes", Sizes.formatByteSize(10));
		Assert.assertEquals("123 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE));
		Assert.assertEquals("123.5 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 512));
		Assert.assertEquals("123.73 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 750));
		Assert.assertEquals("456 mb", Sizes.formatByteSize(456 * SIZES.MEGABYTE));
		Assert.assertEquals("456.12 mb", Sizes.formatByteSize(456 * SIZES.MEGABYTE + 123 * SIZES.KILOBYTE));
		Assert.assertEquals("456.12 gb", Sizes.formatByteSize(456 * SIZES.GIGABYTE + 123 * SIZES.MEGABYTE));
		Assert.assertEquals("456.12 tb", Sizes.formatByteSize(456 * SIZES.TERABYTE + 123 * SIZES.GIGABYTE));
		Assert.assertEquals("456.12 pb", Sizes.formatByteSize(456 * SIZES.PETABYTE + 123 * SIZES.TERABYTE));
		Assert.assertEquals("7.12 eb", Sizes.formatByteSize(7 * SIZES.EXABYTE + 123 * SIZES.PETABYTE));
	}

	@Test public void testFormatByteSize_long_int() {
		Assert.assertEquals("10 bytes", Sizes.formatByteSize(10, 0));
		Assert.assertEquals("10 bytes", Sizes.formatByteSize(10, 2));
		Assert.assertEquals("123 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE, 3));
		Assert.assertEquals("123.5 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 512, 2));
		Assert.assertEquals("123 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 750, 0));
		Assert.assertEquals("123.7 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 750, 1));
		Assert.assertEquals("123.73 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 750, 2));
		Assert.assertEquals("123.732 kb", Sizes.formatByteSize(123 * SIZES.KILOBYTE + 750, 3));
		Assert.assertEquals("456 mb", Sizes.formatByteSize(456 * SIZES.MEGABYTE, 3));
		Assert.assertEquals("456.975 gb", Sizes.formatByteSize(456 * SIZES.GIGABYTE + 999 * SIZES.MEGABYTE, 3));
		Assert.assertEquals("456.9755 tb", Sizes.formatByteSize(456 * SIZES.TERABYTE + 999 * SIZES.GIGABYTE, 4));
		Assert.assertEquals("456.97558 pb", Sizes.formatByteSize(456 * SIZES.PETABYTE + 999 * SIZES.TERABYTE, 5));
		Assert.assertEquals("7.975585 eb", Sizes.formatByteSize(7 * SIZES.EXABYTE + 999 * SIZES.PETABYTE, 6));
	}

}