package gui;

import javax.swing.JProgressBar;

import gui.guiobjectsets.ChooserHubObjectSet;
import gui.guiobjectsets.PreviewerObjectSet;

/**
 * Class to contain GUI object sets. Having the GUI objects in different
 * classes/sets allows cleaner organization, as otherwise all the objects used
 * by any GUI in any part of the program would be in only one class.
 * 
 * @author Thomas Woltjer
 */
public abstract class GUIObjects {
	/**
	 * The instance of the ChooserHubObjectSet object that this program uses. 
	 */
	public static ChooserHubObjectSet ChooserObjects = new ChooserHubObjectSet();
	/**
	 * The instance of the PreviewerObjectSet object that this program uses. 
	 */
	public static PreviewerObjectSet PreviewerObjects = new PreviewerObjectSet();
	
	public static JProgressBar scanProgressBar = new JProgressBar();
}
