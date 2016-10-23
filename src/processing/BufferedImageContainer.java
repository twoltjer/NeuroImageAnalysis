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
	public boolean buffered = false;
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
			DebugMessenger.out("There was a problem reading the image");
			e.printStackTrace();
		}
		this.buffered = true;
		DebugMessenger.out("Done buffering image");
	}
	
	public void unbufferImage() {
		//TODO: Write this method
		this.buffered = false;
	}
	
	private void getImageFile() {
		DebugMessenger.out("Fetching image file object");
		this.imageFile = RuntimeConfig.imageFiles.get(this.indexNumber);
		DebugMessenger.out("Done fetching image file object");
	}
	
}

// End processing/BufferedImageContainer.java