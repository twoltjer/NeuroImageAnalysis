package image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A class for storing misc operations for image processing. Very handy stuff.
 * 
 * @author twtduck
 * 
 */
public abstract class ImageOps {
	/**
	 * Sets a particular pixel to be white. This is used in generating the
	 * monochrome image.
	 * 
	 * @param img
	 *            the image in which the pixel should be converted
	 * @param x
	 *            the x-coordinate of the pixel
	 * @param y
	 *            the y-coordinate of the pixel
	 */
	public static void setPixelToWhite(BufferedImage img, int x, int y) {
		img.setRGB(x, y, Color.white.getRGB());
	}

	/**
	 * Sets a particular pixel to be black. This is used in generating the
	 * monochrome image.
	 * 
	 * @param img
	 *            the image in which the pixel should be converted
	 * @param x
	 *            the x-coordinate of the pixel
	 * @param y
	 *            the y-coordinate of the pixel
	 */
	public static void setPixelToBlack(BufferedImage img, int x, int y) {
		img.setRGB(x, y, Color.black.getRGB());
	}

	/**
	 * Returns the value of the image, with white being positive. 
	 * @param img the image
	 * @return the value of the image
	 */
	public static double getImageValue(BufferedImage img) {
		int numberOfWhitePixels = 0;

		// Read each pixel in the monochrome image
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (ImageOps.isWhite(img.getRGB(x, y)))
					numberOfWhitePixels++;
			}
		}

		double avgValue = ((double) numberOfWhitePixels)
				/ ((double) ((img.getHeight() * img.getWidth())));

		return avgValue;
	}

	/**
	 * Returns the value of the image, with black being positive
	 * @param img the image
	 * @return the value of the image
	 */
	public static double getInvertedImageValue(BufferedImage img) {
		double val = (1.0d - ImageOps.getImageValue(img));
		return val;
	}

	public static boolean isWhite(int RGB) {
		return (Color.white.getRGB() == RGB);
	}
}
