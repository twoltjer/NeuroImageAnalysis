package gui;

import global.RuntimeConfig;
import processing.BufferedImageContainer;

public class BufferRunner extends Thread {
	public BufferedImageContainer bic;
	@Override
	public void run() {
		if(!bic.isBuffered) {
			bic.bufferImage();
		}
		RuntimeConfig.bufferedImages.add(bic);
		GUIThread.updateBufferProgBar();
	}
}