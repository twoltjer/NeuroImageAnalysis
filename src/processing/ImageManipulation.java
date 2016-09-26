package processing;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ImageManipulation {
	/**
	 * Scales image to the desired size. Note that this method modifies (and
	 * returns) the image supplied in the argument, so if it is desired to keep
	 * the original image, a copy should be made first using the copyImage()
	 * method.
	 * 
	 * @param image
	 *            The image to be scaled. Note that this will be modified.
	 * @param maxSize
	 *            Maximum size for the scaled image.
	 * @param minSize
	 *            Minimum size for the scaled image. This should have dimension
	 *            of about half of the maxSize's. If it is larger, then it is
	 *            likely to return a null object.
	 * @return A copy of the image, scaled.
	 */
	public static BufferedImage scaleImage(BufferedImage image, Dimension maxSize, Dimension minSize) {
		BufferedImage scaled;
		/*
		 * TODO: Make the size determination use width as well. Notice how
		 * scaleUp() and scaleDown() use only the height component of the
		 * Dimension objects they are passed.
		 */
		boolean scaleDown = image.getHeight() > maxSize.height;
		if (scaleDown) {
			scaled = scaleDown(image, maxSize);
		} else { // scale up
			scaled = scaleUp(image, minSize);
		}
		return scaled;
	}

	private static BufferedImage scaleDown(BufferedImage image, Dimension maxSize) {
		while (image.getHeight() > maxSize.height) {
			BufferedImage temp = new BufferedImage(image.getWidth() / 2, image.getHeight() / 2, image.getType());
			for (int y = 0; y < temp.getHeight(); y++) {
				for (int x = 0; x < temp.getWidth(); x++) {
					temp.setRGB(x, y, image.getRGB(x * 2, y * 2));
				}
			}
			image.flush();
			image = temp;
		}
		return image;
	}

	private static BufferedImage scaleUp(BufferedImage image, Dimension minSize) {
		while (image.getHeight() < minSize.height) {
			BufferedImage temp = new BufferedImage(image.getWidth() * 2, image.getHeight() * 2, image.getType());
			for (int y = 0; y < temp.getHeight(); y++) {
				for (int x = 0; x < temp.getWidth(); x++) {
					temp.setRGB(x, y, image.getRGB(x / 2, y / 2));
				}
			}
			image.flush();
			image = temp;
		}
		return image;
	}

	public BufferedImage convertFromColorToGrayscale(BufferedImage colorImage) {
		return null;
	}

	public BufferedImage applyThreshold(BufferedImage grayscale, int threshold) {
		return null;
	}
}
