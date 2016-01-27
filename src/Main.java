import logging.Log;
import logging.LogWindowManager;
import system.Config;
import system.Ops;

/**
 * This class is only a simple procedural function that runs the different parts
 * of the program in the correct order. It also sends an argument to
 * Config.setDefaultLists, which is likely to be null.
 * 
 * @author twtduck
 */
public class Main {
	/**
	 * The function to start the program
	 * 
	 * @param args
	 *            if there is an argument, the program interprets it as a
	 *            predetermined scan directory
	 */
	public static void main(String[] args) {
		// Start log
		Log.start();

		// Create log window
		LogWindowManager.init();
		LogWindowManager.show();

		// Set defaults
		try {
			Config.setDefaultLists(args[0]);
		} catch (Exception e) {
			Config.setDefaultLists(null);
		}

		Ops.startGUI();
	}
}
