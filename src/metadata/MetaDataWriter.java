package metadata;

import image.ImageOps;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;

import logging.Log;

import system.Config;
import system.Ops;

/**
 * One day this class will see some love
 * 
 * @author twtduck
 * 
 */
public abstract class MetaDataWriter {
	private static PrintWriter metaDataWriter;

	public static void init() {
		MetaDataWriter.init("metadata.csv");
	}

	public static void init(String fileName) {
		File metaDataFile = new File(Config.location, fileName);
		try {
			MetaDataWriter.metaDataWriter = new PrintWriter(metaDataFile);
		} catch (FileNotFoundException e) {
			Log.write(
					"There was a problem opening the metadata file for writing.",
					Log.ERROR);
		}
		metaDataWriter.println("\"Case number\",\"Image value average\",\"Standard deviation\"");
	}

	public static void writeData(ArrayList<BufferedImage> monoImages, int caseNumber) {
		double[] imageValues = new double[monoImages.size()];
		for(int i = 0; i < monoImages.size(); i++) {
			double imageValue = ImageOps.getInvertedImageValue(monoImages.get(i));
			imageValues[i] = imageValue;
		}
		
		double imageAverageValue = Ops.doubleArraySum(imageValues) / imageValues.length;
		double percentDeviation = Ops.doubleArrayStandardDeviation(imageValues, imageAverageValue);
		metaDataWriter.println(caseNumber + "," + imageAverageValue + "," + percentDeviation);
	}
	
	/**
	 * Close the file, and actually write whatever is left in the buffer to the
	 * file.
	 */
	public static void close() {
		Log.write("Closing metadata writer", Log.STANDARD);
		MetaDataWriter.metaDataWriter.close();
	}
	
	
}
