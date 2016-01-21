package threads;

import java.io.File;
import java.util.ArrayList;

import image.ImageSet;
import logging.Log;
import system.Config;
import system.Ops;

/**
 * 
 * This is where all the image processing happens. DEFINITELY don't want this as
 * part of the main thread, because then the rest of the program can't continue
 * until it's finished processing all the images.
 * 
 * @author twtduck
 * 
 */
public class ImageProcessingThreadA implements Runnable {
	private String name;
	private Thread thread;

	/**
	 * Classic constructor
	 * 
	 * @param name
	 *            name of the thread. Hint: Use something like "debug thread"
	 */
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
		File[] dirFiles = scanDir.listFiles();
		ArrayList<File> imageFiles = new ArrayList<File>();
		// group by file names
		ArrayList<ImageSet> groupedFiles = new ArrayList<ImageSet>();
		for (File f : dirFiles) {
			if ((f.getName().toLowerCase().indexOf("png") >= 0)
					|| (f.getName().toLowerCase().indexOf("jpg") >= 0)) {
				if (f.exists())
					imageFiles.add(f);
			}
		}

		for (File image : imageFiles) {
			int caseNumber = Ops.getCaseNumberFromFilename(image.getName());
			boolean foundMatchingCase = false;
			for (ImageSet i : groupedFiles) {
				if (i.caseNumber == caseNumber) {
					foundMatchingCase = true;
					i.addImageToSet(image);
				}
			}
			if (!foundMatchingCase) {
				Log.write("Creating new set for image " + image.getName(),
						Log.STANDARD);
				ImageSet newSet = new ImageSet(caseNumber);
				newSet.addImageToSet(image);
				groupedFiles.add(newSet);
			}
		}
		System.out.println("Number of groups: " + groupedFiles.size());
		for (int i = 1; i <= groupedFiles.size(); i++) {
			ImageSet set = groupedFiles.get(i - 1);
			Log.write("Processing case " + i + " of " + groupedFiles.size()
					+ ": case number " + set.caseNumber, Log.STANDARD);
			set.calculateImageData();
			Log.write("Writing new image data", Log.STANDARD);
			set.writeMonochromeImages(scanDir);
		}

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
