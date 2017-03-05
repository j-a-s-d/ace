/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import ace.Ace;

/**
 * Useful string composing class.
 */
public class StringComposer extends Ace {

	private StringBuilder _sb;

	public StringComposer() {
		reset();
	}

	public StringComposer(final String value) {
		this();
		append(value);
	}

	public StringComposer(final Character value) {
		this();
		append(value);
	}

	public StringComposer(final char value) {
		this();
		append(value);
	}

	public StringComposer(final char[] value) {
		this();
		append(value);
	}

	public StringComposer(final CharSequence value) {
		this();
		append(value);
	}

	public StringComposer(final StringBuffer value) {
		this();
		append(value);
	}

	public StringComposer(final StringBuilder value) {
		this();
		append(value);
	}

	public StringComposer(final StringComposer value) {
		this();
		append(value);
	}

	public StringComposer(final String... value) {
		this();
		append(value);
	}

	public final int capacity() {
		return _sb.capacity();
	}

	public final int length() {
		return _sb.length();
	}

	public final StringComposer reset() {
		_sb = new StringBuilder();
		return this;
	}

	public final StringComposer append(final String value) {
		_sb.append(value);
		return this;
	}

	public final StringComposer append(final Character value) {
		_sb.append(value);
		return this;
	}

	public final StringComposer append(final char value) {
		_sb.append(value);
		return this;
	}

	public final StringComposer append(final char[] value) {
		_sb.append(value);
		return this;
	}

	public final StringComposer append(final CharSequence value) {
		_sb.append(value);
		return this;
	}

	public final StringComposer append(final StringBuffer value) {
		if (value != null) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final StringBuilder value) {
		if (value != null) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final StringComposer value) {
		if (value != null) {
			_sb.append(value.toString());
		}
		return this;
	}

	public final StringComposer append(final String... values) {
		for (final String s : values) {
			_sb.append(s);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final String value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final Character value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final char value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final char[] value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final CharSequence value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final StringBuffer value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final StringBuilder value) {
		if (condition) {
			_sb.append(value);
		}
		return this;
	}

	public final StringComposer append(final boolean condition, final String... values) {
		if (condition) {
			for (final String s : values) {
				_sb.append(s);
			}
		}
		return this;
	}

	@Override public final String toString() {
		return _sb.toString();
	}

}
