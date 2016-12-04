package processing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import global.DebugMessenger;
import gui.GUIObjects;

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
	
	/**
	 * High performance method to convert color images to grayscale
	 * @param colorImage The color image
	 * @return The grayscale image
	 */
	public static BufferedImage convertFromColorToGrayscale(BufferedImage colorImage) {
		
		BufferedImage image = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(),  
			    BufferedImage.TYPE_BYTE_GRAY);  
			Graphics g = image.getGraphics();  
			g.drawImage(colorImage, 0, 0, null);  
			g.dispose();  
		return image;
	}
	
	public static BufferedImage applyThreshold(BufferedImage grayscale, BufferedImage color, int threshold) {
		int height = grayscale.getHeight();
		int width = grayscale.getWidth();
		BufferedImage mono = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				//TODO: Here's original code. It was replaced with tooBlue code
				/*
				Color origColor = new Color(grayscale.getRGB(x, y));
				float fThresh = (float) (((double) threshold) / 100 );
				float colorV = getValue(origColor);
				if(fThresh < colorV) {
					mono.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					mono.setRGB(x, y, Color.BLACK.getRGB());
				} 
				*/
				Color origColor = new Color(grayscale.getRGB(x, y));
				boolean tooBlue = false;
				float h = colorToHsv(new Color(color.getRGB(x, y)))[0];
				float s = colorToHsv(new Color(color.getRGB(x, y)))[1];
				if((h > (0.5)) && (h < (0.9)) && (s > 0.25))
					tooBlue = true;
				float fThresh = (float) (((double) threshold) / 100 );
				float colorV = getValue(origColor);
				if(fThresh < colorV) {
					mono.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					mono.setRGB(x, y, Color.BLACK.getRGB());
					if(tooBlue) {
						if(GUIObjects.PreviewerObjects.previewFrame.isVisible()) {
							mono.setRGB(x, y, Color.BLUE.getRGB());
						} else {
							mono.setRGB(x, y, Color.WHITE.getRGB());
						}
						
					}
				}
			}
				
		}
		return mono;
	}
	
	private static int calcImageSize(BufferedImage image) {
		return image.getHeight() * image.getHeight();
	}
	
	private static int getScaledImageRGBForScaleDown(int newx, int newy, int dimensionDivisor, BufferedImage originalImage) {
		int oldx = newx * dimensionDivisor * 2;
		//System.out.println("DIM DIV " + dimensionDivisor);
		int imageWidth = originalImage.getWidth();
		while(oldx > imageWidth) {
			oldx--;
		}
		int oldy = newy * dimensionDivisor * 2;
		int imageHeight = originalImage.getHeight();
		while(oldy > imageHeight) {
			oldy--;
		}
		//System.out.println("Querying for color of (" + newx + "," + newy + ") to be asked from (" + oldx + "," + oldy + ")");
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
	
	private static float[] colorToHsv(Color c) {
		return Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		//TODO: Hue is returning 0. Find out why.
		// Know why: We're converting to grayscale and then measuring hue
	}
	
	private static float getValue(Color c) {
		float[] hsbVals = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return hsbVals[2];
	}
	
	public static float getValueTestAccessor(Color c) {
		return getValue(c);
	}
	
	public static float getHueTestAccessor(Color c) {
		return Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null)[0];
	}

	public static float countPositive(Color posColor, BufferedImage image) {
		DebugMessenger.out("Starting pixel count...");
		int totalPix = 0;
		int posPix = 0;
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				totalPix += 1;
				if(posColor.getRGB() == image.getRGB(x, y))
					posPix += 1;
			}
		}
		float rtn = ((float)posPix)/((float)totalPix);
		DebugMessenger.out("Pixel count complete. " + posPix + "/" + totalPix + "   Returns value of " + rtn);
		return rtn;
	}
}
