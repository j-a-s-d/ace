/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.interfaces;

/**
 * Useful interface to give your classes a process method with a parameter.
 */
public interface Processor<T> {
	
	void process(final T item);
	
}
