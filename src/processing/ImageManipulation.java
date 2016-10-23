package processing;

import java.awt.image.BufferedImage;

import global.DebugMessenger;

public class ImageManipulation {
	public static BufferedImage scaleDown(BufferedImage image, int maxSize) {
		DebugMessenger.out("Scaling image down");
		int imageSize = calcImageSize(image);
		int numberOfScalings = 0;
		while(imageSize > maxSize) {
			imageSize /= 4;
			numberOfScalings += 1;
		}
		DebugMessenger.out("Scaling down " + numberOfScalings + " times");
		int dimensionDivisor = (int) Math.pow(2.0d, ((double) numberOfScalings)); 
		BufferedImage newImage = new BufferedImage(image.getWidth()/dimensionDivisor, image.getHeight()/dimensionDivisor, BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < newImage.getHeight(); y++) {
			for(int x = 0; x < newImage.getWidth(); x++) {
				newImage.setRGB(x, y, getScaledImageRGBForScaleDown(x, y, numberOfScalings, image));
			}
		}
		DebugMessenger.out("Done scaling down image");
		return newImage;
	}
	
	public static BufferedImage scaleUp(BufferedImage image, int minSize) {
		DebugMessenger.out("Scaling image up");
		int imageSize = calcImageSize(image);
		int numberOfScalings = 0;
		while(imageSize < minSize) {
			imageSize *= 4;
			numberOfScalings += 1;
		}
		DebugMessenger.out("Scaling up " + numberOfScalings + " times");
		int dimensionMultiplier = (int) Math.pow(2.0d, ((double) numberOfScalings)); 
		int newImageHeight = image.getHeight()*dimensionMultiplier;
		int newImageWidth = image.getWidth()*dimensionMultiplier;
		BufferedImage newImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < newImageHeight; y++) {
			for(int x = 0; x < newImageWidth; x++) {
				newImage.setRGB(x, y, getScaledImageRGBForScaleUp(x, y, dimensionMultiplier, image));
			}
		}
		return newImage;
	}
	
	public static BufferedImage convertFromColorToGrayscale(BufferedImage colorImage) {
		return null;
	}
	
	public static BufferedImage applyThreshold(BufferedImage grayscale, int threshold) {
		return null;
	}
	
	private static int calcImageSize(BufferedImage image) {
		return image.getHeight() * image.getHeight();
	}
	
	private static int getScaledImageRGBForScaleDown(int newx, int newy, int dimensionDivisor, BufferedImage originalImage) {
		int oldx = newx * dimensionDivisor;
		int imageWidth = originalImage.getWidth();
		while(oldx > imageWidth) {
			oldx--;
		}
		int oldy = newy * dimensionDivisor;
		int imageHeight = originalImage.getHeight();
		while(oldy > imageHeight) {
			oldy--;
		}
		return originalImage.getRGB(oldx, oldy);
	}
	private static int getScaledImageRGBForScaleUp(int newx, int newy, int dimensionMultiplier, BufferedImage originalImage) {
		int oldx = newx / dimensionMultiplier;
		int imageWidth = originalImage.getWidth();
		while(oldx > imageWidth) {
			oldx--;
		}
		int oldy = newy / dimensionMultiplier;
		int imageHeight = originalImage.getHeight();
		while(oldy > imageHeight) {
			oldy--;
		}
		return originalImage.getRGB(oldx, oldy);
	}	
}
