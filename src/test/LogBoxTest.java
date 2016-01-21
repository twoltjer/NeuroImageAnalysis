package test;

import logging.Log;
import logging.LogWindowManager;

/**
 * Tests the log window. Really not a necessary test anymore, but sometimes I
 * run it out of nostalgic purposes.
 * 
 * @author twtduck
 * 
 */
public class LogBoxTest {
	public static void main(String[] args) {
		Log.start();
		LogWindowManager.init();
		LogWindowManager.show();
		Log.write("Test text", Log.CONSOLE);
	}
}