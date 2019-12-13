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

	/**
	 * Default constructor using the local class loader.
	 */
	public BinaryClassLoader() {
		this(BinaryClassLoader.class.getClassLoader());
	}

	/**
	 * Default constructor using the specified class loader.
	 * 
	 * @param parent 
	 */
	public BinaryClassLoader(final ClassLoader parent) {
		super(parent);
	}

	/**
	 * Register a class with the specified name and the specified content.
	 * 
	 * @param name
	 * @param data
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean registerClass(final String name, final byte[] data) {
		return !_binaryClasses.containsKey(name) && _binaryClasses.put(name, data) == null;
	}

	/**
	 * Register a resource with the specified name and the specified content.
	 * 
	 * @param name
	 * @param data
	 * @return <tt>true</tt> if the operation was successful, <tt>false</tt> otherwise
	 */
	public boolean registerResource(final String name, final byte[] data) {
		return !_binaryResources.containsKey(name) && _binaryResources.put(name, data) == null;
	}

	/**
	 * Loads the class with the specified name (calling the implied loadClass method).
	 * 
	 * @param name
	 * @return the class instance
	 * @throws ClassNotFoundException 
	 */
	@Override public Class loadClass(final String name) throws ClassNotFoundException {
		return super.loadClass(name, true);
	}

	/**
	 * Finds the class with the specified name (looking for it among the defined classes).
	 * 
	 * @param name
	 * @return the class instance
	 * @throws ClassNotFoundException 
	 */
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

	/**
	 * Returns an input stream for reading the specified resource.
	 * 
	 * @param name
	 * @return an input stream for reading the specified resource
	 */
    @Override public InputStream getResourceAsStream(final String name) {
		return new ByteArrayInputStream(_binaryResources.get(name));
	}

}
