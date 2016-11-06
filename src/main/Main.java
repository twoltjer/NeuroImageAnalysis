package main;

import global.Config;
import global.DebugMessenger;
import gui.GUIThread;
import testing.MemBarMaker;

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
		guiThread.startThread(GUIThread.CHOOSER_HUB);
		if(Config.DEBUG_OUTPUT_ENABLED)
			MemBarMaker.main(null);
		DebugMessenger.out("Main complete");
	}
}
