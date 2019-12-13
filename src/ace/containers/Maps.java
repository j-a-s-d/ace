/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.containers;

import ace.Ace;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class for working with maps.
 */
@SuppressWarnings("PMD.TooManyMethods")
public class Maps extends Ace {

	/**
	 * Indicates if the specified map is not empty.
	 * 
	 * @param map
	 * @return <tt>true</tt> if the specified map is assigned (not null) and has one or more elements, <tt>false</tt> otherwise
	 */
	public static boolean hasContent(final Map map) {
		return map != null && map.size() > 0;
	}

	/**
	 * Creates a new empty map.
	 * 
	 * @return the new map
	 */
	public static HashMap make() {
		return new HashMap();
	}

	/**
	 * Creates a new empty map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static HashMap make(final int initialCapacity) {
		return new HashMap(initialCapacity);
	}

	/**
	 * Creates a new map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static HashMap make(final Map map) {
		return new HashMap(map);
	}

	/**
	 * Creates a new empty synchronized map.
	 * 
	 * @return the new map
	 */
	public static Map makeSynchronized() {
		return Collections.synchronizedMap(new HashMap());
	}

	/**
	 * Creates a new empty synchronized map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static Map makeSynchronized(final int initialCapacity) {
		return Collections.synchronizedMap(new HashMap(initialCapacity));
	}

	/**
	 * Creates a new synchronized map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static Map makeSynchronized(final Map map) {
		return Collections.synchronizedMap(new HashMap(map));
	}

	/**
	 * Creates a new empty linked map.
	 * 
	 * @return the new map
	 */
	public static LinkedHashMap makeLinked() {
		return new LinkedHashMap();
	}

	/**
	 * Creates a new empty linked map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static LinkedHashMap makeLinked(final int initialCapacity) {
		return new LinkedHashMap(initialCapacity);
	}

	/**
	 * Creates a new linked map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static LinkedHashMap makeLinked(final Map map) {
		return new LinkedHashMap(map);
	}

	/**
	 * Creates a new empty synchronized linked map.
	 * 
	 * @return the new map
	 */
	public static Map makeSynchronizedLinked() {
		return Collections.synchronizedMap(new LinkedHashMap());
	}

	/**
	 * Creates a new empty synchronized linked map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static Map makeSynchronizedLinked(final int initialCapacity) {
		return Collections.synchronizedMap(new LinkedHashMap(initialCapacity));
	}

	/**
	 * Creates a new synchronized linked map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static Map makeSynchronizedLinked(final Map map) {
		return Collections.synchronizedMap(new LinkedHashMap(map));
	}

	/**
	 * Creates a new empty weak map.
	 * 
	 * @return the new map
	 */
	public static WeakHashMap makeWeak() {
		return new WeakHashMap();
	}

	/**
	 * Creates a new empty weak map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static WeakHashMap makeWeak(final int initialCapacity) {
		return new WeakHashMap(initialCapacity);
	}

	/**
	 * Creates a new weak map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static WeakHashMap makeWeak(final Map map) {
		return new WeakHashMap(map);
	}

	/**
	 * Creates a new empty synchronized weak map.
	 * 
	 * @return the new map
	 */
	public static Map makeSynchronizedWeak() {
		return Collections.synchronizedMap(new WeakHashMap());
	}

	/**
	 * Creates a new empty synchronized weak map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static Map makeSynchronizedWeak(final int initialCapacity) {
		return Collections.synchronizedMap(new WeakHashMap(initialCapacity));
	}

	/**
	 * Creates a new synchronized weak map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static Map makeSynchronizedWeak(final Map map) {
		return Collections.synchronizedMap(new WeakHashMap(map));
	}

	/**
	 * Creates a new empty tree map.
	 * 
	 * @return the new map
	 */
	public static TreeMap makeTree() {
		return new TreeMap();
	}

	/**
	 * Creates a new tree map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static TreeMap makeTree(final Map map) {
		return new TreeMap(map);
	}

	/**
	 * Creates a new empty synchronized tree map.
	 * 
	 * @return the new map
	 */
	public static Map makeSynchronizedTree() {
		return Collections.synchronizedMap(new TreeMap());
	}

	/**
	 * Creates a new synchronized tree map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static Map makeSynchronizedTree(final Map map) {
		return Collections.synchronizedMap(new TreeMap(map));
	}

	/**
	 * Creates a new empty concurrent map.
	 * 
	 * @return the new map
	 */
	public static ConcurrentHashMap makeConcurrent() {
		return new ConcurrentHashMap();
	}

	/**
	 * Creates a new empty concurrent map with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 * @return the new map
	 */
	public static ConcurrentHashMap makeConcurrent(final int initialCapacity) {
		return new ConcurrentHashMap(initialCapacity);
	}

	/**
	 * Creates a new concurrent map with the elements of the specified map.
	 * 
	 * @param map
	 * @return the new map
	 */
	public static ConcurrentHashMap makeConcurrent(final Map map) {
		return new ConcurrentHashMap(map);
	}

	/**
	 * Gets the specified map string keys as a string array.
	 * 
	 * @param map
	 * @return the specified map string keys as a string array
	 */
	public static String[] getStringKeysAsStringArray(final Map<String, ?> map) {
		return hasContent(map) ? map.keySet().toArray(new String[] {}) : new String[] {};
	}

	/**
	 * Indicates if the specified map contains the specified keys.
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param keys
	 * @return <tt>true</tt> if the specified map contains the specified keys, <tt>false</tt> otherwise
	 */
	public static <K, V> boolean hasKeys(final Map<K, V> map, final K... keys) {
		for (final K k : keys) {
			if (!map.containsKey(k)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Indicates if the specified map contains as keys the values of the specified collection.
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param keys
	 * @return <tt>true</tt> if the specified map contains as keys the values of the specified collection, <tt>false</tt> otherwise
	 */
	public static <K, V> boolean hasKeys(final Map<K, V> map, final Collection<K> keys) {
		for (final K k : keys) {
			if (!map.containsKey(k)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sorts the specified map entries by value in an ascending ordering.
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return the resulting map
	 */
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(final Map<K, V> map) {
		final List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
			/*@Override*/ public int compare(final Map.Entry<K, V> o1, final Map.Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		final Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for (final Map.Entry<K, V> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
