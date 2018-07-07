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

	public static final PrintService[] list() {
		return PrintServiceLookup.lookupPrintServices(null, null);
	}

	public static final List<String> listNames() {
		return new ArrayList<String>() {{
			for (final PrintService printer : list()) {
				add(printer.getName());
			}
		}};
	}

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

	public static final List<String> listNames(final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		return new ArrayList<String>() {{
			if (assigned(flavor, attributes)) {
				for (final PrintService printer : PrintServiceLookup.lookupPrintServices(flavor, attributes)) {
					add(printer.getName());
				}
			}
		}};
	}

	public static final PrintService getByName(final String name) {
		for (final PrintService printer : list()) {
			if (printer.getName().equals(name)) {
				return printer;
			}
		}
		return null;
	}

	public static final PrintService pickBest(final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		final PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, attributes);
		return assigned((Object) printServices) && printServices.length > 0 ? printServices[0] : PrintServiceLookup.lookupDefaultPrintService();
	}

}
