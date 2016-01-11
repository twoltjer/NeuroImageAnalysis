package Threads;

import java.io.File;

import image.ImageSet;
import logging.Log;
import system.Config;

public class ImageProcessingThreadA implements Runnable {
	private String name;
	private Thread thread;

	public ImageProcessingThreadA(String name) {
		this.name = name;
		logWrite("Creating thread " + name);
	}

	/**
	 * This runs as an internal method when Start() is called
	 */
	public void run() {
		logWrite("Running " + this.name);
		File scanDir = Config.location;

		// Just for testing purposes, create one set from the entire directory.
		// TODO: Change this later using the code at the bottom of the method
		File[] imgFiles = scanDir.listFiles();
		ImageSet set = new ImageSet();
		for (File f : imgFiles) {
			if(f.getName().indexOf("jpg") >= 0) {
				if(f.exists())
					set.addImageToSet(f);
			}
				
		}
		set.calculateImageData();
		set.writeMonochromeImages(scanDir);
		// group by file names
		// ArrayList<ImageSet> groupedFiles;

		logWrite("Thread " + this.name + " exiting.");
	}

	/**
	 * Use this to create a new thread. Then give it a ThreadTask object.
	 */
	public void start() {
		logWrite("Starting " + this.name);
		if (this.thread == null) {
			this.thread = new Thread(this, this.name);
			this.thread.start();
		}
	}

	/**
	 * Writes a debug message, to the log if it's initialized, or to the console
	 * if not
	 * 
	 * @param message
	 *            the message to write
	 */
	private void logWrite(String message) {
		if (Log.isInstantiated())
			Log.write(message, Log.DEBUG);
		else {
			System.out.println(message);
		}
	}
}
