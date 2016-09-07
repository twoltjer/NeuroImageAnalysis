package gui.guiobjectsets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Object set class for the chooser hub. All objects used for creating the GUI of the chooser hub are stored here.
 * @author Thomas Woltjer
 */
public class PreviewerObjectSet {
	/**
	 * The frame for the image previewer window
	 */
	public JFrame previewFrame;
	/**
	 * The panel in which the left buttons are held.
	 * These buttons are the following:
	 * <ul><b>
	 * <li>GUIObjects.PreviewerObjects.prevImgButton</li>
	 * <li>GUIObjects.PreviewerObjects.prevDMButton</li>
	 * </b></ul>
	 */
	public JPanel leftButtonPanel;
	/**
	 * The panel that is displaying the currently viewed image
	 */
	public JPanel imagePanel;
	/**
	 * The panel in which the right buttons are held.
	 * These buttons are the following:
	 * <ul><b>
	 * <li>GUIObjects.PreviewerObjects.nextImgButton</li>
	 * <li>GUIObjects.PreviewerObjects.nextDMButton</li>
	 * </b></ul>
	 */
	public JPanel rightButtonPanel;
	/**
	 * The center button holds the increment and decrement buttons, as well as the threshold display panel
	 */
	public JPanel centerButtonPanel;
	/**
	 * The threshold display panel; consists of two JLabel objects
	 */
	public JPanel threshDisplayPanel;
	/**
	 * Displays the previous image when clicked
	 */
	public JButton prevImgButton;
	/**
	 * Displays the previous display mode when clicked. The text for this button
	 * and the triggered display mode change depending on the current display
	 * mode.
	 */
	public JButton prevDMButton;
	/**
	 * Displays the next image when clicked
	 */
	public JButton nextImgButton;
	/**
	 * Displays the next display mode when clicked. The text for this button
	 * and the triggered display mode change depending on the current display
	 * mode.
	 */
	public JButton nextDMButton;
	/**
	 * Cancels the preview window
	 */
	public JButton cancelButton;
	/**
	 * Panel to contain the cancel button, and give it the correct size and all	
	 */
	public JButton decLgButton;
	/**
	 * The small decrement button. Decrements a small amount from the threshold when clicked.
	 */
	public JButton decSmButton;
	/**
	 * The small increment button. Increments a small amount from the threshold when clicked.
	 */
	public JButton incSmButton;
	/**
	 * The large increment button. Increments a large amount from the threshold when clicked.
	 */
	public JButton incLgButton;
	/**
	 * Analyze button launches analysis, using the settings from the current view.
	 */
	public JButton analyzeButton;
	/**
	 * Threshold label top line
	 */
	public JLabel threshTopLabel;
	/**
	 * Threshold label bottom line
	 */
	public JLabel threshBotLabel;
	/**
	 * Status label reflects the current status of the program.
	 * As of now, the possible statuses are:
	 * <ul>
	 * <li>Ready</li>
	 * <li>Buffering..</li>
	 * </ul>
	 */
	public JLabel statusLabel;
	/**
	 * Progress bar that shows the progress of buffering images in the background. 
	 * If buffering is completed, the progress bar is empty and the status label reads "Ready."
	 */
	public JProgressBar bufferProgress;
}
