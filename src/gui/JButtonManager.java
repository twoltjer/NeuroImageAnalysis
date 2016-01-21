package gui;

import javax.swing.JButton;

/**
 * About the stupidest class I've ever written. Really just holds the objects
 * for buttons so that they can be accessed from all over.
 * 
 * @author twtduck
 * 
 */
public abstract class JButtonManager {
	/**
	 * Change directory button for the scan window
	 */
	public static JButton SCAN_WINDOW__CHANGE_DIRECTORY_BUTTON;
	/**
	 * Scan button for the scan window
	 */
	public static JButton SCAN_WINDOW__SCAN_BUTTON;
	/**
	 * Cancel button for the progress window (which at the time of writing this
	 * is pretty far off. I don't know why I have this here already but I'm
	 * going to need it eventually)
	 */
	public static JButton PROGRESS_WINDOW__CANCEL_BUTTON;

}