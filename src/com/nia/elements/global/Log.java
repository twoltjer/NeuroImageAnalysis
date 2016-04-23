package com.nia.elements.global;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.nia.control.Main;
import com.nia.elements.instance.ErrorLog;

public abstract class Log {

	public static ErrorLog err;
	public static boolean consoleOutput = false;
	
	private static PrintWriter logFile; 
	
	public static void init(String fileLocation) {
		try {
			logFile = new PrintWriter(fileLocation);
		} catch (FileNotFoundException e) {
			Main.stop(e);
		}
		err = new ErrorLog();
		Log.println("Log file started");
	}
	
	public static void println(String arg0) {
		logFile.println("[LOG] \t" + arg0);
	}
	
	public static void close() {
		logFile.close();
	}
}
