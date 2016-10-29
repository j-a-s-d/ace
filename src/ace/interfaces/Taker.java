/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * Useful interface to give your classes a take method that returns a specified type.
 */
public interface Taker<T> {
	
	T take();
	
}
