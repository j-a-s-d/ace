/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.printing;

import ace.Ace;
import java.util.ArrayList;
import java.util.List;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 * Utility class for working with printing services.
 */
public class PrintServices extends Ace {

    /**
     * List the print services.
     *
     * @return array of <tt>PrintService</tt> objects.
     * If there are no services, the result is a zero-length array.
     */
	public static final PrintService[] list() {
		return PrintServiceLookup.lookupPrintServices(null, null);
	}

    /**
     * List the print services names.
     *
     * @return list of strings containing the names
	 * of the available printing services.
     */
	public static final List<String> listNames() {
		return new ArrayList<String>() {{
			for (final PrintService printer : list()) {
				add(printer.getName());
			}
		}};
	}

    /**
     * List the print services names for the specified document flavor.
     *
	 * @param flavor
     * @return list of strings containing the names
	 * of the available printing services that match
	 * the specified document flavor.
     */
	public static final List<String> listNames(final DocFlavor flavor) {
		return new ArrayList<String>() {{
			if (assigned(flavor)) {
				for (final PrintService printer : list()) {
					if (printer.isDocFlavorSupported(flavor)) {
						add(printer.getName());
					}
				}
			}
		}};
	}

    /**
     * List the print services names for the specified document flavor and attributes.
     *
	 * @param flavor
	 * @param attributes
     * @return list of strings containing the names
	 * of the available printing services that match
	 * the specified document flavor.
     */
	public static final List<String> listNames(final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		return new ArrayList<String>() {{
			if (assigned(flavor, attributes)) {
				for (final PrintService printer : PrintServiceLookup.lookupPrintServices(flavor, attributes)) {
					add(printer.getName());
				}
			}
		}};
	}

    /**
     * Gets the print service that matches the specified name.
     *
	 * @param name
     * @return an instance of <tt>PrintService</tt> that matches
     * the specified name. If none matches, <tt>null</tt> is returned.
     */
	public static final PrintService getByName(final String name) {
		for (final PrintService printer : list()) {
			if (printer.getName().equals(name)) {
				return printer;
			}
		}
		return null;
	}

    /**
     * Picks the best fit among the print services for the specified document flavor and attributes.
     *
	 * @param flavor
	 * @param attributes
     * @return an instance of <tt>PrintService</tt> that is the best fit
	 * for the specified document flavor and attributes.
	 * If there is none, the default print service is returned.
     */
	public static final PrintService pickBest(final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		final PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, attributes);
		return assigned((Object) printServices) && printServices.length > 0 ? printServices[0] : PrintServiceLookup.lookupDefaultPrintService();
	}

}
