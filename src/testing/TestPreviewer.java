package testing;

import java.io.File;
import java.util.ArrayList;

import global.Config;
import global.RuntimeConfig;
import gui.GUIThread;
import processing.BufferedImageContainer;

public class TestPreviewer {

	public static final String IMAGE_DIRECTORY = "/home/thomas/projects/NeuroImageAnalysis/test_images";
	
	/**
	 * Simply a test function to start the program from. Runs the test() method.
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		GUIThread testThread = new GUIThread();
		RuntimeConfig.imageDir = new File(IMAGE_DIRECTORY);
		RuntimeConfig.imageDirChosen = true;
		File[] dirContents = RuntimeConfig.imageDir.listFiles();
		for(File f : dirContents) {
			RuntimeConfig.imageFiles.add(f);
		}
		testThread.start(GUIThread.IMAGE_PREVIEWER);
	}
	
	
	public static ArrayList<Boolean> test_automated() {
		return null; //TODO: Write the automated tests for the preivewer
	}
}
