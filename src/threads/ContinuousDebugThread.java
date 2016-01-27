package threads;

import logging.Log;
import logging.LogWindowManager;

/**
 * This thing can run all day long if necessary. Basically a separate thread for
 * debugging. I last used it for trying to debug the scrool bar, but that didn't
 * even work.
 * 
 * @author twtduck
 * 
 */
public class ContinuousDebugThread implements Runnable {
	private String name;
	private Thread thread;

	/**
	 * Classic constructor
	 * @param name name of the thread. Hint: Use something like "debug thread"
	 */
	public ContinuousDebugThread(String name) {
		this.name = name;
		logWrite("Creating thread " + name);
	}

	/**
	 * This runs as an internal method when Start() is called
	 */
	public void run() {
		logWrite("Running " + this.name);
		try {
			while (true) {

				// Most recently, this was used to debug the scroll bar for the
				// log window.
				System.out.println("Scroll bar value: "
						+ LogWindowManager.getScrollpane()
								.getVerticalScrollBar().getValue());
				System.out.println("Scroll bar max value: "
						+ LogWindowManager.getScrollpane()
								.getVerticalScrollBar().getMaximum());
				System.out.println("Moving scroll bar to max value");
				LogWindowManager
						.getScrollpane()
						.getVerticalScrollBar()
						.setValue(
								LogWindowManager.getScrollpane()
										.getVerticalScrollBar().getMaximum());
				;
				// Let the thread sleep for a while. In milliseconds
				Thread.sleep(100);
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
