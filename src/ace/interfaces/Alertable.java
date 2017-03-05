/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * Useful interface to give your classes a common method to be alerted with a set of given parameters.
 */
public interface Alertable<T> {

	void alert(final T... parameters);

}
