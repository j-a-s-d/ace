/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;
import ace.constants.STRINGS;
import ace.containers.Lists;
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

	private static final Field _elementData = Reflection.getFieldAsAccessible(ArrayList.class, "elementData");
	private final ArrayList<String> _list;

	/**
	 * Constructor accepting string values.
	 * 
	 * @param values 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public StringList(final String... values) {
		_list = Lists.make();
		add(values);
	}

	/**
	 * Constructor accepting a list of string values.
	 * 
	 * @param values 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public StringList(final List<String> values) {
		_list = Lists.make();
		include(values);
	}

	/**
	 * Constructor accepting another string list.
	 * 
	 * @param values 
	 */
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public StringList(final StringList values) {
		_list = Lists.make();
		add(values);
	}

	/**
	 * Ensures a minimum capacity for the string list.
	 * 
	 * @param minCapacity
	 * @return itself
	 */
	public StringList ensureCapacity(final int minCapacity) {
		_list.ensureCapacity(minCapacity);
		return this;
	}

	/**
	 * Gets the current string list capacity.
	 * 
	 * @return the current string list capacity
	 */
	public int capacity() {
		try {
			return ((Object[]) _elementData.get(_list)).length;
		} catch (final Exception e) {
			GEH.setLastException(e);
			return -1;
		}
	}

	/**
	 * Gets the current string list size.
	 * 
	 * @return the current string list size
	 */
	public int size() {
		return _list.size();
	}

	/**
	 * Clears the string list.
	 * 
	 * @return itself
	 */
	public StringList clear() {
		_list.clear();
		_list.trimToSize();
		return this;
	}

	/**
	 * Determines if the string list is empty.
	 * 
	 * @return <tt>true</tt> if the string list is empty, <tt>false</tt> otherwise
	 */
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	/**
	 * Converts the current string list into a list of strings.
	 * 
	 * @return a list with the strings of this string list
	 */
	public List<String> toList() {
		return (List) Lists.make(_list);
	}

	/**
	 * Converts the current string list into an array of strings.
	 * 
	 * @return an array with the strings of this string list
	 */
	public String[] toArray() {
		return _list.toArray(new String[_list.size()]);
	}

	/**
	 * Adds the elements of the specified string list.
	 * 
	 * @param elements
	 * @return itself
	 */
	public StringList add(final StringList elements) {
		for (final String element : elements) {
			_list.add(element);
		}
		return this;
	}

	/**
	 * Adds the specified strings.
	 * 
	 * @param elements
	 * @return itself
	 */
	public StringList add(final String... elements) {
		for (final String element : elements) {
			_list.add(element);
		}
		return this;
	}

	/**
	 * Adds the elements of the specified collection of strings.
	 * 
	 * @param collection
	 * @return itself
	 */
	public StringList include(final Collection<? extends String> collection) {
		_list.addAll(collection);
		return this;
	}

	/**
	 * Adds the elements of the specified string list.
	 * 
	 * @param elements
	 * @return itself
	 */
	public StringList include(final StringList elements) {
		for (final String element : elements) {
			_list.add(element);
		}
		return this;
	}

	/**
	 * Removes the elements of the specified string list.
	 * 
	 * @param elements
	 * @return itself
	 */
	public StringList exclude(final StringList elements) {
		for (final String element : elements) {
			_list.remove(element);
		}
		return this;
	}

	/**
	 * Removes the elements of the specified collection of strings.
	 * 
	 * @param collection
	 * @return itself
	 */
	public StringList exclude(final Collection<? extends String> collection) {
		for (final String element : collection) {
			_list.remove(element);
		}
		return this;
	}

	/**
	 * Inserts the elements of the specified string list at the specified index.
	 * 
	 * @param index
	 * @param elements
	 * @return itself
	 */
	public StringList insert(final int index, final StringList elements) {
		int i = index;
		for (final String element : elements) {
			_list.add(i++, element);
		}
		return this;
	}

	/**
	 * Inserts the specified string values at the specified index.
	 * 
	 * @param index
	 * @param elements
	 * @return itself
	 */
	public StringList insert(final int index, final String... elements) {
		int i = index;
		for (final String element : elements) {
			_list.add(i++, element);
		}
		return this;
	}

	/**
	 * Gets the string in the specified index.
	 * 
	 * @param index
	 * @return the string in the specified index
	 */
	public String get(final int index) {
		return _list.get(index);
	}

	/**
	 * Removes the string in the specified index.
	 * 
	 * @param index
	 * @return the removed string that was in the specified index
	 */
	public String remove(final int index) {
		return _list.remove(index);
	}

	/**
	 * Removes the specified string from the list.
	 * 
	 * @param element
	 * @return <tt>true</tt> if the string was removed, <tt>false</tt> otherwise
	 */
	public boolean remove(final String element) {
		return _list.remove(element);
	}

	/**
	 * Sets the string at the specified index.
	 * 
	 * @param index
	 * @param element
	 * @return the string that was in the specified index before the replacement or null if it is an invalid index
	 */
	public String set(final int index, final String element) {
		return index > -1 && _list.size() > index ? _list.set(index, element) : null;
	}

	/**
	 * Determines if the specified string is present in the list.
	 * 
	 * @param element
	 * @return <tt>true</tt> if the string is present in the list, <tt>false</tt> otherwise
	 */
	public boolean contains(final String element) {
		return _list.contains(element);
	}

	/**
	 * Determines if the specified string is part of a string in the list.
	 * 
	 * @param partOfElement
	 * @return <tt>true</tt> if the string is part of a string in the list, <tt>false</tt> otherwise
	 */
	public boolean containsContaining(final String partOfElement) {
		for (final String s : _list) {
			if (s.contains(partOfElement)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if at least one string of the list contains the specified string.
	 * 
	 * @param element
	 * @return <tt>true</tt> if at least one string of the list contains the specified string, <tt>false</tt> otherwise
	 */
	public boolean containsContainedIn(final String element) {
		for (final String s : _list) {
			if (element.contains(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the first index in the list of the specified string.
	 * 
	 * @param element
	 * @return the first index in the list of the specified string
	 */
	public int indexOf(final String element) {
		return _list.indexOf(element);
	}

	/**
	 * Gets the last index in the list of the specified string.
	 * 
	 * @param element
	 * @return the last index in the list of the specified string
	 */
	public int lastIndexOf(final String element) {
		return _list.lastIndexOf(element);
	}

	/**
	 * Gets the iterator for this string list.
	 * 
	 * @return the iterator for this string list
	 */
	/*@Override*/ public Iterator<String> iterator() {
		return _list.iterator();
	}

	/**
	 * Gets the list iterator for this string list.
	 * 
	 * @return the list iterator for this string list
	 */
	public ListIterator<String> listIterator() {
		return _list.listIterator();
	}

	/**
	 * Gets a list iterator for this string list starting in the specified index.
	 * 
	 * @param index
	 * @return a list iterator for this string list starting in the specified index
	 */
	public ListIterator<String> listIterator(final int index) {
		return _list.listIterator(index);
	}

	/**
	 * Filters this string list with the specified filter.
	 * 
	 * It accepts '*' wildcard for the beginning and/or the ending of the filter.
	 * 
	 * @param filter
	 * @return itself
	 */
	public StringList filter(final String filter) {
		final List filtered = new ArrayList<String>() {
			{
				if (Strings.hasText(filter)) {
					final boolean unknownEnd = STRINGS.ASTERISK.equals(String.valueOf(filter.charAt(filter.length() - 1)));
					final boolean unknownStart = STRINGS.ASTERISK.equals(String.valueOf(filter.charAt(0)));
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

	/**
	 * Loads the specified text lines as the content of this string list.
	 * 
	 * @param text
	 * @return itself
	 */
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

	/**
	 * Gets this string list content as a string of text lines.
	 * 
	 * @return this string list content as a string of text lines
	 */
	@Override public String toString() {
		return Strings.join(STRINGS.EOL, _list);
	}

}
