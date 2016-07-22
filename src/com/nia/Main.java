package com.nia;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	// Declare constants here
	final static String PROGRAM_NAME = "NIA";
	
	// Status variables
	public static boolean imageDirIsSet = false;
	public static File imageDir;
	public static boolean writeFileIsSet = false;
	public static File writeFilePath;
	
	public static MainWindow mainWin;
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Main.mainWin = new MainWindow();
		Main.mainWin.display();
	}
	
	public static boolean isReadyForScan() {
		if (!imageDirIsSet) {
			return false;
		} else if (!writeFileIsSet) {
			return false;
		}
		return true;
	}

	public static void updateButtons() {
		Main.mainWin.scanButton.setEnabled(Main.isReadyForScan());
	}
}
