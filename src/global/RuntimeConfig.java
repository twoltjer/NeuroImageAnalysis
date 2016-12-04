package global;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import gui.GUIObjects;
import processing.BufferedImageContainer;

/**
 * Class for storing runtime configurations
 * 
 * @author Thomas Woltjer
 */
public class RuntimeConfig {

	// ==========================================================================
	// |                               STATUS VARS                              |
	// ==========================================================================

	/**
	 * If the image directory has been chosen, this is set to true. Otherwise,
	 * it is always false.
	 */
	public static boolean imageDirChosen = false;
	/**
	 * If the output file directory has been chosen, this is set to true.
	 * Otherwise, it is always false.
	 */
	public static boolean outputFileDirChosen = false;
	/**
	 * Variable to hold the status of whether all the requirements for scanning
	 * the image directory are in place
	 */
	public static boolean readyToScanImageDir = false;
	/**
	 * Variable that stores whether the chooser hub exists. This is to make
	 * tests function properly, when skipping over creating a chooser hub.
	 */
	public static boolean chooserHubExists = false;
	/**
	 * Indicates across the program if the previewer is buffering or not
	 */
	public static boolean isBuffering = false;
	/**
	 * If the previewer is ready to display the next image, this is true.
	 */
	public static boolean isReadyToDisplay = true;


	// ==========================================================================
	// |                             USER SETTINGS                              |
	// ==========================================================================

	/**
	 * The image directory file object. Before being set by the user, this is
	 * null.
	 */
	public static File imageDir = null;
	/**
	 * The output file directory
	 */
	public static File outputFileDir = null;
	/**
	 * The output file File object, set when scanDirectory() is run.
	 */
	public static File outputFile;
	/**
	 * The image files in the scanned directory
	 */
	public static ArrayList<File> imageFiles = new ArrayList<File>();

	// ==========================================================================
	// |                             CHANGING RUNTIME                           |
	// ==========================================================================

	// (vars that change as the program runs)

	/**
	 * The Image directory button text, initially set by
	 * Config.IMAGE_DIR_CHOOSER_BUTTON_TEXT
	 */
	public static String imageDirButtonText = Config.CHOOSER_HUB_IMAGE_DIR_CHOOSER_BUTTON_TEXT;
	/**
	 * The output file directory button text, initally set by
	 * Config.OUTPUT_FILE_DIR_CHOOSER_BUTTON_TEXT
	 */
	public static String outputFileDirButtonText = Config.CHOOSER_HUB_OUTPUT_FILE_DIR_CHOOSER_BUTTON_TEXT;
	/**
	 * The current threshold. By default, it is set to
	 * <b>Config.THRESH_DEFAULT</b>
	 */
	public static int threshold;
	
	public static Color foreground = Color.BLACK;
	
	public static Color background = Color.WHITE;

	public static BufferedImageContainer previewerDisplayImage;

	public static ArrayList<BufferedImageContainer> bufferedImages = new ArrayList<BufferedImageContainer>();
	
	public static ArrayList<JButton> tempDisabledPreviewerButtons = new ArrayList<JButton>();

	// ==========================================================================
	// |                                 METHODS                                |
	// ==========================================================================

	/**
	 * Refreshes the following
	 * <ul>
	 * <li>Image Directory Chooser button text</li>
	 * <li>Output File Directory Chooser button text</li>
	 * </ul>
	 */
	public static void refreshChooserVars() {
		if (imageDirChosen) {
			DebugMessenger.out(
					"Image directory chosen, so setting image directory variable from file chooser, and refreshing the button to show the proper text.");
			imageDir = GUIObjects.ChooserObjects.imageDirChooser.getSelectedFile();
			imageDirButtonText = "<html>Directory chosen!<br>"
					+ buttonSetText(GUIObjects.ChooserObjects.imageDirChooserButton, imageDir.getAbsolutePath())
					+ "<br>Click to change</html>";
			GUIObjects.ChooserObjects.imageDirChooserButton.setText(RuntimeConfig.imageDirButtonText);
		}
		if (outputFileDirChosen) {
			DebugMessenger.out(
					"Output file directory chosen, so setting output file directory variable from file chooser, and refreshing the button to show the proper text.");
			outputFileDir = GUIObjects.ChooserObjects.outputFileDirChooser.getSelectedFile();
			outputFileDirButtonText = "<html>Directory chosen!<br>" + outputFileDir.getAbsolutePath()
					+ "<br>Click to change</html>";
			GUIObjects.ChooserObjects.outputFileDirChooserButton.setText(RuntimeConfig.outputFileDirButtonText);
		}
		readyToScanImageDir = imageDirChosen && outputFileDirChosen;
		GUIObjects.ChooserObjects.chooserHubScanButton.setEnabled(readyToScanImageDir);
	}

	/**
	 * If a line to put on a button is too long, this method will shorten it so
	 * that it fits on the button
	 * 
	 * @param button
	 *            The button that the text is displayed on. The width of the
	 *            button's preferred size is used to find the right length for
	 *            the string.
	 * @param line
	 *            The line to paste on to the button. This may or may not be cut
	 *            off.
	 * @return The string to show on the button, which may have been modified
	 */
	public static String buttonSetText(JButton button, String line) {
		// Calculate ideal line length
		if (button.getPreferredSize().width / line.length() < 10)
			try {
				return line.substring(0, button.getPreferredSize().width / 10) + "...";
			} catch (Exception e) {
				return line;
			}
		return line;
	}

	/**
	 * Finds the next DM number
	 * 
	 * @return the next DM number
	 */
	public static int getNextDMNumber() {
		return getNextDMNumber(RuntimeConfig.previewerDisplayImage.DM);
	}

	/**
	 * Finds the previous DM number
	 * 
	 * @return the previous DM number
	 */
	public static int getPrevDMNumber() {
		return getPrevDMNumber(RuntimeConfig.previewerDisplayImage.DM);
	}
	
	/**
	 * Finds the next DM number
	 * 
	 * @return the next DM number
	 */
	public static int getNextDMNumber(int inDM) {
		int nextDMNum = inDM + 1;
		if (!(nextDMNum - Config.PREVIEWER_DM_NAMES.length < 0)) {
			nextDMNum -= Config.PREVIEWER_DM_NAMES.length;
		}
		return nextDMNum;
	}

	/**
	 * Finds the previous DM number
	 * 
	 * @return the previous DM number
	 */
	public static int getPrevDMNumber(int inDM) {
		int prevDMNum = inDM - 1;
		if (prevDMNum < 0) {
			prevDMNum += Config.PREVIEWER_DM_NAMES.length;
		}
		return prevDMNum;
	}
}
