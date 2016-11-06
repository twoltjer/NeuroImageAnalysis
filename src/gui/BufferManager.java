// Begin gui/BufferManager.java

package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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

	class BufferRunner implements Runnable {

		@Override
		public void run() {
			// TODO This must also be written

		}

	}

	public static void buffer() {
		GUIThread disableButtonThread = new GUIThread();
		disableButtonThread.startThread(GUIThread.DISABLE_PREVIEWER_BUTTONS);
		
		// create list of new images to buffer
		ArrayList<BufferedImageContainer> imagesToBuffer = new ArrayList<BufferedImageContainer>();
		BufferedImageContainer dispImage = RuntimeConfig.previewerDisplayImage;

		// Set images to buffer
		BUFFER_COLOR = true;
		BUFFER_GRAYSCALE = true;
		BUFFER_MONOCHROME = true;
		if(dispImage.DM == BufferedImageContainer.DM_MONOCHROME) {
			//TODO: Finish this to check for which increments stay in range (0-255)
		} else {
			BUFFER_LG_DEC = false;
			BUFFER_SM_DEC = false;
			BUFFER_LG_INC = false;
			BUFFER_SM_INC = false;
		}
		BUFFER_PREV = (dispImage.indexNumber > 0);
		BUFFER_NEXT = (dispImage.indexNumber < (RuntimeConfig.imageFiles.size() - 1));
		
		
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
	}

	/**
	 * Static method to run a quick initial buffer on the first image to
	 * display. More buffering can happen after the window is already up, but
	 * the user must not be kept waiting.
	 */
	public static void initialBuffer() {
		// This can't be multithreaded, as it must complete before continuing
		// loading other parts of the program.
		RuntimeConfig.previewerDisplayImage = new BufferedImageContainer(BufferedImageContainer.DM_COLOR,
				Config.THRESH_DEFAULT, 0 /* index */, true);
		RuntimeConfig.previewerDisplayImage.bufferImage();
		RuntimeConfig.bufferedImages.add(RuntimeConfig.previewerDisplayImage);
		/* 
		 * Used for memory testing
		 *  
		 *  
		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
		while(true) {
			try {
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
				imgs.add(ImageIO.read(new File("/home/thomas/code/eclipse-workspace/NIA/test_images/a.png")));
				DebugMessenger.out("Read image " + imgs.size() + " times");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} */
	}
}

// End gui/BufferManager.java
