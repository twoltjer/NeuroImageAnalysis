package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import logging.Log;
import system.Config;
import system.Ops;

/**
 * The scan window allows the user to select a location and executes a scan of
 * that directory. This class is for creating and managing the scan window.
 * 
 * @author Thomas Woltjer
 * 
 */
public abstract class ScanWindow {
	// Window status
	private static boolean hasBeenInitialized = false;
	private static boolean isDisplaying;

	// Window framework
	private static JFrame windowFrame;
	private static Container windowContentPane;

	// Window elements
	private static JLabel SCAN_WINDOW__CURRENT_DIRECTORY_STATIC_LABEL;
	private static JLabel SCAN_WINDOW__CURRENT_DIRECTORY_DYNAMIC_LABEL;

	// Buttons
	public static JButton changeDirectoryButton;
	public static JButton scanButton;

	/**
	 * The init method for the ScanWindow class.
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
		Log.write("Running init sequence for scan window", Log.DEBUG);

		// Instantiate window elements
		ScanWindow.SCAN_WINDOW__CURRENT_DIRECTORY_STATIC_LABEL = new JLabel(
				"Currently in:");
		ScanWindow.SCAN_WINDOW__CURRENT_DIRECTORY_DYNAMIC_LABEL = new JLabel(
				Config.SCAN_WINDOW__DEFAULT_LOCATION_LABEL_TEXT);
		ScanWindow.scanButton = new JButton(
				Config.SCAN_WINDOW__SCAN_BUTTON_TEXT);
		ScanWindow.changeDirectoryButton = new JButton(
				Config.SCAN_WINDOW__CHANGE_DIRECTORY_BUTTON_TEXT);
		if (Config.locationSet)
			ScanWindow.SCAN_WINDOW__CURRENT_DIRECTORY_DYNAMIC_LABEL
					.setText(Config.location.toString());
		// Create and configure window for adding elements
		ScanWindow.windowFrame = new JFrame(Config.PROGRAM_NAME__TITLE_BAR);
		ScanWindow.windowContentPane = windowFrame.getContentPane();
		ScanWindow.windowContentPane.setLayout(new GridBagLayout());
		GridBagConstraints windowConstraints = new GridBagConstraints();

		// Make the program exit in a safe way when the window is closed
		ScanWindow.windowFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Log.write("Running window close procedures", Log.DEBUG);
				Ops.exit();
			}
		});

		// Add elements to window
		int INSET_TOP = 5;
		int INSET_LEFT = 5;
		int INSET_BOTTOM = 0;
		int INSET_RIGHT = 5;
		windowConstraints.insets = new Insets(INSET_TOP, INSET_LEFT,
				INSET_BOTTOM, INSET_RIGHT);

		windowConstraints.gridx = 0;
		windowConstraints.gridy = 0;
		windowConstraints.gridwidth = 1;
		windowConstraints.gridheight = 1;
		ScanWindow.windowContentPane.add(
				SCAN_WINDOW__CURRENT_DIRECTORY_STATIC_LABEL, windowConstraints);

		windowConstraints.gridx = 0;
		windowConstraints.gridy = 1;
		windowConstraints.gridwidth = 1;
		windowConstraints.gridheight = 1;
		INSET_BOTTOM = 5;
		INSET_TOP = 0;
		windowConstraints.insets = new Insets(INSET_TOP, INSET_LEFT,
				INSET_BOTTOM, INSET_RIGHT);
		ScanWindow.windowContentPane
				.add(SCAN_WINDOW__CURRENT_DIRECTORY_DYNAMIC_LABEL,
						windowConstraints);

		windowConstraints.gridx = 1;
		windowConstraints.gridy = 0;
		windowConstraints.gridwidth = 1;
		windowConstraints.gridheight = 2;
		INSET_TOP = 5;
		windowConstraints.insets = new Insets(INSET_TOP, INSET_LEFT,
				INSET_BOTTOM, INSET_RIGHT);
		ScanWindow.windowContentPane.add(
				ScanWindow.changeDirectoryButton,
				windowConstraints);

		windowConstraints.gridx = 0;
		windowConstraints.gridy = 3;
		windowConstraints.gridwidth = 2;
		windowConstraints.gridheight = 1;
		ScanWindow.windowContentPane.add(
				ScanWindow.scanButton, windowConstraints);

		// Add action listeners to the buttons
		ScanWindow.changeDirectoryButton
				.addActionListener(new JButtonClick());
		ScanWindow.scanButton
				.addActionListener(new JButtonClick());

		// Now is a good time to make it invisible if the location hasn't been set
		ScanWindow.scanButton.setVisible(Config.locationSet);

		// Change the display location of the scan window

		// Set size
		
		// Set the default as not displaying
		ScanWindow.isDisplaying = false;

		// Set internal variable to allow other methods to run
		ScanWindow.hasBeenInitialized = true;

		// Write to log that init has completed
		Log.write("Init sequence for scan window completed", Log.DEBUG);
	}

	/**
	 * Displays the scan window
	 */
	public static void show() {
		if (ScanWindow.hasBeenInitialized) {
			if (!ScanWindow.isDisplaying) {
				ScanWindow.windowFrame.pack();
				ScanWindow.windowFrame.setLocation(new Point(500,0));
				ScanWindow.windowFrame.setSize(new Dimension(500,200));
				ScanWindow.windowFrame.setVisible(true);
				ScanWindow.isDisplaying = true;
				Log.write("Scan window is being displayed", Log.DEBUG);
			} else {
				Log.write(
						"There was a call to show the scan window, but it is already showing!",
						Log.ERROR);
			}
		} else {
			Log.write(
					"Cannot display the scan window if it has not yet been initialized",
					Log.ERROR);
		}

	}

	/**
	 * Hides the scan window
	 */
	public static void hide() {
		if (ScanWindow.hasBeenInitialized) {
			if (ScanWindow.isDisplaying) {
				ScanWindow.windowFrame.setVisible(false);
				ScanWindow.isDisplaying = false;
				Log.write("Scan window is no longer being displayed", Log.DEBUG);
			} else {
				Log.write(
						"There was a call to hide the scan window, but it is already hidden!",
						Log.ERROR);
			}
		} else {
			Log.write(
					"Cannot hide the scan window if it has not yet been initialized",
					Log.ERROR);
		}
	}

	/**
	 * Check whether the program is ready to continue or not. Queries the Config
	 * class, which holds global variables to do this.
	 * 
	 * @return if it is okay to continue to the actual scan
	 */
	public static boolean canContinue() {
		if (ScanWindow.hasBeenInitialized) {
			return Config.locationSet;
		} else {
			Log.write(
					"Cannot query the scan window if it has not yet been initialized",
					Log.ERROR);
			return false;
		}
	}

	/**
	 * Refreshes the scan window. This should run whenever any elements in the
	 * scan window need to be updated.
	 */
	public static void refresh() {
		if (ScanWindow.hasBeenInitialized) {
			Log.write("Refreshing scan window", Log.DEBUG);
			if (ScanWindow.isDisplaying) {
				ScanWindow.hide();
				ScanWindow.update();
				ScanWindow.show();
			}
		} else {
			Log.write(
					"Cannot refresh the scan window if it has not yet been initialized",
					Log.ERROR);
		}
	}

	/**
	 * An internal function to update the elements in the scan window
	 */
	private static void update() {
		Log.write("Updating scan window elements", Log.DEBUG);
		ScanWindow.SCAN_WINDOW__CURRENT_DIRECTORY_DYNAMIC_LABEL
				.setText(Config.location.getName());
		ScanWindow.scanButton.setVisible(Config.locationSet);
	}

	/**
	 * Tell the world if this window is open. I wonder if this method is even
	 * necessary.
	 * 
	 * @return whether or not this window is open
	 */
	public static boolean isOpen() {
		return windowFrame.isVisible();
	}

}
