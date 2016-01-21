package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.ProgressWindow;
import logging.Log;
import system.Config;

/**
 * This class holds data for one image set. That is, one subset of all the
 * images that belong to a particular case and have a particular stain.
 * 
 * @author twtduck
 * 
 */
public class ImageSet {
	private ArrayList<File> files;
	private ArrayList<BufferedImage> images;
	private double averageColorR;
	private double averageColorG;
	private double averageColorB;
	private double thresholdR;
	private double thresholdG;
	private double thresholdB;

	public int caseNumber;

	/**
	 * Create a new image set, using the case number. (TODO: Add stain support)
	 * @param caseNumber the case number
	 */
	public ImageSet(int caseNumber) {
		this.files = new ArrayList<File>();
		this.images = new ArrayList<BufferedImage>();
		this.caseNumber = caseNumber;
	}

	/**
	 * Add an image to the set of images
	 * 
	 * @param f
	 *            the image file to add
	 */
	public void addImageToSet(File f) {
		Log.write("Adding image to set: " + f.getName(), Log.CONSOLE);
		this.files.add(f);
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);

		} catch (IOException e) {
			Log.write("There was an error reading the image file", Log.ERROR);
		}
		this.images.add(img);
		Log.write("Added image to set: " + f.getName(), Log.CONSOLE);
	}

	/**
	 * Calculates the average color from the images, then the thresholds
	 */
	public void calculateImageData() {
		ProgressWindow.totalNumberOfImages = images.size();

		// displayImages();
		int totalExpected = 0;
		for (BufferedImage i : this.images) {
			totalExpected += i.getHeight() * i.getWidth();
		}
		int percentageInterval = totalExpected / 100;
		int nextPercentageInterval = 1;
		int percentDone = 0;
		int totalPixels = 0;
		long totalR = 0;
		long totalG = 0;
		long totalB = 0;
		for (BufferedImage img : images) {
			ProgressWindow.imageNumberReading++;
			int imgHeight = img.getHeight();
			int imgWidth = img.getWidth();
			for (int y = 0; y < imgHeight; y++) {
				for (int x = 0; x < imgWidth; x++) {
					int pixelColorAsInt = img.getRGB(x, y);
					Color pixelColor = new Color(pixelColorAsInt);
					/*
					 * Some variables that can be uncommented in case of
					 * debugging stuff int thisR = pixelColor.getRed(); int
					 * thisG = pixelColor.getGreen(); int thisB =
					 * pixelColor.getBlue();
					 */
					totalR += pixelColor.getRed();
					totalG += pixelColor.getGreen();
					totalB += pixelColor.getBlue();
					totalPixels++;
					if (totalPixels == nextPercentageInterval) {
						System.out.println("Completed " + percentDone + "%");
						percentDone++;
						nextPercentageInterval += percentageInterval;
					}
				}
			}
		}
		System.out.println("Done reading image");
		Log.write("Total red is " + totalR, Log.DEBUG);
		Log.write("Total green is " + totalG, Log.DEBUG);
		Log.write("Total blue is " + totalB, Log.DEBUG);
		averageColorR = (((double) totalR) / ((double) totalPixels));
		averageColorG = (((double) totalG) / ((double) totalPixels));
		averageColorB = (((double) totalB) / ((double) totalPixels));
		Log.write("Average red is " + averageColorR, Log.DEBUG);
		Log.write("Average green is " + averageColorG, Log.DEBUG);
		Log.write("Average blue is " + averageColorB, Log.DEBUG);
		thresholdR = averageColorR
				* Config.MONOCHROME_THRESHOLD_ADJUSTMENT_CONSTANT;
		thresholdG = averageColorG
				* Config.MONOCHROME_THRESHOLD_ADJUSTMENT_CONSTANT;
		thresholdB = averageColorB
				* Config.MONOCHROME_THRESHOLD_ADJUSTMENT_CONSTANT;
		Log.write("Threshold red is " + thresholdR, Log.DEBUG);
		Log.write("Threshold green is " + thresholdG, Log.DEBUG);
		Log.write("Threshold blue is " + thresholdB, Log.DEBUG);
	}

	/**
	 * Writes monochrome versions of the images
	 * 
	 * @param directory
	 *            the directory where the images will be written
	 */
	public void writeMonochromeImages(File directory) {
		int totalExpected = 0;
		for (BufferedImage i : this.images) {
			totalExpected += i.getHeight() * i.getWidth();
		}
		int percentageInterval = totalExpected / 10;
		int nextPercentageInterval = 1;
		int percentDone = 0;
		int totalPixels = 0;

		/*
		 * Config.isProcessingImages = true; Config.imageBeingWritten =
		 * images.get(0);
		 * 
		 * MRPIThread mrpi = new MRPIThread("MRPI"); mrpi.start();
		 */

		for (File originalImageFile : this.files) {
			BufferedImage originalImage = images.get(files
					.indexOf(originalImageFile));
			BufferedImage newImage = new BufferedImage(
					originalImage.getWidth(), originalImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			for (int y = 0; y < newImage.getHeight(); y++) {
				for (int x = 0; x < newImage.getWidth(); x++) {
					int originalImageRed = new Color(originalImage.getRGB(x, y))
							.getRed();
					int originalImageGreen = new Color(originalImage.getRGB(x,
							y)).getGreen();
					int originalImageBlue = new Color(
							originalImage.getRGB(x, y)).getBlue();
					setPixelToWhite(newImage, x, y);
					if (originalImageRed < thresholdR)
						setPixelToBlack(newImage, x, y);
					if (originalImageGreen < thresholdG)
						setPixelToBlack(newImage, x, y);
					if (originalImageBlue < thresholdB)
						setPixelToBlack(newImage, x, y);
					totalPixels++;
					if (totalPixels == nextPercentageInterval) {
						Log.write("Completed " + percentDone + "%",
								Log.STANDARD);
						percentDone += 10;
						nextPercentageInterval += percentageInterval;
					}
				}
			}

			String newImageName = Config.MONOCHROME_IMAGE_FILENAME_PREFIX
					+ "-"
					+ Double.toString(Config.MONOCHROME_THRESHOLD_ADJUSTMENT_CONSTANT)
					+ "-" + originalImageFile.getName();
			File newFile = new File(directory, newImageName);
			try {
				ImageIO.write(newImage, "PNG", newFile);
			} catch (IOException e) {
				Log.write("There was a problem writing the new image file",
						Log.ERROR);
			}
		}
		Config.isProcessingImages = false;
	}

	/**
	 * Provides access to the image files from other parts of the application
	 * @return the image files as an ArrayList<File>
	 */
	public ArrayList<File> getImageFiles() {
		return this.files;
	}

	/**
	 * Checks if this is the same ImageSet as another
	 * 
	 * @param i
	 *            the other ImageSet
	 * @return true if they are the same
	 */
	public boolean equals(ImageSet i) {
		return this.files.equals(i.getImageFiles());
	}

	/**
	 * Sets a particular pixel to be white. This is used in generating the monochrome image.
	 * @param img the image in which the pixel should be converted
	 * @param x the x-coordinate of the pixel
	 * @param y the y-coordinate of the pixel
	 */
	private void setPixelToWhite(BufferedImage img, int x, int y) {
		img.setRGB(x, y, Color.white.getRGB());
	}

	/**
	 * Sets a particular pixel to be black. This is used in generating the monochrome image.
	 * @param img the image in which the pixel should be converted
	 * @param x the x-coordinate of the pixel
	 * @param y the y-coordinate of the pixel
	 */
	private void setPixelToBlack(BufferedImage img, int x, int y) {
		img.setRGB(x, y, Color.black.getRGB());
	}


}
