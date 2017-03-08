/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;
import ace.constants.STRINGS;
import ace.platform.Reflection;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Useful string list class.
 */
public class StringList extends Ace implements Iterable<String> {

	private static final Field FIELD_DATA = Reflection.getFieldAsAccessible(ArrayList.class, "elementData");
	private final ArrayList<String> _list;

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public StringList(final String... values) {
		_list = new ArrayList();
		add(values);
	}

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public StringList(final List<String> values) {
		_list = new ArrayList();
		include(values);
	}

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public StringList(final StringList values) {
		_list = new ArrayList();
		add(values);
	}

	public StringList ensureCapacity(final int index) {
		_list.ensureCapacity(index);
		return this;
	}

	public int capacity() {
		try {
			return ((Object[]) FIELD_DATA.get(_list)).length;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return -1;
		}
	}

	public int size() {
		return _list.size();
	}

	public StringList clear() {
		_list.clear();
		_list.trimToSize();
		return this;
	}

	public boolean isEmpty() {
		return _list.isEmpty();
	}

	public String[] toArray() {
		return _list.toArray(new String[_list.size()]);
	}

	public StringList add(final StringList elements) {
		for (final String element : elements) {
			_list.add(element);
		}
		return this;
	}

	public StringList add(final String... elements) {
		for (final String element : elements) {
			_list.add(element);
		}
		return this;
	}

	public StringList include(final Collection<? extends String> collection) {
		_list.addAll(collection);
		return this;
	}

	public StringList include(final StringList elements) {
		for (final String element : elements) {
			_list.add(element);
		}
		return this;
	}

	public StringList exclude(final StringList elements) {
		for (final String element : elements) {
			_list.remove(element);
		}
		return this;
	}

	public StringList exclude(final Collection<? extends String> collection) {
		for (final String element : collection) {
			_list.remove(element);
		}
		return this;
	}

	public StringList insert(final int index, final StringList elements) {
		int i = index;
		for (final String element : elements) {
			_list.add(i++, element);
		}
		return this;
	}

	public StringList insert(final int index, final String... elements) {
		int i = index;
		for (final String element : elements) {
			_list.add(i++, element);
		}
		return this;
	}

	public String get(final int index) {
		return _list.get(index);
	}

	public String remove(final int index) {
		return _list.remove(index);
	}

	public String set(final int index, final String element) {
		return index > -1 && _list.size() > index ? _list.set(index, element) : null;
	}

	public boolean contains(final String element) {
		return _list.contains(element);
	}

	public boolean containsContaining(final String partOfElement) {
		for (final String s : _list) {
			if (s.contains(partOfElement)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsContainedIn(final String element) {
		for (final String s : _list) {
			if (element.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public int indexOf(final String element) {
		return _list.indexOf(element);
	}

	public int lastIndexOf(final String element) {
		return _list.lastIndexOf(element);
	}

	/*@Override*/ public Iterator<String> iterator() {
		return _list.iterator();
	}

	public ListIterator<String> listIterator() {
		return _list.listIterator();
	}

	public ListIterator<String> listIterator(final int index) {
		return _list.listIterator(index);
	}
	
	public StringList filter(final String filter) {
		final List filtered = new ArrayList<String>() {
			{
				if (Strings.hasText(filter)) {
					final boolean unknownEnd = "*".equals(String.valueOf(filter.charAt(filter.length() - 1)));
					final boolean unknownStart = "*".equals(String.valueOf(filter.charAt(0)));
					if (unknownEnd && unknownStart) {
						final String knownPart = Strings.dropBoth(filter, 1);
						for (final String n : _list) {
							if (n.contains(knownPart)) {
								add(n);
							}
						}
					} else if (unknownEnd) {
						final String knownPart = Strings.dropRight(filter, 1);
						for (final String n : _list) {
							if (n.startsWith(knownPart)) {
								add(n);
							}
						}
					} else if (unknownStart) {
						final String knownPart = Strings.dropLeft(filter, 1);
						for (final String n : _list) {
							if (n.endsWith(knownPart)) {
								add(n);
							}
						}
					} else {
						for (final String n : _list) {
							if (n.equals(filter)) {
								add(n);
							}
						}
					}
				}
			}
		};
		_list.clear();
		_list.addAll(filtered);
		_list.trimToSize();
		return this;
	}

	public StringList fromString(final String text) {
		_list.clear();
		final Scanner sc = new Scanner(text);
		while (sc.hasNextLine()) {
			_list.add(sc.nextLine());
		}
		sc.close();
		_list.trimToSize();
		return this;
	}

	@Override public String toString() {
		return Strings.join(STRINGS.EOL, _list);
	}

}
