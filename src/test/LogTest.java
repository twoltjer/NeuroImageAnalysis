package test;

import logging.Log;

/**
 * This tests writing to log files. It also creates two files, which should be
 * deleted after running this test.
 * 
 * @author twtduck
 */
public class LogTest {
	public static void main(String[] args) {
		// Start log
		Log.start();

		Log.write("Good stuff happening", Log.STANDARD);

		Log.changeLogFile("newlog.txt");

		Log.write("Printing this", Log.CONSOLE);

		Log.write("We had an error!", Log.ERROR);
	}
}
