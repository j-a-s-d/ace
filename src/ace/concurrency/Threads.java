/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.concurrency;

import ace.Ace;
import ace.Sandboxed;
import ace.interfaces.Evaluable;
import java.util.concurrent.*;

public class Threads extends Ace {

	// EXECUTORS RELATED
	public static final int DEFAULT = -1;
	public static final int UNLIMITED = 0;
	public static final int SINGLE = 1;

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
	public static final Boolean delayedEvaluation(final long delay, final Evaluable evaluable, final Object... parameters) throws Exception {
		return (Boolean) createWaiter(delay, new Callable() {
			/*@Override*/ public Object call() throws Exception {
				return evaluable != null ? evaluable.evaluate(parameters) : null;
			}
		}).get();
	}

	public static final Boolean delayedSandboxedEvaluation(final long delay, final Boolean defaultValue, final Evaluable evaluable, final Object... parameters) {
		return new Sandboxed<Boolean>() {
			/*@Override*/ public Boolean run() throws Exception {
				return delayedEvaluation(delay, evaluable, parameters);
			}
		}.go(defaultValue);
	}

	// RUNNABLE RELATED
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

	public static final void delayedSpawn(final long delay, final Runnable r) {
		delayedRun(delay, new Runnable() {
			/*@Override*/ public void run() {
				new Thread(r).start();
			}
		});
	}

	public static final void delayedSpawn(final long delay, final Runnable r, final String name) {
		delayedRun(delay, new Runnable() {
			/*@Override*/ public void run() {
				new Thread(r, name).start();
			}
		});
	}

	public static final void isolatedSpawn(final Runnable r) {
		final ExecutorService executor = Threads.getExecutorService(Threads.SINGLE);
		executor.execute(new Thread() {
			/*@Override*/ public void run() {
				r.run();
				executor.shutdown();
			}
		});
	}

	public static final void isolatedSpawn(final Runnable r, final String name) {
		final ExecutorService executor = Threads.getExecutorService(Threads.SINGLE);
		executor.execute(new Thread(name) {
			/*@Override*/ public void run() {
				r.run();
				executor.shutdown();
			}
		});
	}

	public static final void isolatedSpawn(final int count, final Runnable r) {
		isolatedSpawn(count, r, 0);
	}

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

	public static final void spawn(final Runnable r) {
		new Thread(r).start();
	}

	public static final void spawn(final Runnable r, final String name) {
		new Thread(r, name).start();
	}

	public static final void spawn(final int count, final Runnable r) {
		spawn(count, r, 0);
	}

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
	public static synchronized final StackTraceElement getThreadStackTraceElement(final Thread thread, final int index) {
		final StackTraceElement[] stes = thread.getStackTrace();
		return stes.length > index ? stes[index] : null;
	}

	public static synchronized final StackTraceElement getCurrentThreadStackTraceElement(final int index) {
		return getThreadStackTraceElement(Thread.currentThread(), index);
	}

	public static synchronized final Thread getThreadByName(final String threadName) {
		for (final Thread thread : Thread.getAllStackTraces().keySet()) {
			if (thread.getName().equals(threadName)) {
				return thread;
			}
		}
		return null;
	}

	public static synchronized final boolean existsThread(final String threadName) {
		for (final Thread thread : Thread.getAllStackTraces().keySet()) {
			if (thread.getName().equals(threadName)) {
				return true;
			}
		}
		return false;
	}

}
