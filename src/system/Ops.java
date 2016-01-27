package system;

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
	 * Gets the case number from a file name. This is way too complex 'cause I
	 * really don't want to read into the interactions between strings and
	 * integers.
	 * 
	 * @param name
	 *            the name of the file
	 * @return the case number
	 */
	public static int getCaseNumberFromFilename(String name) {
		String preSuffix = name.substring(0, name.indexOf("-"));
		String numberBuilder = "";
		// Remove all characters except numbers
		for (int i = 0; i < preSuffix.length(); i++) {
			if (isNumber(preSuffix.substring(i, i + 1)))
				numberBuilder += preSuffix.substring(i, i + 1);
		}
		int caseNum;
		try {
			caseNum = Integer.parseInt(numberBuilder);
		} catch (Exception e) {
			return 0;
		}
		return caseNum;
	}

	/**
	 * Checks a string to see if it's a number
	 * @param s a one character string
	 * @return whether or not it's a number
	 */
	private static boolean isNumber(String s) {
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
	public static double doubleArrayStandardDeviation(double[] arr,
			double avg) {
		double variance = 0;
		for(double d : arr) {
			variance += (Math.pow((d - avg), 2) / ((double) arr.length));
		}
		return Math.sqrt(variance);
	}
}
