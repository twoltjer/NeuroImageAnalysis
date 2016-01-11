package test;

import java.io.File;

/**
 * Removes log files from log file tester
 * @author Thomas Woltjer
 */
public class CleanLogs {
	public static void main(String[] args)
	{
		final String[] LOG_NAMES = {
				"startlog.txt", 
				"newlog.txt"};
		
		for(String logName : LOG_NAMES) {
			File logFile = new File(logName);
			
			if(logFile.exists()) {
				System.out.println("File found: " + logName);
				logFile.delete();
				System.out.println("File deleted: " + logName);
			} else {
				System.out.println("File not found: " + logName);
			}
		}
	}
}
