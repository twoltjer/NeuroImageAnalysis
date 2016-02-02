package system;

import java.io.File;
import java.util.ArrayList;

import threads.GUIThread;
import threads.ImageProcessingThreadA;
import logging.Log;

/**
 * Runs system operations, such as effectively crashing the program in a stable
 * state. #irony
 * 
 * @author twtduck
 */
public abstract class Ops {
	/**
	 * Exits the program in a friendly way. Essentially makes the log save
	 * first. Now, if this is triggered by an error in closing the log, it
	 * crashes the computer, but that wouldn't ever happen, right?
	 */
	public static void exit() {
		Log.close();
		System.exit(0);
	}

	/**
	 * Starts the image processing!
	 */
	public static void startScan() {
		ImageProcessingThreadA imgThreadA = new ImageProcessingThreadA(
				"Image Processing Thread A");
		imgThreadA.start();
	}

	/**
	 * Starts the GUI. This should actually be above the other method, now that
	 * I think about it. Eh, doesn't really matter too much.
	 */
	public static void startGUI() {
		GUIThread gui = new GUIThread("GUI Thread");
		gui.start();
	}

	/**
	 * Gets the case name from a file name. 
	 * 
	 * @param name
	 *            the name of the file
	 * @return the case name
	 */
	public static String getCaseNameFromFilename(String name) {
		String caseName = "";
		boolean isA = (name.indexOf("(A)") > 0);
		
		String caseNumberAndStain = name.substring(0,name.lastIndexOf("-"));
		
		caseName += caseNumberAndStain;
		
		if(isA) {
			caseName += "-A";
		}
		
		return caseName;
	}

	/**
	 * Checks a string to see if it's a number
	 * 
	 * @param s
	 *            a one character string
	 * @return whether or not it's a number
	 */
	public static boolean isNumber(String s) {
		if (s.length() != 1)
			return false;
		ArrayList<String> numberStrings = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			numberStrings.add(Integer.toString(i));
		}
		for (String test : numberStrings) {
			if (s.equals(test))
				return true;
		}
		return false;
	}

	/**
	 * Sums all the numbers in an array of doubles
	 * 
	 * @param arr
	 *            the array of doubles
	 * @return the sum of the array's values
	 */
	public static double doubleArraySum(double[] arr) {
		double sum = 0d;
		for (double val : arr) {
			sum += val;
		}
		return sum;
	}

	/**
	 * Finds the average standard deviation from the average value for a set of
	 * doubles
	 * 
	 * @param arr
	 *            the set of doubles
	 * @param averageValue
	 *            the average of that set
	 * @return the average percent deviation
	 */
	public static double doubleArrayStandardDeviation(double[] arr, double avg) {
		double variance = 0;
		for (double d : arr) {
			variance += (Math.pow((d - avg), 2) / ((double) arr.length));
		}
		return Math.sqrt(variance);
	}

	/**
	 * Lists all files in a directory, and its sub-directories, and
	 * sub-sub-directories....
	 * 
	 * @param parent
	 *            the parent directory to list
	 * @return all non-directory files from that directory and its children
	 */
	public static File[] listFilesRecursively(File parent) {
		ArrayList<File> nonDirs = new ArrayList<File>();
		File[] temp = parent.listFiles();
		for(File f : temp) {
			if(f.isDirectory()) {
				File[] thdir = Ops.listFilesRecursively(f);
				for(File g : thdir) {
					nonDirs.add(g);
				}
			} else {
				nonDirs.add(f);
			}
		}
		File[] rtn = new File[nonDirs.size()];
		for(int i = 0; i < rtn.length; i++) {
			rtn[i] = nonDirs.get(i);
		}
		return rtn;
	}
}
