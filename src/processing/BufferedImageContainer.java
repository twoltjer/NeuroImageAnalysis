// Begin processing/BufferedImageContainer.java

package processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import global.Config;
import global.DebugMessenger;
import global.RuntimeConfig;

public class BufferedImageContainer {
	public static final int DM_COLOR = 0;
	public static final int DM_GRAYSCALE = 1;
	public static final int DM_MONOCHROME = 2;
	public int DM;
	public int thresh;
	public int indexNumber;
	public boolean scaled ;
	public boolean isBuffered = false;
	public BufferedImage image;
	public File imageFile;
	public BufferedImageContainer(int DM, int thresh, int indexNumber, boolean scaled) {
		this.DM = DM;
		this.thresh = thresh;
		this.indexNumber = indexNumber;
		this.scaled = scaled;
	}
	
	public void bufferImage() {
		DebugMessenger.out("Begin buffering image");
		getImageFile();
		try {
			DebugMessenger.out("Reading image");
			image = ImageIO.read(imageFile);
			//image = new BufferedImage(3,3,BufferedImage.TYPE_INT_RGB);
			if(scaled) {
				DebugMessenger.out("Scaling image");
				// Replace the local image objecct with a scaled version of itself
				int imageSize = image.getHeight() * image.getWidth();
				int minSize = Config.PREVIEWER_IMAGE_MIN_SIZE.height * Config.PREVIEWER_IMAGE_MIN_SIZE.width;
				int maxSize = Config.PREVIEWER_IMAGE_MAX_SIZE.height * Config.PREVIEWER_IMAGE_MAX_SIZE.width;
				if(imageSize < minSize) {
					image = ImageManipulation.scaleUp(image, minSize);
				} else if(imageSize > maxSize){
					image = ImageManipulation.scaleDown(image, maxSize);
				}

			}
			DebugMessenger.out("Done reading image");
		} catch (IOException e) {
			DebugMessenger.out("There was a problem reading the image. Check that the image is a format that works.");
			e.printStackTrace();
		}
		this.isBuffered = true;
		DebugMessenger.out("Done buffering image");
	}
	
	/**
	 * Releases image from memory. Having too many images buffered will cause
	 * the program to use up too much memory and possibly even crash.
	 */
	public void unbufferImage() {
		if(this.isBuffered)
			image.flush();
		this.isBuffered = false;
	}

	private void getImageFile() {
		DebugMessenger.out("Fetching image file object");
		this.imageFile = RuntimeConfig.imageFiles.get(this.indexNumber);
		DebugMessenger.out("Done fetching image file object");
	}
	
	/**
	 * Pulls image file again. This is typically run after the index number has been manually changed.
	 */
	public void resetImageFile() {
		getImageFile();
	}
	
	/**
	 * Checks if this BufferedImageContainer is roughly same as another. This is
	 * ideal for checking if an image has already been buffered.
	 * 
	 * @param other
	 *            The other BufferedImageContainer
	 * @return true if all parts of the image containers are the same.
	 */
	public boolean quickEquals(BufferedImageContainer other) {
		if(this.DM != other.DM)
			return false;
		if(this.indexNumber != other.indexNumber)
			return false;
		if(this.thresh != other.thresh)
			return false;
		if(this.scaled != other.scaled)
			return false;
		return true;
	}

	/**
	 * Same as equals, except checks deeper properties of the objects, such as file path and if it's been buffered
	 * @param other
	 * @return if they are the same container
	 */
	public boolean deepEquals(BufferedImageContainer other) {
		if(!equals(other))
			return false;
		if(!this.imageFile.getAbsolutePath().equals(other.imageFile.getAbsolutePath()))
			return false;
		if(this.isBuffered != other.isBuffered)
			return false;
		return true;
	}
	
	/**
	 * Creates a clone of the BufferedImageContainer object. The clone is never
	 * buffered. If desired, it can always be buffered using the bufferImage()
	 * method.
	 * 
	 * @return an unbuffered clone object
	 */
	public BufferedImageContainer cloneUnbuffered() {
		BufferedImageContainer clone = new BufferedImageContainer(DM, thresh, indexNumber, scaled);
		clone.scaled = this.scaled;
		clone.imageFile = this.imageFile;
		return clone;
	}

	@Override
	public String toString() {
		return "[DM:" + DM + ",Thresh:" + thresh + ",Index:" + indexNumber + ",Scaled:" + scaled + "]";
	}
}

// End processing/BufferedImageContainer.java
