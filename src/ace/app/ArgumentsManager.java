/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.app;

import ace.Ace;
import java.util.ArrayList;
import java.util.List;

/**
 * Command line arguments handler.
 */
public class ArgumentsManager extends Ace {

	class Argument<T> {

		List<String> nicks;
		String name;
		String usage;
		boolean isValued;
		boolean isRequired;
		boolean isPresent;
		T value;

	}

	public static final String[] DEFAULT_PREFIXES = new String[] { "--" };

	private final String[] _prefixes;
	private final List<Argument> _registered = new ArrayList();
	private Argument _absentParameter;
	private int _registeredParameters = 0;
	private int _registeredOptions = 0;
	private int _suppliedOptions;

	public ArgumentsManager(final String... prefixes) {
		_prefixes = prefixes.length > 0 ? prefixes : DEFAULT_PREFIXES;
	}

	private <T> ArgumentsManager addArgument(final String argName, final String argUsage, final boolean argRequired, final boolean argValued, final T argDefault) {
		_registered.add(new Argument<T>() {
			{
				nicks = new ArrayList(_prefixes.length) {
					{
						for (int i = 0; i < _prefixes.length; i++) {
							add(_prefixes[i] + argName);
						}
					}
				};
				name = argName;
				usage = argUsage;
				if (isRequired = argRequired) {
					_registeredParameters++;
				} else {
					_registeredOptions++;
				}
				isValued = argValued;
				value = argDefault;
				isPresent = false;
			}
		});
		return this;
	}

	public ArgumentsManager addOption(final String name, final String usage) {
		return addArgument(name, usage, false, false, null);
	}

	public ArgumentsManager addParameter(final String name, final String usage) {
		return addArgument(name, usage, true, false, null);
	}

	public <T> ArgumentsManager addValuedOption(final String name, final String usage, final T defValue) {
		return addArgument(name, usage, false, true, defValue);
	}

	public <T> ArgumentsManager addValuedParameter(final String name, final String usage) {
		return addArgument(name, usage, true, true, null);
	}

	private Argument findByName(final String name) {
		for (final Argument r : _registered) {
			if (r.name.equals(name)) {
				return r;
			}
		}
		return null;
	}

	public boolean remove(final String arg) {
		final Argument reg = findByName(arg);
		if (reg != null && _registered.remove(reg)) {
			if (reg.isRequired) {
				_registeredParameters--;
			} else {
				_registeredOptions--;
			}
			return true;
		}
		return false;
	}

	public boolean process(final String[] args) {
		// reset working fields
		_absentParameter = null;
		_suppliedOptions = 0;
		// loop registered arguments
		for (final Argument reg : _registered) {
			// loop current arguments
			for (final String arg : args) {
				if (reg.isPresent) {
					reg.value = arg;
					break;
				} else if (reg.nicks.contains(arg)) {
					reg.isPresent = true;
					if (!reg.isValued) {
						break;
					}
				}
			}
			// evaluate registered argument state
			if (!reg.isRequired) {
				if (reg.isPresent) {
					_suppliedOptions++;
				}
			} else if (!reg.isPresent) {
				_absentParameter = reg;
				return false;
			}
		}
		return true;
	}

	public int countRegisteredParameters() {
		return _registeredParameters;
	}

	public int countRegisteredOptions() {
		return _registeredOptions;
	}

	public int countSuppliedOptions() {
		return _suppliedOptions;
	}

	public boolean hadAbsentParameter() {
		return assigned(_absentParameter);
	}

	public String getAbsentParameterName() {
		return _absentParameter.name;
	}

	public String getAbsentParameterUsage() {
		return _absentParameter.usage;
	}

	public boolean has(final String arg) {
		final Argument reg = findByName(arg);
		return reg != null ? reg.isPresent : false;
	}

	public <T> T get(final String arg) {
		final Argument reg = findByName(arg);
		return reg != null ? (T) reg.value : null;
	}

}
