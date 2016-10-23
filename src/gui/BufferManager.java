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
	
	
	class BufferRunner implements Runnable {
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void buffer() {
		
	}
	
	public static void initialBuffer() {
		// This can't be multithreaded, as it must complete before continuing loading other parts of the program.
		RuntimeConfig.previewerDisplayImage = new BufferedImageContainer(BufferedImageContainer.DM_COLOR, Config.THRESH_DEFAULT, 0 /*index*/, true);
		RuntimeConfig.previewerDisplayImage.bufferImage();
	}
}
