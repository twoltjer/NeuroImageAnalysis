package global;

import java.awt.Dimension;

/**
 * General configuration file for the program, which only contains constants. 
 * Bye bye, magic numbers!
 * @author Thomas Woltjer
 */
public class Config {
	// ==========================================================================
	// |                                BOOLEANS                                |
	// ==========================================================================

	/**
	 * Enables debugging output to <pre>System.out</pre>. Releases should have this set to false
	 */
	public final static boolean DEBUG_OUTPUT_ENABLED = true;
	
	// ==========================================================================
	// |                                INTEGERS                                |
	// ==========================================================================
	
	/**
	 * The size of the border, which is used when making insets for GUI objects
	 */
	public static final int GUI_BORDER_WIDTH = 20;
	/**
	 * The amount of internal padding (horizontal, in pixels)
	 */
	public static final int GUI_INTERNAL_PADDING_X = 12;
	/**
	 * The amount of internal padding (vertical, in pixels)
	 */
	public static final int GUI_INTERNAL_PADDING_Y = 8;
	/**
	 * The large threshold change amount
	 */
	public static final int THRESH_CHANGE_LG_AMOUNT = 5;
	/**
	 * The small threshold change amount
	 */
	public static final int THRESH_CHANGE_SM_AMOUNT = 1;
	/**
	 * Default threshold value
	 */
	public static final int THRESH_DEFAULT = 100;
	
	// ==========================================================================
	// |                                STRINGS                                 |
	// ==========================================================================

	/**
	 * The program's name, to be displayed as the titles of the windows
	 */
	public final static String PROGRAM_NAME = "NIA";
	/**
	 * The initial text to display on the image directory chooser button
	 */
	public static final String CHOOSER_HUB_IMAGE_DIR_CHOOSER_BUTTON_TEXT = "Choose an image directory";
	/**
	 * The initial text to display on the output file directory chooser button
	 */
	public static final String CHOOSER_HUB_OUTPUT_FILE_DIR_CHOOSER_BUTTON_TEXT = "Choose an ouput file directory";
	/**
	 * Label text for the output file name text field
	 */
	public static final String CHOOSER_HUB_OUTPUT_FILE_NAME_TEXTAREA_LABEL_TEXT = "File name:";
	/**
	 * Default output file name
	 */
	public static final String CHOOSER_HUB_OUTPUT_FILE_NAME_TEXTAREA_TEXT = "nia-results.csv";
	/**
	 * Chooser hub scan button text
	 */
	public static final String CHOOSER_HUB_SCAN_BUTTON_TEXT = "Scan";
	/**
	 * Text for analyze button in the image previewer
	 */
	public static final String PREVIEWER_ANALYZE_BUTTON_TEXT = "Analyze";
	/**
	 * Text for cancel button in the image previewer
	 */
	public static final String PREVIEWER_CANCEL_BUTTON_TEXT = "Cancel";
	/**
	 * Prefix for button labels for reducing threshold amount
	 */
	public static final String PREVIEWER_DEC_PREFIX = "-";
	/**
	 * Prefix for button labels for increasing threshold amount
	 */
	public static final String PREVIEWER_INC_PREFIX = "+";
	
	public static final String PREVIEWER_NEXT_BUTTON_TEXT = "Next Image";
	
	public static final String PREVIEWER_PREV_BUTTON_TEXT = "Previous Image";
	
	public static final String PREVIEWER_THRESH_LABEL_TOP_TEXT = "Thresh:";
	
	// ==========================================================================
	// |                                 ARRAYS                                 |
	// ==========================================================================

	public static final String[] PREVIEWER_DM_NAMES = {"Color", "Grayscale", "Monochrome"};
	
	// ==========================================================================
	// |                               DIMENSIONS                               |
	// ==========================================================================

	/**
	 * Size of the buttons on the chooser hub
	 */
	public static final Dimension CHOOSER_HUB_BUTTON_SIZE = new Dimension(400,70);
	/**
	 * Size of large buttons in the previewer
	 */
	public static final Dimension PREVIEWER_LARGE_BUTTON_SIZE = new Dimension(200,70);
	/**
	 * Size of small buttons in the previewer
	 */
	public static final Dimension PREVIEWER_SMALL_BUTTON_SIZE = new Dimension(80,70);

	public static final Dimension PREVIEWER_PROG_BAR_SIZE = new Dimension(460, 78);
}
