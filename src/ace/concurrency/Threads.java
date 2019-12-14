/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.Sandboxed;
import ace.interfaces.Evaluable;
import java.util.concurrent.*;

/**
 * Utility class for working with threads.
 */
public class Threads extends Ace {

	// EXECUTORS RELATED

	public static final int DEFAULT = -1;
	public static final int UNLIMITED = 0;
	public static final int SINGLE = 1;

	/**
	 * Gets an executor service instance with kind will be according to the specified execution type:
	 *   DEFAULT (-1) will return <tt>null</tt>
	 *   UNLIMITED (0) will return a cached thread pool
	 *   SINGLE (1) will return a single thread pool executor
	 *   OTHER (*) will return a fixed thread pool that reuses the specified number of threads
	 * 
	 * @param execution
	 * @return an executor service instance
	 */
	public static synchronized final ExecutorService getExecutorService(final int execution) {
		switch (execution) {
			case DEFAULT:
				return null;
			case UNLIMITED:
				return Executors.newCachedThreadPool();
			case SINGLE:
				return Executors.newSingleThreadExecutor();
			default:
				return Executors.newFixedThreadPool(execution);
		}
	}

	// FUTURES RELATED

	/**
	 * Creates a single executor service in which will be submitted an anonymous callable task
	 * in which will be waited the specified delay and then will be executed the specified callable task.
	 * The routine returns the corresponding future interface instance to evaluate the result of that task.
	 * 
	 * @param <T>
	 * @param delay
	 * @param callable
	 * @return the corresponding future interface instance
	 */
	public static final <T> Future<T> createWaiter(final long delay, final Callable<T> callable) {
		final ExecutorService es = Threads.getExecutorService(Threads.SINGLE);
		return (Future<T>) es.submit(new Callable() {
			/*@Override*/ public T call() throws Exception {
				T result = null;
				if (callable != null) {
					Thread.sleep(delay);
					result = callable.call();
				}
				es.shutdown(); // NOTE: this cleanup avoids process to "hang" on shutdown
				return result;
			}
		});
	}

	// EVALUABLE RELATED

	/**
	 * Asynchronically evaluates the specified evaluable interface instance with the specified parameters after the specified delay.
	 * 
	 * @param delay
	 * @param evaluable
	 * @param parameters
	 * @return <tt>true</tt> if the evaluation was successful, <tt>false</tt> otherwise
	 * @throws Exception 
	 */
	public static final Boolean delayedEvaluation(final long delay, final Evaluable evaluable, final Object... parameters) throws Exception {
		return (Boolean) createWaiter(delay, new Callable() {
			/*@Override*/ public Object call() throws Exception {
				return evaluable != null ? evaluable.evaluate(parameters) : null;
			}
		}).get();
	}

	/**
	 * Asynchronically evaluates the specified evaluable interface instance with the specified parameters after the specified delay inside a Sandbox instance with the specified default value.
	 * 
	 * @param delay
	 * @param defaultValue
	 * @param evaluable
	 * @param parameters
	 * @return <tt>true</tt> if the evaluation was successful or the specified default value otherwise
	 */
	public static final Boolean delayedSandboxedEvaluation(final long delay, final Boolean defaultValue, final Evaluable evaluable, final Object... parameters) {
		return new Sandboxed<Boolean>() {
			/*@Override*/ public Boolean run() throws Exception {
				return delayedEvaluation(delay, evaluable, parameters);
			}
		}.go(defaultValue);
	}

	// RUNNABLE RELATED

	/**
	 * Asynchronically runs the specified runnable instance after the specified delay inside a Sandbox instance.
	 * 
	 * @param delay
	 * @param r
	 */
	public static final void delayedRun(final long delay, final Runnable r) {
		new Sandboxed<Void>() {
			/*@Override*/ public Void run() throws Exception {
				return (Void) createWaiter(delay, new Callable() {
					/*@Override*/ public Void call() throws Exception {
						r.run();
						return null;
					}
				}).get();
			}
		}.go();
	}

	/**
	 * Asynchronically runs the specified runnable instance in a separate thread after the specified delay inside a Sandbox instance.
	 * 
	 * @param delay
	 * @param r
	 */
	public static final void delayedSpawn(final long delay, final Runnable r) {
		delayedRun(delay, new Runnable() {
			/*@Override*/ public void run() {
				new Thread(r).start();
			}
		});
	}

	/**
	 * Asynchronically runs the specified runnable instance in a separate thread withe the specified name after the specified delay inside a Sandbox instance.
	 * 
	 * @param delay
	 * @param r
	 */
	public static final void delayedSpawn(final long delay, final Runnable r, final String name) {
		delayedRun(delay, new Runnable() {
			/*@Override*/ public void run() {
				new Thread(r, name).start();
			}
		});
	}

	/**
	 * Asynchronically runs the specified runnable instance in a separate thread in a single executor service.
	 * 
	 * @param r
	 */
	public static final void isolatedSpawn(final Runnable r) {
		final ExecutorService executor = Threads.getExecutorService(Threads.SINGLE);
		executor.execute(new Thread() {
			/*@Override*/ public void run() {
				r.run();
				executor.shutdown();
			}
		});
	}

	/**
	 * Asynchronically runs the specified runnable instance in a separate thread with the specified name in a single executor service.
	 * 
	 * @param r
	 * @param name
	 */
	public static final void isolatedSpawn(final Runnable r, final String name) {
		final ExecutorService executor = Threads.getExecutorService(Threads.SINGLE);
		executor.execute(new Thread(name) {
			/*@Override*/ public void run() {
				r.run();
				executor.shutdown();
			}
		});
	}

	/**
	 * Asynchronically runs the specified runnable instance in the specified amount of separate threads in separate single executor services.
	 * 
	 * @param count
	 * @param r
	 */
	public static final void isolatedSpawn(final int count, final Runnable r) {
		isolatedSpawn(count, r, 0);
	}

	/**
	 * Asynchronically runs the specified runnable instance in the specified amount of separate threads in separate single executor services waiting the specified delay between each thread spawn.
	 * 
	 * @param count
	 * @param r
	 * @param delay
	 */
	public static final void isolatedSpawn(final int count, final Runnable r, final long delay) {
		isolatedSpawn(new Runnable() {
			/*@Override*/ public void run() {
				try {
					for (int i = 0; i < count; i++) {
						isolatedSpawn(r);
						Thread.sleep(delay);
					}
				} catch (final Exception e) {
					GEH.setLastException(e);
				}
			}
		});
	}

	/**
	 * Asynchronically runs the specified runnable instance in the specified amount of separate threads in separate single executor services waiting the specified delay between each thread spawn and naming threads with the specified name prefix.
	 * 
	 * @param count
	 * @param r
	 * @param delay
	 * @param namePrefix
	 */
	public static final void isolatedSpawn(final int count, final Runnable r, final long delay, final String namePrefix) {
		isolatedSpawn(new Runnable() {
			/*@Override*/ public void run() {
				try {
					for (int i = 0; i < count; i++) {
						isolatedSpawn(r, namePrefix + Integer.toString(i));
						Thread.sleep(delay);
					}
				} catch (final Exception e) {
					GEH.setLastException(e);
				}
			}
		});
	}

	/**
	 * Spawns the specified runnable instance in a separate thread.
	 * 
	 * @param r 
	 */
	public static final void spawn(final Runnable r) {
		new Thread(r).start();
	}

	/**
	 * Spawns the specified runnable instance in a separate thread with the specified name.
	 * 
	 * @param r 
	 * @param name 
	 */
	public static final void spawn(final Runnable r, final String name) {
		new Thread(r, name).start();
	}

	/**
	 * Spawns the specified runnable instance in the specified amount of separate threads.
	 * 
	 * @param count
	 * @param r 
	 */
	public static final void spawn(final int count, final Runnable r) {
		spawn(count, r, 0);
	}

	/**
	 * Spawns the specified runnable instance in the specified amount of separate threads waiting the specified delay between each thread spawn.
	 * 
	 * @param count
	 * @param r 
	 * @param delay 
	 */
	public static final void spawn(final int count, final Runnable r, final long delay) {
		spawn(new Runnable() {
			/*@Override*/ public void run() {
				try {
					for (int i = 0; i < count; i++) {
						spawn(r);
						Thread.sleep(delay);
					}
				} catch (final Exception e) {
					GEH.setLastException(e);
				}
			}
		});
	}

	/**
	 * Spawns the specified runnable instance in the specified amount of separate threads waiting the specified delay between each thread spawn and naming threads with the specified name prefix.
	 * 
	 * @param count
	 * @param r 
	 * @param delay 
	 * @param namePrefix 
	 */
	public static final void spawn(final int count, final Runnable r, final long delay, final String namePrefix) {
		spawn(new Runnable() {
			/*@Override*/ public void run() {
				try {
					for (int i = 0; i < count; i++) {
						spawn(r, namePrefix + Integer.toString(i));
						Thread.sleep(delay);
					}
				} catch (final Exception e) {
					GEH.setLastException(e);
				}
			}
		});
	}

	// STACK TRACE RELATED

	/**
	 * Gets the specified stack trace element from the specified thread.
	 * 
	 * @param thread
	 * @param index
	 * @return the specified stack trace element from the specified thread
	 */
	public static synchronized final StackTraceElement getThreadStackTraceElement(final Thread thread, final int index) {
		final StackTraceElement[] stes = thread.getStackTrace();
		return stes.length > index ? stes[index] : null;
	}

	/**
	 * Gets the specified stack trace element of the current thread.
	 * 
	 * @param index
	 * @return the specified stack trace element of the current thread
	 */
	public static synchronized final StackTraceElement getCurrentThreadStackTraceElement(final int index) {
		return getThreadStackTraceElement(Thread.currentThread(), index);
	}

	/**
	 * Gets the thread with the specified name if it exists.
	 * 
	 * @param threadName
	 * @return the thread with the specified name if it exists, <tt>null</tt> otherwise
	 */
	public static synchronized final Thread getThreadByName(final String threadName) {
		for (final Thread thread : Thread.getAllStackTraces().keySet()) {
			if (thread.getName().equals(threadName)) {
				return thread;
			}
		}
		return null;
	}

	/**
	 * Determines if a thread with the specified name exists.
	 * 
	 * @param threadName
	 * @return <tt>true</tt> if a thread with the specified name exists, <tt>false</tt> otherwise
	 */
	public static synchronized final boolean existsThread(final String threadName) {
		for (final Thread thread : Thread.getAllStackTraces().keySet()) {
			if (thread.getName().equals(threadName)) {
				return true;
			}
		}
		return false;
	}

}
