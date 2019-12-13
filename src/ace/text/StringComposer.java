/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;

/**
 * Useful string composing class.
 */
public class StringComposer extends Ace {

	private StringBuilder _sb;

	/**
	 * Default constructor.
	 */
	public StringComposer() {
		reset();
	}

	/**
	 * Constructor accepting an initial value from the specified string.
	 * 
	 * @param value 
	 */
	public StringComposer(final String value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified character.
	 * 
	 * @param value 
	 */
	public StringComposer(final Character value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified char.
	 * 
	 * @param value 
	 */
	public StringComposer(final char value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified char array.
	 * 
	 * @param value 
	 */
	public StringComposer(final char[] value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified char sequence.
	 * 
	 * @param value 
	 */
	public StringComposer(final CharSequence value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified string buffer.
	 * 
	 * @param value 
	 */
	public StringComposer(final StringBuffer value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified string builder.
	 * 
	 * @param value 
	 */
	public StringComposer(final StringBuilder value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified string composer.
	 * 
	 * @param value 
	 */
	public StringComposer(final StringComposer value) {
		this();
		append(value);
	}

	/**
	 * Constructor accepting an initial value from the specified set of initial strings.
	 * 
	 * @param value 
	 */
	public StringComposer(final String... value) {
		this();
		append(value);
	}

	/**
	 * Gets the capacity of this string composer.
	 * 
	 * @return the capacity of this string composer
	 */
	public final int capacity() {
		return _sb.capacity();
	}

	/**
	 * Gets the length of this string composer.
	 * 
	 * @return the length of this string composer
	 */
	public final int length() {
		return _sb.length();
	}

	/**
	 * Resets the content of this string composer.
	 * 
	 * @return itself
	 */
	public final StringComposer reset() {
		_sb = new StringBuilder();
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final String value) {
		_sb.append(value);
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final Character value) {
		_sb.append(value);
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final char value) {
		_sb.append(value);
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final char[] value) {
		_sb.append(value);
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final CharSequence value) {
		_sb.append(value);
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final StringBuffer value) {
		if (value != null) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final StringBuilder value) {
		if (value != null) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value.
	 * 
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final StringComposer value) {
		if (value != null) {
			_sb.append(value.toString());
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified values.
	 * 
	 * @param values
	 * @return itself
	 */
	public final StringComposer append(final String... values) {
		for (final String s : values) {
			_sb.append(s);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final String value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final Character value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final char value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final char[] value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final CharSequence value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final StringBuffer value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified value if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param value
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final StringBuilder value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	/**
	 * Appends this string composer content with the specified values if the specified conditional value is <tt>true</tt>.
	 * 
	 * @param condition
	 * @param values
	 * @return itself
	 */
	public final StringComposer append(final boolean condition, final String... values) {
		if (condition) {
			for (final String s : values) {
				_sb.append(s);
			}
		}
		return this;
	}

	/**
	 * Gets this string composer content as string.
	 * 
	 * @return this string composer content as string
	 */
	@Override public final String toString() {
		return _sb.toString();
	}

}
