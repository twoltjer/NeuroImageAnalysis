package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import global.DebugMessenger;
import global.RuntimeConfig;
import processing.BufferedImageContainer;

/**
 * A class that runs its actionPerformed method whenever a button is clicked
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
			// Debug message, mainly for testing action listeners and program timing
			DebugMessenger.out("Image directory chooser button clicked");
			FileChooserDialogOpener fcdo = new FileChooserDialogOpener();
			fcdo.chooseImageDirectory();
		}
		if(e.getSource().equals(GUIObjects.ChooserObjects.outputFileDirChooserButton)) {
			DebugMessenger.out("Output file directory chooser button clicked");
			FileChooserDialogOpener fcdo = new FileChooserDialogOpener();
			fcdo.chooseOutputFileDirectory();
		}
		if(e.getSource().equals(GUIObjects.ChooserObjects.chooserHubScanButton)) {
			DebugMessenger.out("Scan button clicked");
			GUIThread gt = new GUIThread();
			gt.start(GUIThread.IMAGE_PREVIEWER);
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.prevImgButton)) {
			DebugMessenger.out("Previous image button clicked");
			BufferedImageContainer newDispImg = RuntimeConfig.displayingImage.cloneUnbuffered();
			newDispImg.indexNumber -= 1;
			newDispImg.setImageFile();
			for(BufferedImageContainer bic : RuntimeConfig.bufferedImages) {
				if(bic.equals(newDispImg)) {
					newDispImg = bic;
					break;
				}
			}
			
			
					
			// If image index is zero, disable this button. If not, enable it.
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.prevDMButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.nextImgButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.nextDMButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.cancelButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.decLgButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.decSmButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.incSmButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.incLgButton)) {
			
		}
		if(e.getSource().equals(GUIObjects.PreviewerObjects.analyzeButton)) {
			
		}
		
	}

}
