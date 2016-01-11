package test;

import logging.Log;
import logging.LogWindowManager;

public class LogBoxTest {
	public static void main(String[] args) {
		Log.start();
		LogWindowManager.init();
		LogWindowManager.show();
		Log.write("Test text", Log.CONSOLE);
	}
}