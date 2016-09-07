package gui;

import javax.swing.JFileChooser;

import global.DebugMessenger;
import global.RuntimeConfig;

/**
 * Runs a folder chooser dialog, using either chooseImageDirectory() or
 * chooseOutputFileDirectory()
 * 
 * @author Thomas Woltjer
 * 
 */
public class FileChooserDialogOpener implements Runnable {
	private boolean isImageDirChooser;
	private boolean isOutputFileDirChooser;

	/**
	 * Call this to run the image directory chooser
	 */
	public void chooseImageDirectory() {
		DebugMessenger.out("Starting image directory chooser dialog thread.");
		this.isImageDirChooser = true;
		this.isOutputFileDirChooser = false;
		run();
	}

	/**
	 * Call this to run the output file directory chooser
	 */
	public void chooseOutputFileDirectory() {
		DebugMessenger.out("Starting output file directory chooser dialog thread.");
		this.isImageDirChooser = false;
		this.isOutputFileDirChooser = true;
		run();
	}

	/**
	 * An internal method, which runs based on configurations set by
	 * chooserImageDirectory() and chooseOutputFileDirectory()
	 * <p>
	 * This should never be run from outside this file, and the only reason it's
	 * public is because the interface (java.lang.Runnable) declares it so.
	 */
	@Override
	public void run() {
		DebugMessenger.out("File chooser dialog opener thread started");
		if (this.isImageDirChooser) {
			// If directory has already been set, start from there
			// Otherwise, open a default file chooser
			DebugMessenger.out("Creating new dialog for image directory chooser");
			if (RuntimeConfig.imageDirChosen) {
				GUIObjects.ChooserObjects.imageDirChooser = new JFileChooser(RuntimeConfig.imageDir);
			} else {
				GUIObjects.ChooserObjects.imageDirChooser = new JFileChooser();
			}
			GUIObjects.ChooserObjects.imageDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			DebugMessenger.out("Displaying image directory chooser dialog");
			int imageFileChooserReturn = GUIObjects.ChooserObjects.imageDirChooser.showOpenDialog(null);
			if (imageFileChooserReturn == 0) {
				DebugMessenger.out("File chooser returned success. Setting image directory chosen to true and refreshing variables.");
				RuntimeConfig.imageDirChosen = true;
				RuntimeConfig.refreshVars();
			}

		}
		if (this.isOutputFileDirChooser) {
			// If directory has already been set, start from there
			// Otherwise, open a default file chooser
			DebugMessenger.out("Creating new dialog for output file directory chooser");
			if (RuntimeConfig.outputFileDirChosen) {
				GUIObjects.ChooserObjects.outputFileDirChooser = new JFileChooser(RuntimeConfig.outputFileDir);
			} else {
				GUIObjects.ChooserObjects.outputFileDirChooser = new JFileChooser();
			}
			GUIObjects.ChooserObjects.outputFileDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			DebugMessenger.out("Displaying image directory chooser dialog");
			int outputFileFileChooserReturn = GUIObjects.ChooserObjects.outputFileDirChooser.showOpenDialog(null);
			if (outputFileFileChooserReturn == 0) {
				DebugMessenger.out("File chooser returned success. Setting output file directory chosen to true and refreshing variables.");
				RuntimeConfig.outputFileDirChosen = true;
				RuntimeConfig.refreshVars();
			}
		}
	}
}
