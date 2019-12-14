/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * Useful interface to evaluate parameters and return a value.
 */
public interface Evaluable {

	Object evaluate(final Object... parameters) throws Exception;

}
