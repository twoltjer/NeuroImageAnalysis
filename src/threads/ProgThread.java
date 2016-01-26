package threads;

import gui.ProgressWindow;
import logging.Log;

/**
 * Template for new threads, and for testing multithreading stuff.
 * 
 * @author twtduck
 * 
 */
public class ProgThread implements Runnable {
	private String name;
	private Thread thread;

	/**
	 * Classic constructor
	 * 
	 * @param name
	 *            name of the thread. Hint: Use something like "debug thread"
	 */
	public ProgThread(String name) {
		this.name = name;
		logWrite("Creating thread " + name);
	}

	/**
	 * This runs as an internal method when start() is called
	 */
	public void run() {
		logWrite("Running " + this.name);
		ProgressWindow.init();
		ProgressWindow.show();
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
