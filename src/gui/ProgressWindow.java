package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import logging.Log;
import logging.LogWindowManager;
import system.Config;
import threads.ImageProcessingThreadA;

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
	public static boolean isInitialized = false;
	public static int setPercentage = 0;
	public static int setNumber = 0;
	public static int numberOfSets;
	public static long usedSpace;
	public static long totalSpace;

	// Window framework
	private static JFrame windowFrame;
	private static Container windowContentPane;

	// Window elements
	private static JLabel progressTitleLabel;
	private static JLabel percentLabel;
	private static JLabel setNumberLabel;
	private static JLabel diskSpaceLabel;
	public static JProgressBar currentSetProgress;
	public static JProgressBar totalProgress;
	public static JProgressBar diskFillProgress;

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

		// Get init info
		ProgressWindow.numberOfSets = ImageProcessingThreadA.totalSets;

		// Instantiate window elements
		ProgressWindow.windowFrame = new JFrame(Config.PROGRAM_NAME__TITLE_BAR);
		ProgressWindow.progressTitleLabel = new JLabel("Progress");
		ProgressWindow.percentLabel = new JLabel("Scanning images in set: "
				+ (ProgressWindow.setPercentage / 2) + "% complete");
		ProgressWindow.setNumberLabel = new JLabel("Scanning set "
				+ ProgressWindow.setNumber + " of "
				+ ProgressWindow.numberOfSets);
		ProgressWindow.diskSpaceLabel = new JLabel("Disk space used:");

		currentSetProgress = new JProgressBar();
		totalProgress = new JProgressBar();
		diskFillProgress = new JProgressBar();

		// resize bars
		Dimension minProgBarSize = new Dimension(400,50);
		currentSetProgress.setMinimumSize(minProgBarSize);
		totalProgress.setMinimumSize(minProgBarSize);
		diskFillProgress.setMinimumSize(minProgBarSize);
		Dimension minLabelSize = new Dimension(590,50);
		percentLabel.setMinimumSize(minLabelSize);
		setNumberLabel.setMinimumSize(minLabelSize);
		diskSpaceLabel.setMinimumSize(minLabelSize);

		// Create window
		ProgressWindow.windowContentPane = windowFrame.getContentPane();
		ProgressWindow.windowContentPane.setLayout(new GridBagLayout());
		GridBagConstraints windowConstraints = new GridBagConstraints();

		// Set gridbag defaults
		int INSET_TOP = 15;
		int INSET_LEFT = 10;
		int INSET_BOTTOM = 15;
		int INSET_RIGHT = 10;
		windowConstraints.insets = new Insets(INSET_TOP, INSET_LEFT,
				INSET_BOTTOM, INSET_RIGHT);
		windowConstraints.weightx = 1;
		windowConstraints.weighty = 1;

		// Title Label
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 0;
		windowContentPane.add(progressTitleLabel, windowConstraints);

		// Inside set progress label
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 1;
		windowContentPane.add(percentLabel, windowConstraints);

		// Percentage progress bar
		windowConstraints.gridx = 1;
		windowConstraints.gridy = 1;
		windowContentPane.add(currentSetProgress, windowConstraints);

		// Set number label
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 2;
		windowContentPane.add(setNumberLabel, windowConstraints);

		// Total progress bar
		windowConstraints.gridx = 1;
		windowConstraints.gridy = 2;
		windowContentPane.add(totalProgress, windowConstraints);

		// Disk label
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 3;
		windowContentPane.add(diskSpaceLabel, windowConstraints);

		// Disk usage progress bar
		windowConstraints.gridx = 1;
		windowConstraints.gridy = 3;
		windowContentPane.add(diskFillProgress, windowConstraints);

		ProgressWindow.isInitialized = true;

		ProgressWindow.updateBars();
	}

	public static void show() {
		windowFrame.pack();
		windowFrame.setLocation(new Point(500,224));
		windowFrame.setSize(new Dimension(500, 200));
		windowFrame.setVisible(true);
	}

	public static void hide() {
		windowFrame.setVisible(true);
	}

	public static void updateBars() {
		if (ProgressWindow.isInitialized) {
			System.out.println(windowFrame.getSize().getWidth());
			// currentSetProgress is reperesented through a percentage, but
			// there are two operations so the max is 300 (100 for first op, 200
			// for second because it takes longer)
			currentSetProgress.setMinimum(0);
			currentSetProgress.setMaximum(300);
			currentSetProgress.setValue(ProgressWindow.setPercentage);

			// totalProgress is only updated with the completion of each set.
			totalProgress.setMinimum(0);
			totalProgress.setMaximum(ProgressWindow.numberOfSets);
			totalProgress.setValue(ProgressWindow.setNumber);

			// diskFillProgress shows how much of the disk is used
			diskFillProgress.setMinimum(0);
			diskFillProgress
					.setMaximum((int) (Config.location.getTotalSpace() / 100000));
			diskFillProgress
					.setValue((int) ((Config.location.getTotalSpace() - Config.location
							.getFreeSpace()) / 100000));

			// Refresh labels
			int progress = (ProgressWindow.setPercentage / 3);
			if(progress > 100)
				progress = 100;
			percentLabel.setText("Scanning images in set: "
					+ progress + "% complete");
			setNumberLabel.setText("Scanning set " + ProgressWindow.setNumber
					+ " of " + ProgressWindow.numberOfSets);
		}
	}
}
