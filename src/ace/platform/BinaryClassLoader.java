/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.containers.Maps;
import ace.containers.Sets;
import java.util.Map;
import java.util.Set;

/**
 * Useful and extensible custom class loader which simplifies loading classes from their binary images.
 */
public class BinaryClassLoader extends ClassLoader {

	private final Set<String> _definedClasses = Sets.make();
	private final Map<String, byte[]> _binaryClasses = Maps.make();

	public boolean registerClass(final String name, final byte[] data) {
		return !_binaryClasses.containsKey(name) && _binaryClasses.put(name, data) != null;
	}

	@Override public Class loadClass(String name) throws ClassNotFoundException {
		return super.loadClass(name, true);
	}

	@Override public Class findClass(String name) throws ClassNotFoundException {
		if (!_definedClasses.contains(name)) {
			try {
				final byte[] classdata = _binaryClasses.get(name);
				final Class<?> c = super.defineClass(name, classdata, 0, classdata.length, null);
				if (c != null) {
					_definedClasses.add(name);
				}
				return c;
			} catch (final Exception e) {
				Ace.GEH.setLastException(e);
			}
		}
		return super.loadClass(name, true);
	}

}
