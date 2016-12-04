package gui.guiobjectsets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Object set class for the chooser hub. All objects used for creating the GUI of the chooser hub are stored here.
 * @author Thomas Woltjer
 */
public class ChooserHubObjectSet {
	/**
	 * The frame for the chooser hub
	 */
	public JFrame chooserFrame;
	/**
	 * A button for opening the image directory chooser
	 */
	public JButton imageDirChooserButton;
	/**
	 * A button for opening the output file directory chooser
	 */
	public JButton outputFileDirChooserButton;
	/**
	 * A label for the file name input area
	 */
	public JLabel outputFileNameTextAreaLabel;
	/**
	 * The file name input area
	 */
	public JTextArea outputFileNameTextArea;
	/**
	 * The image directory chooser
	 */
	public JFileChooser imageDirChooser;
	/**
	 * The output file directory chooser
	 */
	public JFileChooser outputFileDirChooser;
	/**
	 * The chooser hub main panel, which resides in the <b><i>GUIObjects.ChooserObjects.chooserFrame</i></b>
	 */
	public JPanel chooserHubMainPanel;
	/**
	 * Scan button for chooser hub
	 */
	public JButton chooserHubScanButton;
}
