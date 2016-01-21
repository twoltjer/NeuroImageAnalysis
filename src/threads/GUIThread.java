package threads;

import gui.ScanWindow;
import logging.Log;

/**
 * A thread that starts the gui and that it runs in. Don't want something like
 * the gui on the main thread, because it could slow other stuff down.
 * 
 * @author twtduck
 * 
 */
public class GUIThread implements Runnable {
	private String name;
	private Thread thread;

	/**
	 * Classic constructor
	 * 
	 * @param name
	 *            name of the thread. Hint: Use something like "debug thread"
	 */
	public GUIThread(String name) {
		this.name = name;
		logWrite("Creating thread " + name);
	}

	/**
	 * This runs as an internal method when Start() is called
	 */
	public void run() {
		logWrite("Running " + this.name);

		// Create scan window
		ScanWindow.init();
		ScanWindow.show();

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
