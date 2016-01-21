package test;

import gui.ScanWindow;
import logging.Log;

/**
 * Tests displaying the scan window
 * 
 * @author twtduck
 * 
 */
public class ScanWindowTest {
	public static void main(String[] args) {
		Log.start();
		ScanWindow.init();
		ScanWindow.show();
	}
}
