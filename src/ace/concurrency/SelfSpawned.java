/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;

public abstract class SelfSpawned extends Ace implements Runnable {

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned() {
		Threads.isolatedSpawn(this);
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final String name) {
		Threads.isolatedSpawn(this, name);
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final long delay) {
		Threads.delayedSpawn(delay, this);
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final String name, final long delay) {
		Threads.delayedSpawn(delay, this, name);
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition) {
		if (condition) {
			Threads.isolatedSpawn(this);
		}
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition, final String name) {
		if (condition) {
			Threads.isolatedSpawn(this, name);
		}
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition, final long delay) {
		if (condition) {
			Threads.delayedSpawn(delay, this);
		}
	}

	@SuppressWarnings("LeakingThisInConstructor")
	public SelfSpawned(final boolean condition, final String name, final long delay) {
		if (condition) {
			Threads.delayedSpawn(delay, this, name);
		}
	}

}
