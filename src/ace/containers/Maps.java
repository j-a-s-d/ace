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

	public static boolean hasContent(final Map map) {
		return map != null && map.size() > 0;
	}

	public static HashMap make() {
		return new HashMap();
	}

	public static HashMap make(final int initialCapacity) {
		return new HashMap(initialCapacity);
	}

	public static HashMap make(final Map map) {
		return new HashMap(map);
	}

	public static Map makeSynchronized() {
		return Collections.synchronizedMap(new HashMap());
	}

	public static Map makeSynchronized(final int initialCapacity) {
		return Collections.synchronizedMap(new HashMap(initialCapacity));
	}

	public static Map makeSynchronized(final Map map) {
		return Collections.synchronizedMap(new HashMap(map));
	}

	public static LinkedHashMap makeLinked() {
		return new LinkedHashMap();
	}

	public static LinkedHashMap makeLinked(final int initialCapacity) {
		return new LinkedHashMap(initialCapacity);
	}

	public static LinkedHashMap makeLinked(final Map map) {
		return new LinkedHashMap(map);
	}

	public static Map makeSynchronizedLinked() {
		return Collections.synchronizedMap(new LinkedHashMap());
	}

	public static Map makeSynchronizedLinked(final int initialCapacity) {
		return Collections.synchronizedMap(new LinkedHashMap(initialCapacity));
	}

	public static Map makeSynchronizedLinked(final Map map) {
		return Collections.synchronizedMap(new LinkedHashMap(map));
	}

	public static WeakHashMap makeWeak() {
		return new WeakHashMap();
	}

	public static WeakHashMap makeWeak(final int initialCapacity) {
		return new WeakHashMap(initialCapacity);
	}

	public static WeakHashMap makeWeak(final Map map) {
		return new WeakHashMap(map);
	}

	public static Map makeSynchronizedWeak() {
		return Collections.synchronizedMap(new WeakHashMap());
	}

	public static Map makeSynchronizedWeak(final int initialCapacity) {
		return Collections.synchronizedMap(new WeakHashMap(initialCapacity));
	}

	public static Map makeSynchronizedWeak(final Map map) {
		return Collections.synchronizedMap(new WeakHashMap(map));
	}

	public static TreeMap makeTree() {
		return new TreeMap();
	}

	public static TreeMap makeTree(final Map map) {
		return new TreeMap(map);
	}

	public static Map makeSynchronizedTree() {
		return Collections.synchronizedMap(new TreeMap());
	}

	public static Map makeSynchronizedTree(final Map map) {
		return Collections.synchronizedMap(new TreeMap(map));
	}

	public static ConcurrentHashMap makeConcurrent() {
		return new ConcurrentHashMap();
	}

	public static ConcurrentHashMap makeConcurrent(final int initialCapacity) {
		return new ConcurrentHashMap(initialCapacity);
	}

	public static ConcurrentHashMap makeConcurrent(final Map map) {
		return new ConcurrentHashMap(map);
	}

	public static String[] getStringKeysAsStringArray(final Map<String, ?> map) {
		return hasContent(map) ? map.keySet().toArray(new String[] {}) : new String[] {};
	}

	public static <K, V> boolean hasKeys(final Map<K, V> map, final K... keys) {
		for (final K k : keys) {
			if (!map.containsKey(k)) {
				return false;
			}
		}
		return true;
	}

	public static <K, V> boolean hasKeys(final Map<K, V> map, final Collection<K> keys) {
		for (final K k : keys) {
			if (!map.containsKey(k)) {
				return false;
			}
		}
		return true;
	}

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
