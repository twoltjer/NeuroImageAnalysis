package main;

import global.DebugMessenger;
import gui.GUIThread;

/**
 * Main class.
 * @author Thomas Woltjer
 *
 */
public class Main {
	/**
	 * Main method. Starts the threads and lets them roll.
	 * @param args
	 */
	public static void main(String[] args) {
		// start other threads
		DebugMessenger.out("Starting Main");
		GUIThread guiThread = new GUIThread();
		guiThread.start(GUIThread.CHOOSER_HUB);
		DebugMessenger.out("Main complete");
	}

}
