/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;

/**
 * Useful self spawned runnable class.
 */
public abstract class SelfSpawned extends Ace implements Runnable {

	/**
	 * Default constructor.
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned() {
		Threads.isolatedSpawn(this);
	}

	/**
	 * Constructor accepting a name.
	 * 
	 * @param name
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final String name) {
		Threads.isolatedSpawn(this, name);
	}

	/**
	 * Constructor accepting a delay.
	 * 
	 * @param delay
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final long delay) {
		Threads.delayedSpawn(delay, this);
	}

	/**
	 * Constructor accepting a name and a delay.
	 * 
	 * @param name
	 * @param delay
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final String name, final long delay) {
		Threads.delayedSpawn(delay, this, name);
	}

	/**
	 * Constructor accepting a conditional value that defines if this self spawn runnable will be executed (must be <tt>true</tt>).
	 * 
	 * @param condition 
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition) {
		if (condition) {
			Threads.isolatedSpawn(this);
		}
	}

	/**
	 * Constructor accepting a name and a conditional value that defines if this self spawn runnable will be executed (must be <tt>true</tt>).
	 * 
	 * @param condition 
	 * @param name 
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition, final String name) {
		if (condition) {
			Threads.isolatedSpawn(this, name);
		}
	}

	/**
	 * Constructor accepting a delay and a conditional value that defines if this self spawn runnable will be executed (must be <tt>true</tt>).
	 * 
	 * @param condition 
	 * @param delay 
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition, final long delay) {
		if (condition) {
			Threads.delayedSpawn(delay, this);
		}
	}


	/**
	 * Constructor accepting a name, a delay and a conditional value that defines if this self spawn runnable will be executed (must be <tt>true</tt>).
	 * 
	 * @param condition 
	 * @param name 
	 * @param delay 
	 */
	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition, final String name, final long delay) {
		if (condition) {
			Threads.delayedSpawn(delay, this, name);
		}
	}

}
