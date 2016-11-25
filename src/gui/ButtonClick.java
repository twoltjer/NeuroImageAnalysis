package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import global.DebugMessenger;
import global.RuntimeConfig;
import processing.BufferedImageContainer;
import processing.Preprocessing;

/**
 * A class that runs its actionPerformed method whenever a button is clicked
 * 
 * @author Thomas Woltjer
 */
public class ButtonClick implements ActionListener {

	/**
	 * Run when a button is clicked. This determines which button is clicked,
	 * and executes the correct code for said button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(GUIObjects.ChooserObjects.imageDirChooserButton)) {
			// Debug message, mainly for testing action listeners and program
			// timing
			DebugMessenger.out("Image directory chooser button clicked");
			FileChooserDialogOpener fcdo = new FileChooserDialogOpener();
			fcdo.chooseImageDirectory();
		}
		if (e.getSource().equals(GUIObjects.ChooserObjects.outputFileDirChooserButton)) {
			DebugMessenger.out("Output file directory chooser button clicked");
			FileChooserDialogOpener fcdo = new FileChooserDialogOpener();
			fcdo.chooseOutputFileDirectory();
		}
		if (e.getSource().equals(GUIObjects.ChooserObjects.chooserHubScanButton)) {
			DebugMessenger.out("Scan button clicked");
			DebugMessenger.out("Reading image directory for images");
			Preprocessing.readImageDirectory();
			DebugMessenger.out("Creating GUIThread for previewer");
			GUIThread gt = new GUIThread();
			gt.startThread(GUIThread.LAUNCH_IMAGE_PREVIEWER);

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.prevImgButton)) {
			DebugMessenger.out("Previous image button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.indexNumber -= 1;
			for (BufferedImageContainer bic : RuntimeConfig.bufferedImages) {
				if (bic.equals(newDispImg)) {
					newDispImg = bic;
					break;
				}
			}
			RuntimeConfig.previewerDisplayImage = newDispImg;
			BufferManager.buffer();
		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.prevDMButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.nextImgButton)) {
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Next image button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.indexNumber += 1;
			for (BufferedImageContainer bic : RuntimeConfig.bufferedImages) {
				DebugMessenger.out("Comparing " + bic.toString() + " and " + newDispImg.toString());
				if (bic.quickEquals(newDispImg)) {
					newDispImg = bic;
					DebugMessenger.out("Found new display image");
					DebugMessenger.out("New display image is buffered: " + newDispImg.isBuffered);
					break;
				}
			}
			
			RuntimeConfig.previewerDisplayImage = newDispImg;
			DebugMessenger.out("Set new display image");
			DebugMessenger.out("Set display image is buffered: " + RuntimeConfig.previewerDisplayImage.isBuffered);
			GUIThread.updatePreviewerDispImage();
			BufferManager.buffer();
		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.nextDMButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.cancelButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.decLgButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.decSmButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.incSmButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.incLgButton)) {

		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.analyzeButton)) {

		}

	}

}
