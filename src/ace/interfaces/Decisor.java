/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * Useful interface to give your classes a common method to decide based in a set of given parameters.
 */
public interface Decisor<T> {
	
	boolean decide(final T... parameters);
	
}
