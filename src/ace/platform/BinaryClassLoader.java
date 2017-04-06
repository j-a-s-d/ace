/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.platform;

import ace.Ace;
import ace.containers.Maps;
import ace.containers.Sets;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * Useful and extensible custom class loader which simplifies loading classes and resources from their binary images.
 */
public class BinaryClassLoader extends ClassLoader {

	private final Set<String> _definedClasses = Sets.make();
	private final Map<String, byte[]> _binaryClasses = Maps.make();
	private final Map<String, byte[]> _binaryResources = Maps.make();

	public BinaryClassLoader() {
		this(BinaryClassLoader.class.getClassLoader());
	}

	public BinaryClassLoader(final ClassLoader parent) {
		super(parent);
	}

	public boolean registerClass(final String name, final byte[] data) {
		return !_binaryClasses.containsKey(name) && _binaryClasses.put(name, data) == null;
	}

	public boolean registerResource(final String name, final byte[] data) {
		return !_binaryResources.containsKey(name) && _binaryResources.put(name, data) == null;
	}

	@Override public Class loadClass(final String name) throws ClassNotFoundException {
		return super.loadClass(name, true);
	}

	@Override public Class findClass(final String name) throws ClassNotFoundException {
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

    @Override public InputStream getResourceAsStream(final String name) {
		return new ByteArrayInputStream(_binaryResources.get(name));
	}

}
