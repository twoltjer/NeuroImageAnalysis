// Begin gui/BufferManager.java

package gui;

import java.util.ArrayList;

import global.Config;
import global.DebugMessenger;
import global.RuntimeConfig;
import processing.BufferedImageContainer;

/**
 * This class buffers new images if necessary after the displaying image
 * changes. Be sure to have set a new RuntimeConfig.displayingImage before
 * running buffer(). Also, running buffer() will change the the
 * RuntimeConfig.bufferedImages list.
 * 
 * @author Thomas Woltjer
 */
public class BufferManager {
	static public boolean BUFFER_COLOR = false;
	static public boolean BUFFER_GRAYSCALE = false;
	static public boolean BUFFER_MONOCHROME = false;
	static public boolean BUFFER_LG_DEC = false;
	static public boolean BUFFER_SM_DEC = false;
	static public boolean BUFFER_SM_INC = false;
	static public boolean BUFFER_LG_INC = false;
	static public boolean BUFFER_PREV = false;
	static public boolean BUFFER_NEXT = false;



	public static void buffer() {
		DebugMessenger.out("Starting buffer process");
		RuntimeConfig.isBuffering = true;
		//GUIThread disableButtonThread = new GUIThread();
		//disableButtonThread.startThread(GUIThread.DISABLE_PREVIEWER_BUTTONS);
		

		BufferedImageContainer dispImage = RuntimeConfig.previewerDisplayImage;

		// Set images to buffer
		BUFFER_COLOR = true;
		BUFFER_GRAYSCALE = true;
		BUFFER_MONOCHROME = true;
		BUFFER_LG_DEC = dispImage.thresh >= Config.THRESH_CHANGE_LG_AMOUNT;
		BUFFER_SM_DEC = dispImage.thresh >= Config.THRESH_CHANGE_SM_AMOUNT;
		BUFFER_LG_INC = dispImage.thresh <= 100 - Config.THRESH_CHANGE_LG_AMOUNT;
		BUFFER_SM_INC = dispImage.thresh <= 100 - Config.THRESH_CHANGE_SM_AMOUNT;
		BUFFER_PREV = (dispImage.indexNumber > 0);
		BUFFER_NEXT = (dispImage.indexNumber < (RuntimeConfig.imageFiles.size() - 1));
		
		// create list of new images to buffer
		ArrayList<BufferedImageContainer> imagesToBuffer = new ArrayList<BufferedImageContainer>();
		if(BUFFER_COLOR) {
			imagesToBuffer.add(new BufferedImageContainer(BufferedImageContainer.DM_COLOR, dispImage.thresh, dispImage.indexNumber, true));
		}
		if(BUFFER_GRAYSCALE) {
			imagesToBuffer.add(new BufferedImageContainer(BufferedImageContainer.DM_GRAYSCALE, dispImage.thresh, dispImage.indexNumber, true));
		}
		if(BUFFER_MONOCHROME) {
			imagesToBuffer.add(new BufferedImageContainer(BufferedImageContainer.DM_MONOCHROME, dispImage.thresh, dispImage.indexNumber, true));
		}
		if(BUFFER_LG_DEC) {
			imagesToBuffer.add(new BufferedImageContainer(dispImage.DM, dispImage.thresh - Config.THRESH_CHANGE_LG_AMOUNT, dispImage.indexNumber, true));
		}
		if(BUFFER_SM_DEC) {
			imagesToBuffer.add(new BufferedImageContainer(dispImage.DM, dispImage.thresh - Config.THRESH_CHANGE_SM_AMOUNT, dispImage.indexNumber, true));
		}
		if(BUFFER_LG_INC) {
			imagesToBuffer.add(new BufferedImageContainer(dispImage.DM, dispImage.thresh + Config.THRESH_CHANGE_LG_AMOUNT, dispImage.indexNumber, true));
		}
		if(BUFFER_SM_INC) {
			imagesToBuffer.add(new BufferedImageContainer(dispImage.DM, dispImage.thresh + Config.THRESH_CHANGE_SM_AMOUNT, dispImage.indexNumber, true));
		}
		if(BUFFER_PREV) {
			imagesToBuffer.add(new BufferedImageContainer(dispImage.DM, dispImage.thresh, dispImage.indexNumber - 1, true));
		}
		if(BUFFER_NEXT) {
			imagesToBuffer.add(new BufferedImageContainer(dispImage.DM, dispImage.thresh, dispImage.indexNumber + 1, true));
		}
		
		/* TODO: Optimize buffering by keeping some images
		// Check already buffered images for matches. If they don't match, remove them
		ArrayList<Integer> indecesToRemove = new ArrayList<Integer>();
		for(int i = 0; i < RuntimeConfig.bufferedImages.size(); i++) {
			BufferedImageContainer testImg = RuntimeConfig.bufferedImages.get(i);
			boolean fitsTheBill = false;
			for(BufferedImageContainer compImg : imagesToBuffer) {
				if(testImg.equals(compImg)) {
					fitsTheBill = true;
					break;
				}
			}
			if(!fitsTheBill) {
				indecesToRemove.add(new Integer(i));
			}
		}
		for(int i = indecesToRemove.size() - 1; i >= 0; i -= 1) {
			int indexToRemove = indecesToRemove.get(i).intValue();
			BufferedImageContainer bic = RuntimeConfig.bufferedImages.remove(indexToRemove);
			bic.unbufferImage();
		}
		indecesToRemove.clear();
		*/
		// For now, just clear the list of buffered images
		DebugMessenger.out("Clearing previously buffered images");
		for(BufferedImageContainer bic : RuntimeConfig.bufferedImages) {
			bic.unbufferImage();
		}
		RuntimeConfig.bufferedImages.clear();
		DebugMessenger.out("Done clearing previously buffered images");
			
		GUIObjects.PreviewerObjects.bufferProgress.setMaximum(imagesToBuffer.size());
		for(int i = 0; i < imagesToBuffer.size(); i++) {
			BufferRunner br = new BufferRunner();
			br.bic = imagesToBuffer.get(i);
			br.start();
		}
		
	}

	/**
	 * Static method to run a quick initial buffer on the first image to
	 * display. More buffering can happen after the window is already up, but
	 * the user must not be kept waiting.
	 */
	public static void initialBuffer() {
		// This can't be threaded separately from the previewer, as it must 
		// complete before continuing loading other parts of the program.
		RuntimeConfig.previewerDisplayImage = new BufferedImageContainer(BufferedImageContainer.DM_COLOR,
				Config.THRESH_DEFAULT, 0 /* index */, true);
		RuntimeConfig.previewerDisplayImage.bufferImage();
		RuntimeConfig.bufferedImages.add(RuntimeConfig.previewerDisplayImage);
		/* 
		 * Used for memory testing
		 *  
		 *   
		ArrayList<java.awt.image.BufferedImage> imgs = new ArrayList<java.awt.image.BufferedImage>();
		while(true) {
			try {
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
				imgs.add(javax.imageio.ImageIO.read(new java.io.File("/home/thomas/code/eclipse-workspace/NIA/test_images/a.png")));
				DebugMessenger.out("Read image " + imgs.size() + " times");

			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		} */
	}
}

// End gui/BufferManager.java
