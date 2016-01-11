import logging.Log;
import logging.LogWindowManager;
import system.Config;
import system.Ops;

/**
 * This class is only a simple procedural function that runs the different parts
 * of the program in the correct order.
 * 
 * @author Thomas Woltjer
 */
public class Main {
	public static void main(String[] args) {
		// Start log
		Log.start();

		// Create log window
		LogWindowManager.init();
		LogWindowManager.show();
		
		// Set defaults
		Config.setDefaultLists();
		
		Ops.startGUI();
	}
}
