package threads;

import logging.Log;

/**
 * Template for new threads, and for testing multithreading stuff.
 * 
 * @author twtduck
 * 
 */
public class ThreadExample implements Runnable {
	private String name;
	private Thread thread;

	/**
	 * Classic constructor
	 * 
	 * @param name
	 *            name of the thread. Hint: Use something like "debug thread"
	 */
	public ThreadExample(String name) {
		this.name = name;
		logWrite("Creating thread " + name);
	}

	/**
	 * This runs as an internal method when Start() is called
	 */
	public void run() {
		logWrite("Running " + this.name);
		try {
			for (int i = 4; i > 0; i--) {
				logWrite("Thread: " + this.name + ", " + i);
				// Let the thread sleep for a while. In milliseconds
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			logWrite("Thread " + this.name + " interrupted.");
		}
		logWrite("Thread " + this.name + " exiting.");
	}

	/**
	 * Use this to create a new thread. Then give it a ThreadTask object.
	 */
	public void start() {
		logWrite("Starting " + this.name);
		if (this.thread == null) {
			this.thread = new Thread(this, this.name);
			this.thread.start();
		}
	}

	/**
	 * Writes a debug message, to the log if it's initialized, or to the console
	 * if not
	 * 
	 * @param message
	 *            the message to write
	 */
	private void logWrite(String message) {
		if (Log.isInstantiated())
			Log.write(message, Log.DEBUG);
		else {
			System.out.println(message);
		}
	}
}
