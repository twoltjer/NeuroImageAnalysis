package threads;

import java.io.File;
import java.util.ArrayList;

import gui.ProgressWindow;
import image.ImageSet;
import logging.Log;
import metadata.MetaDataWriter;
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
	public static int totalSets = 0;
	public static int setNumber = 0;
	
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

		// get an array of File objects and create an arraylist for just the image files
		File[] dirFiles = Ops.listFilesRecursively(scanDir);
		ArrayList<File> imageFiles = new ArrayList<File>();
		
		// Discover which files are either png or jpg image files and add them to imageFiles
		// I'll have to rewrite this if any of the files I scan have "png" or "jpg" as part 
		// of their titles, but for now it shouldn't be a problem
		//TODO: Have the code check if these are the actual extensions.
		for (File f : dirFiles) {
			if ((f.getName().toLowerCase().indexOf("png") >= 0)
					|| (f.getName().toLowerCase().indexOf("jpg") >= 0)) {
				imageFiles.add(f);
			}
		}
		
		// Create an ArrayList of image sets, and add the image files to those sets, but 
		// don't convert them to BufferedImage objects until the sets themselves are processing.
		ArrayList<ImageSet> groupedFiles = new ArrayList<ImageSet>();
		for (File image : imageFiles) {
			String caseName = Ops.getCaseNameFromFilename(image.getName());
			boolean foundMatchingCase = false;
			for (ImageSet i : groupedFiles) {
				if (i.caseName.equals(caseName)) {
					foundMatchingCase = true;
					i.addImageToSet(image);
				}
			}
			if (!foundMatchingCase) {
				Log.write("Creating new set for image " + image.getName(),
						Log.STANDARD);
				ImageSet newSet = new ImageSet(caseName);
				newSet.addImageToSet(image);
				groupedFiles.add(newSet);
			}
		}
		
		// Now that the images have been grouped by case number into ImageSet objects, they
		// can be scanned for MetaData. 
		MetaDataWriter.init();
		ImageProcessingThreadA.totalSets = groupedFiles.size();
		ProgThread prog = new ProgThread("Progress window thread");
		prog.start();
		
		for (int i = 1; i <= totalSets; i++) {
			ProgressWindow.setNumber = i;
			ProgressWindow.updateBars();
			ImageSet set = groupedFiles.get(i - 1);
			Log.write("Processing case " + i + " of " + groupedFiles.size()
					+ ": case " + set.caseName, Log.STANDARD);
			set.calculateImageData();
			Log.write("Writing new image data", Log.STANDARD);
			set.writeMonochromeImages(scanDir);
		}

		// We're done processing!
		MetaDataWriter.close();
		ProgressWindow.hide();
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
