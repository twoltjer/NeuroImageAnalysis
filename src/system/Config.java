package system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * This class contains the configuration for the build of the program. As of now
 * this includes:
 * 
 * - The program's name - The default separator characters - The default ignored
 * characters
 * 
 * @author Thomas Woltjer
 *
 */
public abstract class Config {
	// Set basic program preferences
	public static final String PROGRAM_NAME__TITLE_BAR = "Ducky's Image Analyzer";
	public static final String MONOCHROME_IMAGE_FILENAME_PREFIX = "mono";
	public static final double MONOCHROME_THRESHOLD_ADJUSTMENT_CONSTANT = 0.8;
	
	// Scan window preferences
	public static final String SCAN_WINDOW__DEFAULT_LOCATION_LABEL_TEXT = "No directory selected";
	public static final String SCAN_WINDOW__SCAN_BUTTON_TEXT = "Begin Scan";
	public static final String SCAN_WINDOW__CHANGE_DIRECTORY_BUTTON_TEXT = "Change Directory";
	
	public static boolean locationSet = false;
	public static File location;
	public static ArrayList<String> separatorChars = new ArrayList<String>();
	public static ArrayList<String> ignoredChars = new ArrayList<String>();

	// Image processing multithreading communication variables
	public static boolean isProcessingImages = false;
	public static BufferedImage imageBeingWritten = null;
	
	public static void setLocation(File f) {
		location = f;
		locationSet = true;
	}

	/**
	 * Sets the separatorChars and ignoredChars lists with a preset. Default
	 * separators: "-", ".", " ", "_" Default ignored: "(", ")"
	 */
	public static void setDefaultLists() {
		final String[] DEFAULT_SEPARATORS = { "-", ".", " ", "_" };
		final String[] DEFAULT_IGNORED = { "(", ")" };

		for (String s : DEFAULT_SEPARATORS) {
			separatorChars.add(s);
		}

		for (String s : DEFAULT_IGNORED) {
			ignoredChars.add(s);
		}
	}
}
