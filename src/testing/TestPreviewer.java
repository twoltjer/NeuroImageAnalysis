package testing;

import java.io.File;
import java.util.ArrayList;

import global.Config;
import global.RuntimeConfig;
import gui.GUIThread;
import processing.BufferedImageContainer;

public class TestPreviewer {

	public static final String IMAGE_DIRECTORY = "/home/thomas/code/eclipse-workspace/NIA/test_images2";
	
	/**
	 * Simply a test function to start the program from. Runs the test() method.
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		MemBarMaker.main(null);
		GUIThread testThread = new GUIThread();
		RuntimeConfig.imageDir = new File(IMAGE_DIRECTORY); // Uses hard coded image directory
		RuntimeConfig.imageDirChosen = true;
		File[] dirContents = RuntimeConfig.imageDir.listFiles();
		for(File f : dirContents) {
			RuntimeConfig.imageFiles.add(f);
		}
		testThread.startThread(GUIThread.LAUNCH_IMAGE_PREVIEWER);
	}
}
