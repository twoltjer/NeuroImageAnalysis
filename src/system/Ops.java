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
}
