// Start com/nia/control/Main.java

package com.nia.control;

import com.nia.elements.global.Log;
import com.nia.init.InitSequence;

/**
 * The alpha and omega class. This starts and ends the program. (The latter in a
 * colorful variety of ways.)
 * 
 * @author Thomas Woltjer
 *
 */
public class Main {

	public static void main(String[] args) {
		// Run the init sequence
		InitSequence.init();
	}

	/**
	 * Stop the program in an erroneous way, but give no feedback. This method
	 * should never be used if the other components of the program are written
	 * correctly.
	 */
	public static void stop() {
		System.exit(1);
	}

	/**
	 * When an Exception is thrown, print the stack trace and the message
	 * embedded in the Exception. Then, exit the program.
	 * 
	 * @param e
	 *            The exception thrown
	 */
	public static void stop(Exception e) {
		e.printStackTrace();
		stop();
	}

	/**
	 * In case there is a problem with the log, an alternative stop method is
	 * available. This prints directly to the system error stream.
	 * 
	 * @param message
	 *            The message to display
	 */
	public static void logStop(String arg0) {
		System.err.println(arg0);
		System.exit(2);
	}

	/**
	 * This is run when the program is supposed to exit, either because it
	 * finishes or the user closes it.
	 */
	public static void controlledStop() {
		Log.println("Program stopping now");
		Log.close();
		System.exit(0);
	}

}

// End com/nia/control/Main.java