package gui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import logging.Log;
import system.Config;

/**
 * Class to manage the progress window. This window will show a progress bar or
 * two visualizing the program's progress and will have a cancel button to stop
 * processing. I'm considering having a disk space progress bar as well, because
 * this thing is going to be handling a LOT of data one day.
 * 
 * @author twtduck
 * 
 */
public abstract class ProgressWindow {
	public static boolean isInitialized;
	public static int imageNumberProcessing;
	public static int totalNumberOfImages;
	public static int imageNumberReading;

	// Window framework
	private static JFrame windowFrame;
	private static Container windowContentPane;

	// Window elements
	private static JLabel progressTitleLabel;
	private static JLabel imageNumberLabel;
	private static JLabel diskSpaceLabel;
	private static JProgressBar currentImageProgress;
	private static JProgressBar totalProgress;
	private static JProgressBar diskFillProgress;

	/**
	 * The init method for the ProgressWindow class.
	 * 
	 * To run any other methods, this needs to be run first.
	 * 
	 * It instantiates the window elements, creates the window framework, and
	 * adds the elements to the framework.
	 * 
	 * It also creates a window listener to properly execute some code when the
	 * window is closed
	 */
	public static void init() {
		// Write to log that init is beginning
		Log.write("Running init sequence for progress window", Log.DEBUG);

		// Instantiate window elements
		ProgressWindow.windowFrame = new JFrame(Config.PROGRAM_NAME__TITLE_BAR);
		ProgressWindow.progressTitleLabel = new JLabel("Progress");
		ProgressWindow.imageNumberLabel = new JLabel("Scanning image "
				+ ProgressWindow.imageNumberProcessing + " of "
				+ ProgressWindow.totalNumberOfImages);

	}
}
