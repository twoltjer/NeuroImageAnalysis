package processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import global.DebugMessenger;
import global.RuntimeConfig;

public class BufferedImageContainer implements Runnable {
	public int DM;
	public int thresh;
	public int indexNumber;
	public BufferedImage image;
	public BufferedImage scaled;
	public File imageFile;
	public boolean isBuffered = false;
	public boolean scaledIsBuffered = false;

	private int BUFFER_TYPE;
	private int BUFFER_TYPE_NORMAL = 0;
	private int BUFFER_TYPE_SCALED = 1;
	
	public BufferedImageContainer(int DM, int thresh, int indexNumber) {
		this.DM = DM;
		this.thresh = thresh;
		this.indexNumber = indexNumber;
	}

	/**
	 * Reads the image into memory. To read the scaled image, call the bufferScaled() method.
	 */
	public void bufferImage() {
		BUFFER_TYPE = BUFFER_TYPE_NORMAL;
		run();
	}
	
	/**
	 * Buffers the scaled image for display. Note that this will also buffer the normal image if that hasn't happened yet. 
	 */
	public void bufferScaled() {
		if(!isBuffered)
			bufferImage();
		BUFFER_TYPE = BUFFER_TYPE_SCALED;
		run();
	}

	/**
	 * Releases image from memory. Having too many images buffered will cause
	 * the program to use up too much memory and possibly even crash.
	 */
	public void unbufferImage() {
		if(isBuffered)
			image.flush();
		isBuffered = false;
	}
	
	/**
	 * Releases the scaled image from memory, but doesn't unbuffer the normal image. 
	 */
	public void unbufferScaled() {
		if(scaledIsBuffered)
			scaled.flush();
		scaledIsBuffered = false;
	}
	
	/**
	 * Releases both images from memory. Use this if the image is not needed for rendering anymore. 
	 */
	public void unbuffer() {
		unbufferImage();
		unbufferScaled();
	}

	/**
	 * Uses the index of the image to find the actual File object for the image,
	 * and sets the internal File variable.
	 */
	public void setImageFile() {
		imageFile = RuntimeConfig.imageFiles.get(indexNumber);
	}

	/**
	 * Checks if this BufferedImageContainer is roughly same as another. This is
	 * ideal for checking if an image has already been buffered.
	 * 
	 * @param other
	 *            The other BufferedImageContainer
	 * @return true if all parts of the image containers are the same.
	 */
	public boolean equals(BufferedImageContainer other) {
		return false;
	}
	
	/**
	 * Same as equals, except checks deeper properties of the objects
	 * @param other
	 * @return
	 */
	public boolean deepEquals(BufferedImageContainer other) {
		if(!equals(other))
			return false;
		if(!this.imageFile.getAbsolutePath().equals(other.imageFile.getAbsolutePath()))
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
		BufferedImageContainer clone = new BufferedImageContainer(DM, thresh, indexNumber);
		clone.scaled = this.scaled;
		clone.imageFile = this.imageFile;
		return clone;
	}

	@Override
	public void run() {
		try {
			if(BUFFER_TYPE == BUFFER_TYPE_NORMAL) {
				image = ImageIO.read(imageFile);
				isBuffered = true;
			}
			if(BUFFER_TYPE == BUFFER_TYPE_SCALED) {
				// scale image here
				
			}
		} catch (IOException e) {
			DebugMessenger.out("There was a problem reading the image");
			e.printStackTrace();
		}
	}
}
