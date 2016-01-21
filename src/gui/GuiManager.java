package gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import logging.Log;
import system.Config;

/**
 * A GUI manager class that currently only runs the directory chooser, but could
 * eventually be useful for more
 * 
 * @author twtduck
 * 
 */
public abstract class GuiManager {
	private static JFileChooser dirChooser;

	/**
	 * A method to create a change directory box, that opens from the scan
	 * window
	 */
	public static void changeDirectory() {
		dirChooser = new JFileChooser();
		JFrame dirChooserFrame = new JFrame();
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int dirChosenRtn = dirChooser.showOpenDialog(dirChooserFrame);
		if (dirChosenRtn == JFileChooser.APPROVE_OPTION) {
			Log.write("Directory chosen approved", Log.DEBUG);
			Log.write("Selected file (actually a directory): "
					+ dirChooser.getSelectedFile().getAbsolutePath(), Log.DEBUG);
			Config.setLocation(dirChooser.getSelectedFile());
			ScanWindow.refresh();
		} else {
			Log.write("Directory chosen not approved", Log.DEBUG);
		}
	}

}
