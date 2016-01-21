import logging.Log;
import logging.LogWindowManager;
import system.Config;
import system.Ops;

/**
 * This class is only a simple procedural function that runs the different parts
 * of the program in the correct order.
 * 
 * @author twtduck
 */
public class Main {
	/**
	 * The function to start the program
	 * @param args currently does nothing. In the future it could point to a start directory.
	 * TODO: Point the start directory at the arguments passed to main()
	 */
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
