package system;

import Threads.GUIThread;
import Threads.ImageProcessingThreadA;
import logging.Log;

/**
 * Runs system operations, such as effectively crashing the program in a stable state. #irony
 * @author Thomas
 */
public abstract class Ops {
	public static void exit()
	{
		Log.close();
		System.exit(0);
	}

	public static void startScan() {
		ImageProcessingThreadA imgThreadA = new ImageProcessingThreadA("Image Processing Thread A");
		imgThreadA.start();
	}
	
	public static void startGUI() {
		GUIThread gui = new GUIThread("GUI Thread");
		gui.start();
	}
}
