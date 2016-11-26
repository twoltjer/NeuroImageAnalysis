package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import global.Config;
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
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Previous image button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.indexNumber -= 1;
			for (BufferedImageContainer bic : RuntimeConfig.bufferedImages) {
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
		if (e.getSource().equals(GUIObjects.PreviewerObjects.prevDMButton)) {
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Prev DM button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.DM = RuntimeConfig.getPrevDMNumber();
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
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Next DM button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.DM = RuntimeConfig.getNextDMNumber();
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
		if (e.getSource().equals(GUIObjects.PreviewerObjects.cancelButton)) {
			DebugMessenger.out("Cancel button clicked");
			GUIThread cancelThread = new GUIThread();
			cancelThread.destroyPreviewer();
			cancelThread.startThread(GUIThread.CHOOSER_HUB);
		}
		if (e.getSource().equals(GUIObjects.PreviewerObjects.decLgButton)) {
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Large dec button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.thresh -= Config.THRESH_CHANGE_LG_AMOUNT;
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
		if (e.getSource().equals(GUIObjects.PreviewerObjects.decSmButton)) {
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Small dec button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.thresh -= Config.THRESH_CHANGE_SM_AMOUNT;
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
		if (e.getSource().equals(GUIObjects.PreviewerObjects.incSmButton)) {
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Small inc button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.thresh += Config.THRESH_CHANGE_SM_AMOUNT;
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
		if (e.getSource().equals(GUIObjects.PreviewerObjects.incLgButton)) {
			RuntimeConfig.isReadyToDisplay = false;
			DebugMessenger.out("Large inc button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.previewerDisplayImage.cloneUnbuffered();
			newDispImg.thresh += Config.THRESH_CHANGE_LG_AMOUNT;
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
		if (e.getSource().equals(GUIObjects.PreviewerObjects.analyzeButton)) {

		}

	}

}
