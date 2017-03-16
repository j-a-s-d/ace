/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.printing;

import ace.Ace;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 * Utility class for working with printing jobs.
 */
public class PrintJobs extends Ace {

	public static final boolean submitDataAsSimpleDocumentJob(final PrintService printService, final InputStream fileData, final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		if (assigned(printService, fileData, flavor, attributes)) {
			try {
				printService.createPrintJob().print(new SimpleDoc(fileData, flavor, null), attributes);
				return true;
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return false;
	}

	public static final boolean submitFileAsSimpleDocumentJob(final PrintService printService, final String fileName, final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		if (assigned(printService, fileName, flavor, attributes)) {
			try {
				return submitDataAsSimpleDocumentJob(printService, new FileInputStream(fileName), flavor, attributes);
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return false;
	}

	public static final boolean submitDataAsSimpleDocumentJob(final InputStream fileData, final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		final PrintService printer = PrintServices.pickBest(flavor, attributes);
		return assigned(printer) ? submitDataAsSimpleDocumentJob(printer, fileData, flavor, attributes) : false;
	}

	public static final boolean submitFileAsSimpleDocumentJob(final String fileName, final DocFlavor flavor, final PrintRequestAttributeSet attributes) {
		final PrintService printer = PrintServices.pickBest(flavor, attributes);
		return assigned(printer) ? submitFileAsSimpleDocumentJob(printer, fileName, flavor, attributes) : false;
	}

}
