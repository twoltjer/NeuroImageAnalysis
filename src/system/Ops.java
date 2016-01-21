package system;

import java.util.ArrayList;

import threads.GUIThread;
import threads.ImageProcessingThreadA;
import logging.Log;

/**
 * Runs system operations, such as effectively crashing the program in a stable state. #irony
 * @author Thomas
 */
public abstract class Ops {
	public static void exit()
	{
		Log.close();
		System.exit(0);
	}

	public static void startScan() {
		ImageProcessingThreadA imgThreadA = new ImageProcessingThreadA("Image Processing Thread A");
		imgThreadA.start();
	}
	
	public static void startGUI() {
		GUIThread gui = new GUIThread("GUI Thread");
		gui.start();
	}

	public static int getCaseNumberFromFilename(String name) {
		String preSuffix = name.substring(0, name.indexOf("-"));
		String numberBuilder = "";
		// Remove all characters except numbers
		for(int i = 0; i < preSuffix.length(); i++) {
			if(isNumber(preSuffix.substring(i, i+1)))
				numberBuilder += preSuffix.substring(i, i+1);
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
		if(s.length() != 1)
			return false;
		ArrayList<String> numberStrings = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			numberStrings.add(Integer.toString(i));
		}
		for(String test : numberStrings) {
			if(s.equals(test))
				return true;
		}
		return false;
	}
}
