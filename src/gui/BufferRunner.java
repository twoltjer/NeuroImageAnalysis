package gui;

import global.DebugMessenger;
import global.RuntimeConfig;
import processing.BufferedImageContainer;

public class BufferRunner extends Thread {
	public static int threads = 0;
	private String thread_id;
	public BufferedImageContainer bic;
	@Override
	public void run() {
		thread_id = Integer.toString(threads);
		threads += 1;
		DebugMessenger.out("Starting buffer thread " + thread_id);
		if(!bic.isBuffered) {
			bic.bufferImage();
		}
		RuntimeConfig.bufferedImages.add(bic);
		GUIThread.updateBufferProgBar();
		DebugMessenger.out("Buffer thread " + thread_id + " complete");
		DebugMessenger.out("(" + thread_id + ") Index = " + bic.indexNumber);
		DebugMessenger.out("(" + thread_id + ") DM = " + bic.DM);
		DebugMessenger.out("(" + thread_id + ") Thresh = " + bic.thresh);
		
		
	}
}