package testing;

import java.io.File;
import java.util.ArrayList;

import global.Config;
import global.DebugMessenger;
import global.RuntimeConfig;
import gui.GUIObjects;
import gui.GUIThread;
import processing.BufferedImageContainer;

public class TestPreviewer {

	public static final String IMAGE_DIRECTORY = "/home/thomas/code/eclipse-workspace/NIA/test_images2";

	/**
	 * Simply a test function to start the program from. Runs the test() method.
	 * 
	 * @param args
	 *            empty argument array
	 * @throws InterruptedException
	 *             if there is something wrong with the thread sleeping later on
	 *             while it waits for the cancel button to be created.
	 */
	public static void main(String[] args) throws InterruptedException {
		test();
	}

	public static void test() throws InterruptedException {
		MemBarMaker.main(null);
		GUIThread testThread = new GUIThread();
		RuntimeConfig.imageDir = new File(IMAGE_DIRECTORY); // Uses hard coded
															// image directory
		RuntimeConfig.imageDirChosen = true;
		File[] dirContents = RuntimeConfig.imageDir.listFiles();
		for (File f : dirContents) {
			RuntimeConfig.imageFiles.add(f);
		}
		testThread.startThread(GUIThread.LAUNCH_IMAGE_PREVIEWER);
		// Can't go back to anything. So deactivate this button that is supposed
		// to take the user back to the chooser hub.
		while (true) {
			try {
				GUIObjects.PreviewerObjects.cancelButton.setEnabled(false);
				if(GUIObjects.PreviewerObjects.cancelButton.isEnabled() == false) {
					break;
				}
			} catch (NullPointerException e) {
				DebugMessenger.out("Cancel button not ready, waiting to cancel.");
				Thread.sleep(Config.THREAD_LOOP_WAIT_TIME_MILLIS);
			}
		}
	}
}
