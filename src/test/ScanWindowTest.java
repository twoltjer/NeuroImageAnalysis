package test;

import gui.ScanWindow;
import logging.Log;

public class ScanWindowTest {
	public static void main(String[] args) {
		Log.start();
		ScanWindow.init();
		ScanWindow.show();
	}
}
