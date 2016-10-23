// Begin gui/BufferManager.java

package gui;

import global.Config;
import global.RuntimeConfig;
import processing.BufferedImageContainer;

public class BufferManager {
	static public boolean BUFFER_COLOR = false;
	static public boolean BUFFER_GRAYSCALE = false;
	static public boolean BUFFER_MONOCHROME = false;
	static public boolean BUFFER_LG_DEC = false;
	static public boolean BUFFER_SM_DEC = false;
	static public boolean BUFFER_SM_INC = false;
	static public boolean BUFFER_LG_INC = true;

	/**
	 * This class buffers new images if necessary after the displaying image
	 * changes. Be sure to have set a new RuntimeConfig.displayingImage before
	 * running buffer(). Also, running buffer() will change the the
	 * RuntimeConfig.bufferedImages list.
	 * 
	 * @author Thomas Woltjer
	 */
	class BufferRunner implements Runnable {

		@Override
		public void run() {
			// TODO This must also be written

		}

	}

	public void buffer() {
		// TODO This must also be written
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
	}
}

// End gui/BufferManager.java
