/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * Useful interface to give your classes a treat method that receives a parameter of the same type that it returns.
 */
public interface Treater<T> {
	
	T treat(final T item);
	
}
